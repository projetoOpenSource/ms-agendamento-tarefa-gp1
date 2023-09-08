package br.com.agendamento.api.controller;

import br.com.agendamento.api.dto.CreateTaskDto;
import br.com.agendamento.api.exceptions.InternalErrorException;
import br.com.agendamento.api.exceptions.ValidacaoException;
import br.com.agendamento.api.model.CreateTaskModel;
import br.com.agendamento.api.service.CreateTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Classe de controle com o CRUD das tarefas.
 * @author LeoVizeu
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/create_task")
public class CreateTaskController {

    final CreateTaskService createTaskService;

    public CreateTaskController(CreateTaskService createTaskService) {
        this.createTaskService = createTaskService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCreateTask (@RequestBody @Valid CreateTaskDto createTaskDto) throws ValidacaoException, InternalErrorException {
        createTaskService.cadastrarTarefa(createTaskDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CreateTaskModel>> getAllTasks(){
        return ResponseEntity.status(HttpStatus.OK).body(createTaskService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTask(@PathVariable(value = "id") Long id){
        Optional<CreateTaskModel> createTaskModelOptional = createTaskService.findById(id);
        if (!createTaskModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(createTaskModelOptional.get());
    }

    @DeleteMapping("/id")
    public ResponseEntity<Object> deleteTask(@PathVariable(value = "id") Long id){
        Optional<CreateTaskModel> createTaskModelOptional = createTaskService.findById(id);
        if (!createTaskModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task doesn't exists.");
        }
        createTaskService.delete(createTaskModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully.");
    }

    @PutMapping("/id")
    public ResponseEntity<Object> updateTask(@PathVariable(value = "id") Long id,
                                             @RequestBody @Valid CreateTaskDto createTaskDto){
        Optional<CreateTaskModel> createTaskModelOptional = createTaskService.findById(id);
        if (!createTaskModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }
        var createTaskModel = new CreateTaskModel(); //realizar das duas maneiras


        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
