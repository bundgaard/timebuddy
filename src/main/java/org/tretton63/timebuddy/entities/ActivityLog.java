package org.tretton63.timebuddy.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Slf4j
@ToString(exclude = "activityLogId")
@Entity
public class ActivityLog {
    @Id
    private UUID activityLogId;

    @Enumerated(EnumType.STRING)
    private Activity activity;

    private LocalDateTime startTime;

}
