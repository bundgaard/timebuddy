package org.tretton63.timebuddy.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tretton63.timebuddy.entities.Activity;
import org.tretton63.timebuddy.entities.ActivityLog;
import org.tretton63.timebuddy.repositories.ActivityLogRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ActivityService {
    public ActivityService(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    private final ActivityLogRepository activityLogRepository;


    public void createActivity(Activity activity, LocalDateTime startTime) {
        ActivityLog al = new ActivityLog();
        al.setActivityLogId(UUID.randomUUID());
        al.setActivity(activity);
        al.setStartTime(Objects.requireNonNullElseGet(
                        startTime,
                        () -> LocalDateTime.now(ZoneId.of("Europe/Stockholm"))
                )
        );
        activityLogRepository.save(al);
    }


    public Map<String, Duration> getDailyReport(String date) {

        LocalDate localDate = LocalDate.parse(date);
        log.info("Date {}", date);

        List<ActivityLog> logs = activityLogRepository.findAll().stream()
                .filter(
                        al ->
                                al
                                        .getStartTime()
                                        .toLocalDate()
                                        .isEqual(localDate)
                )
                .sorted(Comparator.comparing(ActivityLog::getStartTime).reversed()).collect(Collectors.toList());
        Map<String, Duration> result = new HashMap<>();
        for (int i = 1; i < logs.size(); i++) {
            ActivityLog first = logs.get(i - 1);
            ActivityLog current = logs.get(i);
            Duration d = Duration.between(first.getStartTime(), current.getStartTime());
            log.info("duration {}", d);
            result.compute(current.getActivity().name(), (key, value) -> {
                if (value == null) {
                    return d;
                }
                return value.plus(d);
            });
        }


        return result;

    }
}
