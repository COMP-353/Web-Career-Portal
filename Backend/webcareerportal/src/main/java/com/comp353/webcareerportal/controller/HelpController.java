package com.comp353.webcareerportal.controller;

import com.comp353.webcareerportal.models.Help;
import com.comp353.webcareerportal.service.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "help")
public class HelpController {
    @Autowired
    private HelpService helpService;

    @PutMapping(path = "/close/{id}")
    public void closeHelpWithId(@PathVariable(name = "id") int id){
        this.helpService.closeHelp(id);
    }

    @PostMapping(path = "/")
    public void saveNewHelp(@RequestBody Help help){
        this.helpService.saveHelp(help);
    }

    @GetMapping(path = "/{id}")
    public List<Help> getAllOpenHelpForWEmployer(@PathVariable(name = "id") String id){
        return this.helpService.getAllOpenHelpForEmployerWithId(id);
    }
}
