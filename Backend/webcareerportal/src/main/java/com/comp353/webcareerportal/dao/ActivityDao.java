package com.comp353.webcareerportal.dao;

import com.comp353.webcareerportal.models.Activity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityDao extends JpaRepository<Activity, Long> {

    @Query(nativeQuery = true, value = "select * from Activity")
    List<Activity> getAllActivities();

    @Query(nativeQuery = true, value = "select * from Activity a where a.userId = :id")
    List<Activity> getActivitiesForUserWithId(@Value("id") String id);
}
