package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Tag;

@Repository
public interface TagDAO extends JpaRepository<Tag, Integer>{

}
