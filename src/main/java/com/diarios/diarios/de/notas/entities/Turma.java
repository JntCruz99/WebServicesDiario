package com.diarios.diarios.de.notas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_turma")
public class Turma implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;

    private String turno;


    @JsonIgnore
    @OneToMany(mappedBy = "turma")
    private List<Aluno> alunos = new ArrayList<>();

    @OneToMany(mappedBy = "turma")
    private List<Disciplina> disciplinas = new ArrayList<>();

    public Turma() {
    }

    public Turma(UUID id, String nome, String turno ){
        this.id = id;
        this.nome = nome;
        this.turno = turno;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return Objects.equals(id, turma.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

