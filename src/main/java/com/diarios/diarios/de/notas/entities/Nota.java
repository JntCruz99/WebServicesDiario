package com.diarios.diarios.de.notas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_notas")
public class Nota implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Double n1;
    private Double n2;
    private Double n3;
    private Double n4;
    private Double pFinal;
    private Double mFinal;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    public Nota() {
    }

    public Nota(Double n1, Double n2, Double n3, Double n4, Double pFinal, Aluno aluno, Disciplina disciplina) {
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.n4 = n4;
        this.pFinal = pFinal;
        this.aluno = aluno;
        this.disciplina = disciplina;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getN1() {
        return n1;
    }

    public void setN1(Double n1) {
        this.n1 = n1;
    }

    public Double getN2() {
        return n2;
    }

    public void setN2(Double n2) {
        this.n2 = n2;
    }

    public Double getN3() {
        return n3;
    }

    public void setN3(Double n3) {
        this.n3 = n3;
    }

    public Double getN4() {
        return n4;
    }

    public void setN4(Double n4) {
        this.n4 = n4;
    }

    public Double getpFinal() {
        return pFinal;
    }

    public void setpFinal(Double pFinal) {
        this.pFinal = pFinal;
    }

    public Double getmFinal() {
        // Verifica se há notas para calcular a média final
        if (n1 != null && n2 != null) {
            // Caso haja n1 e n2, faz a média inicial com elas
            mFinal = (n1 + n2) / 2.0;
        } else {
            // Caso não tenha n1 e n2, a média inicial é nula
            mFinal = null;
        }

        // Verifica se há notas para calcular a média final a partir das demais notas
        if (n3 != null) {
            // Se há n3, atualiza a média final considerando n3
            mFinal = (mFinal != null) ? (mFinal + n3) / 3.0 : n3;
        }

        if (n4 != null) {
            // Se há n4, atualiza a média final considerando n4
            mFinal = (mFinal != null) ? (mFinal + n4) / 2.0 : n4;
        }

        if (pFinal != null) {
            // Se há pFinal, substitui a nota mais baixa pela pFinal na média final
            if (n1 != null && n2 != null && n3 != null && n4 != null) {
                // Todas as notas n1, n2, n3 e n4 estão presentes
                // Substitui a menor nota (mínimo entre n1, n2, n3 e n4) pela pFinal
                Double minNota = Math.min(Math.min(n1, n2), Math.min(n3, n4));
                mFinal = (mFinal + pFinal - minNota) / 4.0;
            } else if (n1 == null && n2 == null && n3 == null && n4 == null) {
                // Não há notas n1, n2, n3 e n4 (ou seja, a média é nula)
                mFinal = pFinal;
            } else {
                // Caso alguma das notas n1, n2, n3 ou n4 seja nula, a média final não pode ser calculada
                // Portanto, a pFinal não será utilizada para calcular a média final
            }
        }

        return mFinal;
    }


    public void setmFinal(Double mFinal) {
        this.mFinal = mFinal;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;
        return Objects.equals(id, nota.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
