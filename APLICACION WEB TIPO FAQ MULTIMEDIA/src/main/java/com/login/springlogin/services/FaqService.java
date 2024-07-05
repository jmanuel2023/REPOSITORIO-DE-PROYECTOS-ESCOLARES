package com.login.springlogin.services;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.login.springlogin.DTO.FaqDTO;
import com.login.springlogin.models.Faq;

public interface FaqService {
    public String create(FaqDTO faqDTO, MultipartFile file);
    public String update(FaqDTO faqDTO, MultipartFile file);
    public String delete(Integer id);
    public List<Faq> getAll();
    public Optional<Faq> getById(Integer id);
}
