package com.login.springlogin.models;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
@Entity
@Table(name = "faq")
public class Faq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "pregunta")
    private String pregunta;
    @Column(name = "archivo")
    private String path_file;
  /*  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_faq")
    List<Answer> respuestas; */
    public Faq() {
    }
    public Faq(String pregunta, String path_file) {
        this.pregunta = pregunta;
        this.path_file = path_file;
    }
    public Faq(String pregunta) {
        this.pregunta = pregunta;
    }
    public Faq(int id, String pregunta, String path_file) {
        this.id = id;
        this.pregunta = pregunta;
        this.path_file = path_file;
    }
   /*  public Faq(int id, String pregunta, String path_file, List<Answer> respuestas) {
        this.id = id;
        this.pregunta = pregunta;
        this.path_file = path_file;
        this.respuestas = respuestas;
    }*/
   /*  public Faq(int id, String pregunta, List<Answer> respuestas) {
        this.id = id;
        this.pregunta = pregunta;
        this.respuestas = respuestas;
    }*/

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
   /* public List<Answer> getRespuestas() {
        return respuestas;
    }
    public void setRespuestas(List<Answer> respuestas) {
        this.respuestas = respuestas;
    } */
    
}
