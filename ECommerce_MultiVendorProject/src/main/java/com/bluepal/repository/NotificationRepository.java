package com.bluepal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluepal.model.Notification;



public interface NotificationRepository extends JpaRepository<Notification, Long> {



}
