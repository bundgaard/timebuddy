package org.tretton63.timebuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tretton63.timebuddy.entities.ActivityLog;

import java.util.UUID;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, UUID> {
}
