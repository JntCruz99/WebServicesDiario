package com.diarios.diarios.de.notas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_frequencia")
public class Frequencia implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "aula_id")
    private Aula aula;

    private boolean presente;


    public Frequencia() {
    }

    public Frequencia(Aluno aluno, Aula aula, boolean presente) {
        this.aluno = aluno;
        this.aula = aula;
        this.presente = presente;
    }

    public Frequencia(UUID id, Aluno aluno, Aula aula, boolean presente) {
        this.id = id;
        this.aluno = aluno;
        this.aula = aula;
        this.presente = presente;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }
}
