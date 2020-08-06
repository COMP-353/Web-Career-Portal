package com.comp353.webcareerportal.service;

import com.comp353.webcareerportal.dao.ActivityDao;
import com.comp353.webcareerportal.dao.HelpDao;
import com.comp353.webcareerportal.models.Activity;
import com.comp353.webcareerportal.models.Help;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HelpService {
    @Autowired
    private HelpDao helpRepo;
    @Autowired
    private ActivityDao activityRepo;

    public void saveHelp(Help help) {
        helpRepo.save(help);
        activityRepo.save(new Activity(help.getJobSeekerId(), "ASKED FOR HELP"));
    }

    public void closeHelp(int id) {
        Long idL = new Long(id);
        Optional<Help> help = helpRepo.findById(idL);
        if (help.isPresent()) {
            helpRepo.closeHelpWithId(id);
            activityRepo.save(new Activity(help.get().getEmployerId(), "CLOSED A HELP TICKET"));
        }
    }

    public List<Help> getAllOpenHelpForEmployerWithId(String id) {
        return helpRepo.getAllOpenHelpForEmployerWithId(id);
    }
}
