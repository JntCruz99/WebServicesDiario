package com.diarios.diarios.de.notas.controller;

import com.diarios.diarios.de.notas.entities.Aluno;
import com.diarios.diarios.de.notas.entities.Disciplina;
import com.diarios.diarios.de.notas.entities.Frequencia;
import com.diarios.diarios.de.notas.entities.Nota;
import com.diarios.diarios.de.notas.repository.AlunoRepository;
import com.diarios.diarios.de.notas.repository.DisciplinaRepository;
import com.diarios.diarios.de.notas.repository.FrequenciaRepository;
import com.diarios.diarios.de.notas.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private final NotaRepository notaRepository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private AlunoRepository alunoRepository;



    @Autowired
    public NotaController(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Nota>> getAllNotas() {
        List<Nota> notas = notaRepository.findAll();
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> getNotaById(@PathVariable UUID id) {
        Nota nota = notaRepository.findById(id).orElse(null);
        if (nota != null) {
            return ResponseEntity.ok(nota);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{disciplinaId}/{alunoId}")
    public ResponseEntity<Nota> createNota(@PathVariable UUID disciplinaId, @PathVariable UUID alunoId, @RequestBody Nota nota) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId).orElse(null);
        Aluno aluno = alunoRepository.findById(alunoId).orElse(null);

        if (disciplina != null && aluno != null) {
            nota.setDisciplina(disciplina);
            nota.setAluno(aluno);
            Nota savedNota = notaRepository.save(nota);
            return ResponseEntity.ok(savedNota);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> updateNota(@PathVariable UUID id, @RequestBody Nota nota) {
        Nota existingNota = notaRepository.findById(id).orElse(null);
        if (existingNota != null) {
            // Atualiza os campos da nota existente com os dados da nota recebida no corpo da requisição
            existingNota.setN1(nota.getN1());
            existingNota.setN2(nota.getN2());
            existingNota.setN3(nota.getN3());
            existingNota.setN4(nota.getN4());
            existingNota.setpFinal(nota.getpFinal());
            existingNota.setAluno(nota.getAluno());
            existingNota.setDisciplina(nota.getDisciplina());

            // Salva a nota atualizada no banco de dados
            Nota updatedNota = notaRepository.save(existingNota);
            return ResponseEntity.ok(updatedNota);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNota(@PathVariable UUID id) {
        Nota existingNota = notaRepository.findById(id).orElse(null);
        if (existingNota != null) {
            notaRepository.delete(existingNota);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
