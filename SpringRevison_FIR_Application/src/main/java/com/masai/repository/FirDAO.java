package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.FIR;

@Repository
public interface FirDAO extends JpaRepository<FIR, Integer>{

}
