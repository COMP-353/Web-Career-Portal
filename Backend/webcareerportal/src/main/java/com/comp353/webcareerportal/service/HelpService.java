package com.comp353.webcareerportal.service;

import com.comp353.webcareerportal.dao.HelpDao;
import com.comp353.webcareerportal.models.Help;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpService {
    @Autowired
    private HelpDao helpRepo;

    public void saveHelp(Help help){
    helpRepo.save(help);
    }

    public void closeHelp(int id){
        helpRepo.closeHelpWithId(id);
    }

    public List<Help> getAllOpenHelpForEmployerWithId(String id){
        return helpRepo.getAllOpenHelpForEmployerWithId(id);
    }
}
