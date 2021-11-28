package org.tretton63.timebuddy.interfaces.rest.requestentities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TimeTrack {
    String activity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("time")
    String startTime;
}
