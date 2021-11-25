package org.tretton63.timebuddy.interfaces.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.tretton63.timebuddy.entities.Activity;
import org.tretton63.timebuddy.entities.ActivityLog;
import org.tretton63.timebuddy.interfaces.rest.requestentities.TimeTrack;
import org.tretton63.timebuddy.repositories.ActivityLogRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RestController

public class TimeTrackController {


    public TimeTrackController(ActivityLogRepository repository) {
        this.repository = repository;
    }

    private final ActivityLogRepository repository;


    @PostMapping(value = "/start", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void start(@RequestBody TimeTrack timeTrackRequest) {

        ActivityLog activity = new ActivityLog();
        activity.setActivityLogId(UUID.randomUUID());
        activity.setActivity(Activity.valueOf(timeTrackRequest.getActivity()));
        activity.setStartTime(LocalDateTime.parse(timeTrackRequest.getStartTime()));
        repository.save(activity);

    }

    @GetMapping("/start/{date}")
    public Map<Activity, LocalDateTime> dailyReport(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        log.info("Date {}", date);
        Map<Activity, LocalDateTime> dayMap = new HashMap<>();

        log.info("map {} {}", dayMap, Collections.emptyList());

        return dayMap;
    }


}
