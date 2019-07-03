package com.integrationTestDemo.demo.boxers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integrationTestDemo.demo.boxers.entity.Boxer;
import com.integrationTestDemo.demo.boxers.service.BoxerService;

@RestController
@RequestMapping("/api")
public class BoxerController {

    private BoxerService boxerService;

    @Autowired
    public BoxerController(BoxerService theBoxerService){
        boxerService = theBoxerService;
    }

    @GetMapping("/boxers")
    public List<Boxer> findAll() {
        return boxerService.findAll();
    }


    @GetMapping("/boxers/{id}")
    public Boxer getBoxer(@PathVariable("id") int boxerId) {

        Boxer theBoxer = boxerService.findById(boxerId);

        if (theBoxer == null) {
            throw new RuntimeException("Boxer id not found - " + boxerId);
        }

        return theBoxer;
    }

    @PostMapping("/boxers")
    public Boxer addBoxer(@RequestBody Boxer theBoxer) {

        theBoxer.setId(0);

        boxerService.save(theBoxer);

        return theBoxer;
    }

    @PutMapping("/boxers")
    public Boxer updateBoxer(@RequestBody Boxer theBoxer) {

        boxerService.save(theBoxer);

        return theBoxer;
    }

    @DeleteMapping("/boxers/{id}")
    public String deleteBoxer(@PathVariable("id") int boxerId) {

        Boxer boxer = boxerService.findById(boxerId);

        // throw exception if null

        if (boxer == null) {
            throw new RuntimeException("id not found - " + boxerId);
        }

        boxerService.deleteById(boxerId);

        return "Deleted id - " + boxerId;
    }
}
