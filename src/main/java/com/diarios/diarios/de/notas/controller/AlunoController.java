package com.diarios.diarios.de.notas.controller;

import com.diarios.diarios.de.notas.entities.Aluno;
import com.diarios.diarios.de.notas.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable UUID id) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        return alunoOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Aluno createAluno(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable UUID id, @RequestBody Aluno aluno) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if (alunoOptional.isPresent()) {
            Aluno existingAluno = alunoOptional.get();
            existingAluno.setNome(aluno.getNome()); // Se houver mais atributos, atualize-os aqui
            alunoRepository.save(existingAluno);
            return ResponseEntity.ok(existingAluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable UUID id) {
        alunoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
