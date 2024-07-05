package com.login.springlogin.services;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.login.springlogin.DTO.AnswerDTO;
import com.login.springlogin.DTO.IdDTO;
import com.login.springlogin.models.Answer;

public interface RespuestaService {
    public String create(AnswerDTO answerDTO, MultipartFile file);
    public String update(AnswerDTO answerDTO, MultipartFile file);
    public String delete(IdDTO idDTO);
    List<Answer> getAllByFaq(IdDTO idDTO);
}
