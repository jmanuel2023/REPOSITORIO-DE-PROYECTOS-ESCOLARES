package com.login.springlogin.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "respuestas")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String respuesta;
    private String nombreArchivo;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_faq")
    private Faq pregunta;
    public Answer() {}
    public Answer(String respuesta, Faq pregunta) {
        this.respuesta = respuesta;
        this.pregunta = pregunta;
    }
    public Answer(String respuesta, String nombreArchivo, Faq pregunta) {
        this.respuesta = respuesta;
        this.nombreArchivo = nombreArchivo;
        this.pregunta = pregunta;
    }
    public Answer(Integer id, String respuesta, String nombreArchivo, Faq pregunta) {
        this.id = id;
        this.respuesta = respuesta;
        this.nombreArchivo = nombreArchivo;
        this.pregunta = pregunta;
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
    public Faq getPregunta() {
        return pregunta;
    }
    public void setPregunta(Faq pregunta) {
        this.pregunta = pregunta;
    }
    
}
