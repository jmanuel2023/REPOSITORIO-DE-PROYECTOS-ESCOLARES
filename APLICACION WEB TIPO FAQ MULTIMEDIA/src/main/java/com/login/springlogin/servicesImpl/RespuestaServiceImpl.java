package com.login.springlogin.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.login.springlogin.DTO.AnswerDTO;
import com.login.springlogin.DTO.IdDTO;
import com.login.springlogin.models.Answer;
import com.login.springlogin.models.Faq;
import com.login.springlogin.repositories.AnswerRepository;
import com.login.springlogin.repositories.FaqRepository;
import com.login.springlogin.services.RespuestaService;
@Service
public class RespuestaServiceImpl implements RespuestaService{
    @Autowired
    AnswerRepository respuestaRepository;
    @Autowired
    FaqRepository faqRepository;
    @Autowired
    ArchivosServiceImpl archivosService;
    @Override
    public String create(AnswerDTO answerDTO, MultipartFile file) {
        
        //Verificar si la pregunta existe
        Faq pregunta = faqRepository.findById(answerDTO.getId_faq()).orElse(null);
        if(pregunta == null) {
            return "La pregunta no existe";
        }
        Answer res = new Answer(answerDTO.getRespuesta(), pregunta);
        if(file != null){
            System.out.println("Archivo no nulo");
            String archivo = archivosService.saveFile(file);
            if(!archivo.equals("Error")){
                res.setNombreArchivo(archivo);
            }
        }
        respuestaRepository.save(res);
        return "Respuesta creada";
    }

    @Override
    public String update(AnswerDTO answerDTO, MultipartFile file) {
        
        //Verificar que exista la respuesta
        Answer respuesta = respuestaRepository.findById(answerDTO.getId()).orElse(null);

        if(respuesta == null) {
            return "La respuesta no existe";
        }
        if(respuesta.getNombreArchivo() != null && file !=null){
            archivosService.deleteFile(respuesta.getNombreArchivo());
            String archivo = archivosService.saveFile(file);
            if(!archivo.equals("Error")){
                respuesta.setNombreArchivo(archivo);
            }
        }else if(respuesta.getNombreArchivo() == null && file != null){
            //Se le agrego un archivo a una respuesta que no tenia
            String archivo = archivosService.saveFile(file);
            if(!archivo.equals("Error")){
                respuesta.setNombreArchivo(archivo);
            }
        }
        respuesta.setRespuesta(answerDTO.getRespuesta());
        respuestaRepository.save(respuesta);
        return "Respuesta actualizada";
    }

    @Override
    public String delete(IdDTO idDTO) {
        respuestaRepository.deleteById(idDTO.getId());
       return "Respuesta eliminada";
    }

    @Override
    public List<Answer> getAllByFaq(IdDTO idDTO) {
        //Encontrar pregunta
        Faq pregunta = faqRepository.findById(idDTO.getId()).orElse(null);
        return respuestaRepository.findByPregunta(pregunta);
    }
    
}
