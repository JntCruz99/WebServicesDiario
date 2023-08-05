package com.diarios.diarios.de.notas.controller;

import com.diarios.diarios.de.notas.entities.Aula;
import com.diarios.diarios.de.notas.entities.Cronograma;
import com.diarios.diarios.de.notas.entities.Disciplina;
import com.diarios.diarios.de.notas.repository.AulaRepository;
import com.diarios.diarios.de.notas.repository.CronogramaRepository;
import com.diarios.diarios.de.notas.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/aulas")
public class AulaController {

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private CronogramaRepository cronogramaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;


    @PostMapping("/{cronogramaId}/{disciplinaId}")
    public ResponseEntity<Object> criarAula(@PathVariable UUID cronogramaId, @PathVariable UUID disciplinaId, @RequestBody Aula aula) {
        // Verificar se o cronograma existe
        Optional<Cronograma> optionalCronograma = cronogramaRepository.findById(cronogramaId);
        if (!optionalCronograma.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Cronograma cronograma = optionalCronograma.get();

        // Verificar se a disciplina existe
        Optional<Disciplina> optionalDisciplina = disciplinaRepository.findById(disciplinaId);
        if (!optionalDisciplina.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Disciplina disciplina = optionalDisciplina.get();

        // Verificar se a aula está dentro do cronograma (data da aula >= data de início e <= data de fim do cronograma)
        LocalDate dataAula = aula.getDataAula();
        if (dataAula.isBefore(cronograma.getDataInicio()) || dataAula.isAfter(cronograma.getDataFim())) {
            return ResponseEntity.badRequest().body("A data da aula está fora do cronograma.");
        }

        // Associar a aula à disciplina e ao cronograma
        aula.setDisciplina(disciplina);
        aula.setCronograma(cronograma);
        Aula savedAula = aulaRepository.save(aula);

        return ResponseEntity.ok(savedAula);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aula> buscarAulaPorId(@PathVariable UUID id) {
        Optional<Aula> aulaOptional = aulaRepository.findById(id);
        return aulaOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Aula>> listarAulas() {
        List<Aula> aulas = aulaRepository.findAll();
        return ResponseEntity.ok(aulas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aula> atualizarAula(@PathVariable UUID id, @RequestBody Aula aulaAtualizada) {
        Optional<Aula> aulaOptional = aulaRepository.findById(id);
        if (aulaOptional.isPresent()) {
            Aula aula = aulaOptional.get();

            Aula updatedAula = aulaRepository.save(aula);
            return ResponseEntity.ok(updatedAula);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAula(@PathVariable UUID id) {
        Optional<Aula> aulaOptional = aulaRepository.findById(id);
        if (aulaOptional.isPresent()) {
            aulaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
