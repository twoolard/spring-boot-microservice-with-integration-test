package com.integrationTestDemo.demo.boxers.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integrationTestDemo.demo.boxers.entity.Boxer;

public interface BoxerRepository extends JpaRepository<Boxer, Integer> {


}
