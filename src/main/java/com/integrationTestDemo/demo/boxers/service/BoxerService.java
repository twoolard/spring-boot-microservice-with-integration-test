package com.integrationTestDemo.demo.boxers.service;

import java.util.List;

import com.integrationTestDemo.demo.boxers.entity.Boxer;

public interface BoxerService {

    public List<Boxer> findAll();

    public Boxer findById(int id);

    public void save(Boxer boxer);

    public void deleteById(int id);
}
