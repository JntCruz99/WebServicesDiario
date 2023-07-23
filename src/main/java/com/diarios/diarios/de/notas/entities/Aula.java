package com.diarios.diarios.de.notas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "tb_aula")
public class Aula implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "data_aula")
    private LocalDate dataAula;

    private int horasDeAula;

    private LocalTime horaDeInico;

    private LocalTime horaDeFim;

    private String assunto;

    private String assinatura;



    @ManyToOne
    @JoinColumn(name = "cronograma_id")
    private Cronograma cronograma;

    @OneToMany(mappedBy = "aula")
    private List<Frequencia> frequencias = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    public Aula() {
    }

    public Aula(LocalDate dataAula, int horasDeAula, LocalTime horaDeInico, String assunto, String assinatura, Cronograma cronograma, List<Frequencia> frequencias, Disciplina disciplina) {
        this.dataAula = dataAula;
        this.horasDeAula = horasDeAula;
        this.horaDeInico = horaDeInico;
        this.assunto = assunto;
        this.assinatura = assinatura;
        this.cronograma = cronograma;
        this.frequencias = frequencias;
        this.disciplina = disciplina;
    }

    public LocalDate getDataAula() {
        return dataAula;
    }

    public void setDataAula(LocalDate dataAula) {
        this.dataAula = dataAula;
    }

    public int getHorasDeAula() {
        return horasDeAula;
    }

    public void setHorasDeAula(int horasDeAula) {
        this.horasDeAula = horasDeAula;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(String assinatura) {
        this.assinatura = assinatura;
    }


    public List<Frequencia> getFrequencias() {
        return frequencias;
    }

    public void setFrequencias(List<Frequencia> frequencias) {
        this.frequencias = frequencias;
    }

    public LocalTime getHoraDeInico() {
        return horaDeInico;
    }

    public void setHoraDeInico(LocalTime horaDeInico) {
        calcularHoraFim();
        this.horaDeInico = horaDeInico;
        // Após setar a hora de início, recalcula a hora de fim

    }

    public LocalTime getHoraDeFim() {
        return horaDeFim;
    }


    public void calcularHoraFim() {
        if (horaDeInico != null && horasDeAula > 0) {
            horaDeFim = horaDeInico.plusHours(horasDeAula);
        }
    }

    public Cronograma getCronograma() {
        return cronograma;
    }

    public void setCronograma(Cronograma cronograma) {
        this.cronograma = cronograma;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
}
