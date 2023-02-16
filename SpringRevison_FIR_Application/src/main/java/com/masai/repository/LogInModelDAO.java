package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.LogInModel;


@Repository
public interface LogInModelDAO extends JpaRepository<LogInModel, Integer>{

}
