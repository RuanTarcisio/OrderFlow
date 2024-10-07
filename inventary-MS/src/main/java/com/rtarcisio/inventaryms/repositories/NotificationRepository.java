package com.rtarcisio.inventaryms.repositories;

import com.rtarcisio.inventaryms.domains.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
