package org.tretton63.timebuddy.interfaces.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.tretton63.timebuddy.entities.Activity;
import org.tretton63.timebuddy.interfaces.rest.requestentities.TimeTrack;
import org.tretton63.timebuddy.services.ActivityService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RestController
public class TimeTrackController {

    private final ActivityService activityService;

    public TimeTrackController(ActivityService activityService) {
        this.activityService = activityService;
    }


    @PostMapping(value = "/start", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void start(@RequestBody TimeTrack timeTrackRequest) {
        activityService.createActivity(
                Activity.valueOf(timeTrackRequest.getActivity()),
                LocalDateTime.parse(timeTrackRequest.getStartTime())
        );

    }

    @GetMapping("/start/{date}")
    public Map<String, Duration> dailyReport(@PathVariable String date) {
        return activityService.getDailyReport(date);
    }


}
