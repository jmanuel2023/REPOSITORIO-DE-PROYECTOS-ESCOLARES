package com.login.springlogin.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.login.springlogin.DTO.FaqDTO;
import com.login.springlogin.DTO.IdDTO;
import com.login.springlogin.models.Faq;
import com.login.springlogin.services.FaqService;

@RestController
@RequestMapping("/api/faq")
public class FaqController {
    @Autowired
    FaqService faqService;
    @PostMapping("/crear")
    public ResponseEntity<?> createFaq(@RequestParam String pregunta, @RequestPart(required = false) MultipartFile file) {
        System.out.println("Hola");
        FaqDTO faqDTO = new FaqDTO(pregunta);
        return ResponseEntity.ok().body(getResponse(faqService.create(faqDTO, file)));
    }
    @PostMapping("/actualizar")
    public ResponseEntity<?> updateFaq(@RequestParam String id,@RequestParam String pregunta,@RequestParam(name = "ruta") String file_ruta,@RequestPart(required = false) MultipartFile file) {
        FaqDTO faqDTO = new FaqDTO(Integer.parseInt(id),pregunta,file_ruta);
        return ResponseEntity.ok().body(getResponse(faqService.update(faqDTO, file)));
    }
    @PostMapping("/eliminar")
    public ResponseEntity<?> deleteFaq(@RequestBody IdDTO idDTO) {   
        return ResponseEntity.ok().body(getResponse(faqService.delete(idDTO.getId())));
    }
    @GetMapping
    public ResponseEntity<?> getAllFaqs() {
        return ResponseEntity.ok().body(faqService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getFaqById(@PathVariable int id) {
        Optional<?> faq = faqService.getById(id);
        if (!faq.isPresent()) {
            return ResponseEntity.ok().body("No se encontró el faq");
        }
        return ResponseEntity.ok().body(faq.get());
    }
    private Map<String,String> getResponse(String mensaje) {
        Map<String,String> response = new HashMap<>();
        response.put("mensaje", mensaje);
        return response;
    }
}
