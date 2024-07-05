package com.login.springlogin.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.login.springlogin.models.Answer;
import com.login.springlogin.models.Faq;

public interface AnswerRepository extends JpaRepository<Answer, Integer>{
    List<Answer> findByPregunta(Faq pregunta);
}
