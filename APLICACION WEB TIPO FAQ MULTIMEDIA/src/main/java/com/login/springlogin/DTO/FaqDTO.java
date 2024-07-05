package com.login.springlogin.DTO;

import java.util.List;

public class FaqDTO {
    private int id;
    private String pregunta;
   // private List<AnswerDTO> respuestas;
    private String path_file;
    public FaqDTO() {
    }
    public FaqDTO(String pregunta) {
        this.pregunta = pregunta;
    }
    public FaqDTO(String pregunta, String path_file) {
        this.pregunta = pregunta;
        this.path_file = path_file;
    }
    public FaqDTO(int id,String pregunta, String path_file) {
        this.id = id;
        this.pregunta = pregunta;
        this.path_file = path_file;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPregunta() {
        return pregunta;
    }
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
   
    public String getPath_file() {
        return path_file;
    }
    public void setPath_file(String path_file) {
        this.path_file = path_file;
    }
    /*public List<AnswerDTO> getRespuestas() {
        return respuestas;
    }
    public void setRespuestas(List<AnswerDTO> respuestas) {
        this.respuestas = respuestas;
    }*/
    
}
