package com.algostyle.PersoManagTasks.controller;

import com.algostyle.PersoManagTasks.dto.task.TaskRequestBody;
import com.algostyle.PersoManagTasks.dto.task.TaskResponseBody;
import com.algostyle.PersoManagTasks.model.Task;
import com.algostyle.PersoManagTasks.service.MapperService;
import com.algostyle.PersoManagTasks.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private MapperService mapperService;

    @GetMapping
    public ResponseEntity<List<TaskResponseBody>> getAllTasks(){
        return new ResponseEntity<>(this.taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/get-task/{id}")
    public ResponseEntity<TaskResponseBody> getTaskById(@PathVariable Long id){
        return new ResponseEntity<>(this.taskService.getTaskById(id),HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<String> addTask(@Valid @RequestBody TaskRequestBody taskRequestBody){
        TaskResponseBody taskResponseBody = taskService.addTask(taskRequestBody);
        String str="\n - Titre de la tâche : "+taskResponseBody.getTitle()+"\n - Description de la tâche : "+taskResponseBody.getDescription()+"\n - Date d'échéance de la tâche : "+taskResponseBody.getDueDate()+"\n - Status de la tâche : "+taskResponseBody.getStatus()+"\n - Catégorie de la tâche : "+taskResponseBody.getCategoryName();
        return new ResponseEntity<>("La tâche est ajoutée avec succès"+str,HttpStatus.OK);
    }

    @PutMapping("/update-task/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id,@Valid @RequestBody TaskRequestBody taskRequestBody){
        this.taskService.updateTask(id,taskRequestBody);
        return new ResponseEntity<>("la tâche est modifiée avec succès",HttpStatus.OK);
    }

    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long id){
        this.taskService.deleteTaskById(id);
        return new ResponseEntity<>("la tâche est supprimée avec succès",HttpStatus.OK);
    }

    @GetMapping("/{categoryname}")
    public ResponseEntity<List<TaskResponseBody>> getTasksByCategoryName(@PathVariable("categoryname") String name){
        return new ResponseEntity<>(this.taskService.getAllTasksByCategoryName(name),HttpStatus.OK);
    }
}
