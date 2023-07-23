package com.diarios.diarios.de.notas.controller;

import com.diarios.diarios.de.notas.entities.Disciplina;
import com.diarios.diarios.de.notas.entities.Frequencia;
import com.diarios.diarios.de.notas.repository.DisciplinaRepository;
import com.diarios.diarios.de.notas.repository.FrequenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/frequencias")
public class FrequenciaController {

    @Autowired
    private FrequenciaRepository frequenciaRepository;

    @GetMapping
    public List<Frequencia> getAllDfrequencia() {
        return frequenciaRepository.findAll();
    }
}
