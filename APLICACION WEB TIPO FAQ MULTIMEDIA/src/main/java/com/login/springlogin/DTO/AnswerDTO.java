package com.login.springlogin.DTO;

public class AnswerDTO {
    private  Integer id;
    private String respuesta;
    private String nombreArchivo;
    private Integer id_faq;
    public AnswerDTO() {}
    public AnswerDTO(String respuesta, Integer id_faq) {
        this.respuesta = respuesta;
        this.id_faq = id_faq;
    }
    public AnswerDTO(String respuesta, String nombreArchivo, Integer id_faq) {
        this.respuesta = respuesta;
        this.nombreArchivo = nombreArchivo;
        this.id_faq = id_faq;
    }
    public AnswerDTO(Integer id, String respuesta, String nombreArchivo, Integer id_faq) {
        this.id = id;
        this.respuesta = respuesta;
        this.nombreArchivo = nombreArchivo;
        this.id_faq = id_faq;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getRespuesta() {
        return respuesta;
    }
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    public String getNombreArchivo() {
        return nombreArchivo;
    }
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    public Integer getId_faq() {
        return id_faq;
    }
    public void setId_faq(Integer id_faq) {
        this.id_faq = id_faq;
    }
    
}
