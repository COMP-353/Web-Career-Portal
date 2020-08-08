package com.comp353.webcareerportal.controller;

import com.comp353.webcareerportal.dao.ActivityDao;
import com.comp353.webcareerportal.models.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "activity")
public class ActivityController {

    @Autowired
    private ActivityDao activityDao;

    @GetMapping(path = "/")
    public List<Activity> getAllActivity(){
        return activityDao.getAllActivities();
    }

    @DeleteMapping(path = "/")
    public void deleteAllRecords(){
        activityDao.deleteAll();
    }

    @GetMapping(path = "/{id}")
    public List<Activity> getAllActivitiesForUser(@PathVariable(value = "id") String id){
        return activityDao.getActivitiesForUserWithId(id);
    }
}
