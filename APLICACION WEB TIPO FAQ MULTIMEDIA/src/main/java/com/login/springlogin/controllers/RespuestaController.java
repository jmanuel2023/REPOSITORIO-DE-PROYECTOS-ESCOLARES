package com.login.springlogin.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.login.springlogin.DTO.AnswerDTO;
import com.login.springlogin.DTO.IdDTO;
import com.login.springlogin.models.Answer;
import com.login.springlogin.services.RespuestaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/respuesta")
public class RespuestaController {
    @Autowired
     RespuestaService respuestaService;
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestParam String respuesta, @RequestParam String id_faq, @RequestPart(required = false)  MultipartFile file) {
        AnswerDTO res = new AnswerDTO(respuesta, Integer.parseInt(id_faq));
        return ResponseEntity.ok().body(getResponse(respuestaService.create(res, file)));
    }
    @PostMapping("/actualizar")
    public ResponseEntity<?> update(@RequestParam String respuesta, @RequestParam String id, @RequestParam String id_faq, @RequestParam String nombreArchivo,@RequestPart(required = false) MultipartFile file) {
        AnswerDTO res = new AnswerDTO(Integer.parseInt(id), respuesta, nombreArchivo, Integer.parseInt(id_faq));
        return ResponseEntity.ok().body(getResponse(respuestaService.update(res, file)));
    }
    @PostMapping("/eliminar")
    public ResponseEntity<?> delete(@RequestBody IdDTO idDTO) {
        return ResponseEntity.ok().body(getResponse(respuestaService.delete(idDTO)));
    }
    @GetMapping("/respuestas/{id}")
    public List<Answer> Faqrespuestas(@PathVariable String id) {
        IdDTO idDTO = new IdDTO(Integer.parseInt(id));
        return respuestaService.getAllByFaq(idDTO);
    }
       private Map<String,String> getResponse(String mensaje) {
        Map<String,String> response = new HashMap<>();
        response.put("mensaje", mensaje);
        return response;
    }
   
    
}
