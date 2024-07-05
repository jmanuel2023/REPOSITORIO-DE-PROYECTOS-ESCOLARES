package com.login.springlogin.servicesImpl;




import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.ClassPathResource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.login.springlogin.DTO.FaqDTO;
import com.login.springlogin.models.Faq;
import com.login.springlogin.repositories.AnswerRepository;
import com.login.springlogin.repositories.FaqRepository;
import com.login.springlogin.services.FaqService;


@Service
public class FaqServiceImpl implements FaqService{
    @Autowired
    FaqRepository faqRepository;
    @Autowired 
    AnswerRepository respuestaRepository;
    @Autowired
    ArchivosServiceImpl archivosService;
    public String create(FaqDTO faqDTO, MultipartFile file) {
        Faq faq = new Faq(faqDTO.getPregunta());
        if(file != null){
            String archivo = archivosService.saveFile(file);
        if(!archivo.equals("Error")){
            faq.setPath_file(archivo);
        }   
        }
             
        faqRepository.save(faq);
         return "Faq creado";
    }
    public String update(FaqDTO faqDTO, MultipartFile file) {
        Faq faq = faqRepository.findById(faqDTO.getId()).get();
        if(faqDTO.getPath_file() != null && file != null){
            archivosService.deleteFile(faq.getPath_file());
            String archivo = archivosService.saveFile(file);
            if(!archivo.equals("Error")){
                faq.setPath_file(archivo);
            }
        }else if(faqDTO.getPath_file() == null && file != null){
            String archivo = archivosService.saveFile(file);
            if(!archivo.equals("Error")){
                faq.setPath_file(archivo);
            }
        }
        faq.setPregunta(faqDTO.getPregunta());
        faqRepository.save(faq);
        return "Faq actualizado";
    }
    public String delete(Integer id) {
        Faq faq = faqRepository.findById(id).get();
        respuestaRepository.deleteAll(respuestaRepository.findByPregunta(faq));
        faqRepository.deleteById(id);
        if(faq.getPath_file() != null){
            archivosService.deleteFile(faq.getPath_file());
        }
        
        return "Faq eliminado";
    }
    public List<Faq> getAll() {
        List<Faq> faqs = faqRepository.findAll();
        return faqs;
    }
    public Optional<Faq> getById(Integer id) {
        Optional<Faq> faq = faqRepository.findById(id);
        return faq;
    }

   }
