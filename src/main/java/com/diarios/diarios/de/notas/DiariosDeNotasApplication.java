package com.diarios.diarios.de.notas;

import com.diarios.diarios.de.notas.entities.*;
import com.diarios.diarios.de.notas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@RestController
public class DiariosDeNotasApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DiariosDeNotasApplication.class, args);
	}
	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	@Autowired
	private AulaRepository aulaRepository;

	@Autowired
	private FrequenciaRepository frequenciaRepository;

	@Autowired
	private NotaRepository notaRepository;

	@Autowired
	private CronogramaRepository cronogramaRepository;

	@Override
	public void run(String... args) throws Exception {
		//Turma t1 = new Turma(null, "turma2", "manhã");
		//turmaRepository.save(t1);

		//Aluno a1 =  new Aluno("Jonatas Cruz",t1, "20212");
		//alunoRepository.save(a1);



		//Disciplina d1 = new Disciplina(null, "Geografia", t1);
		//disciplinaRepository.save(d1);

		//Aula aula1 = new Aula(
		//		LocalDate.of(2023, 7, 21), // dataAula: 2023-07-21
		//		2, // horasDeAula: 2
		//		LocalTime.of(9, 0), // horaDeInico: 09:00
		//		"Adição", // assunto: "Adição"
		//		"Fulano", // assinatura: "Fulano"
		//		d1, // disciplina: (objeto Disciplina)
		//		null // frequencias: lista vazia
		//);
		//aulaRepository.save(aula1);

		//Frequencia f1 = new Frequencia(a1, aula1, true);
		//frequenciaRepository.save(f1);

		//List<Aula> listaAulas= new ArrayList<>();
		//listaAulas.add(aula1);

		//Cronograma c1 = new Cronograma(t1,LocalDate.of(2023, 7, 20),LocalDate.of(2023, 7, 22),listaAulas);
		//cronogramaRepository.save(c1);
		//aula1.calcularHoraFim();
		//System.out.println(c1);
		//System.out.println(aula1);

		//Nota n1 = new Nota(7.0,7.0,null,null,null,a1,d1);
		//notaRepository.save(n1);
	}
}
