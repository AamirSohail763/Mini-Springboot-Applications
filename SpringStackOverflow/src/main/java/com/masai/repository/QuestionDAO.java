package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Question;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer>{

}
