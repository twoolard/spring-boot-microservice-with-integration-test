package com.integrationTestDemo.demo.boxers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integrationTestDemo.demo.boxers.dao.BoxerRepository;
import com.integrationTestDemo.demo.boxers.entity.Boxer;

@Service
public class BoxerServiceImpl implements BoxerService {

    private BoxerRepository boxerRepository;

    @Autowired
    public BoxerServiceImpl(BoxerRepository boxerRepository){
        this.boxerRepository = boxerRepository;
    }

    @Override
    public List<Boxer> findAll() {
        return boxerRepository.findAll();
    }

    @Override
    public Boxer findById(int id) {
        Optional<Boxer> result = boxerRepository.findById(id);

        Boxer theBoxer = null;

        if (result.isPresent()) {
            theBoxer = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find id - " + id);
        }

        return theBoxer;
    }

    @Override
    public void save(Boxer boxer) {
        boxerRepository.save(boxer);
    }

    @Override
    public void deleteById(int id) {
        boxerRepository.deleteById(id);
    }
}
