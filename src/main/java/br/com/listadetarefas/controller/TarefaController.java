package br.com.listadetarefas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.listadetarefas.model.Tarefa;
import br.com.listadetarefas.repository.TarefaRepository;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin("*")
public class TarefaController {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@GetMapping
	public List<Tarefa> listaTarefas() {
		return tarefaRepository.findAll(); 
	}
	
	@GetMapping("/pronta/{id}")
	public List<Tarefa> concluirTarefa(@PathVariable Long id) {
		Tarefa tarefa = tarefaRepository.findById(id).get();
		if(tarefa.getPronto()) {
			tarefa.setPronto(false);
			tarefaRepository.save(tarefa);
		} else {
			tarefa.setPronto(true);
			tarefaRepository.save(tarefa);
		}
		
		return this.tarefaRepository.findAll();
	}
	
	@PostMapping
	public List<Tarefa> salvar(@RequestBody Tarefa tarefa) {
		tarefaRepository.save(tarefa);
		return this.tarefaRepository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public List<Tarefa> deletar(@PathVariable Long id) {
		tarefaRepository.deleteById(id);
		return this.tarefaRepository.findAll();
	}

}
