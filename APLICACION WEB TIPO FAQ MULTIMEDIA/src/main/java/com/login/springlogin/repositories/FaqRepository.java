package com.login.springlogin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.springlogin.models.Faq;

public interface FaqRepository extends JpaRepository<Faq, Integer>{
    
}
