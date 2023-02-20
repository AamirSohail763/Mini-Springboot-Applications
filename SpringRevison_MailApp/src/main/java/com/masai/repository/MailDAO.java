package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Mail;

@Repository
public interface MailDAO extends JpaRepository<Mail, Integer>{

}
