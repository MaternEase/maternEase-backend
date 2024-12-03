package com.maternease.maternease.repository;

import com.maternease.maternease.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,Integer> {
    List<Notification> findByUserIdAndIsRead(int userId, boolean isRead);
}
