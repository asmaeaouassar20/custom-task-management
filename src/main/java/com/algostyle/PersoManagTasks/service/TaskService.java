package com.algostyle.PersoManagTasks.service;

import com.algostyle.PersoManagTasks.dto.task.TaskRequestBody;
import com.algostyle.PersoManagTasks.dto.task.TaskResponseBody;
import com.algostyle.PersoManagTasks.exception.CategoryNotFoundException;
import com.algostyle.PersoManagTasks.exception.IdNotFoundException;
import com.algostyle.PersoManagTasks.model.Category;
import com.algostyle.PersoManagTasks.model.Task;
import com.algostyle.PersoManagTasks.repository.CategoryRepository;
import com.algostyle.PersoManagTasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private MapperService mapperService;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<TaskResponseBody>  getAllTasks(){
        List<TaskResponseBody> list=new ArrayList<>();
        for(Task task:this.taskRepository.findAll()){
            list.add(this.mapperService.toTaskResponseBody(task));
        }
        return list;
    }

    public TaskResponseBody addTask(TaskRequestBody taskRequestBody){
        Optional<Category> fetchedCategoryByName=categoryRepository.findByName(taskRequestBody.getCategoryName());
        if(!fetchedCategoryByName.isPresent()){
            throw new CategoryNotFoundException("la catégorie saisie n'existe pas");
        }
        Task task=mapperService.toTask(taskRequestBody);
        Task task2=this.taskRepository.save(task);
        return this.mapperService.toTaskResponseBody(task2);
    }

    public void updateTask(Long id,TaskRequestBody taskRequestBody){
        Optional<Task> fetchedTskOpt=taskRepository.findById(id);
        if(!fetchedTskOpt.isPresent()){
            throw new IdNotFoundException("id de la tâche saisi n'existe pas");
        }
        Task fetchedTask=fetchedTskOpt.get();
        fetchedTask.setTitle(taskRequestBody.getTitle());
        fetchedTask.setDescription(taskRequestBody.getDescription());
        fetchedTask.setStatus(taskRequestBody.getStatus());
        fetchedTask.setDueDate(taskRequestBody.getDueDate());
        fetchedTask.setCategoryName(taskRequestBody.getCategoryName());
        this.taskRepository.save(fetchedTask);
    }

    public TaskResponseBody getTaskById(Long id){
        Optional<Task> fetchedTskOpt=taskRepository.findById(id);
        if(!fetchedTskOpt.isPresent()){
            throw new IdNotFoundException("id de la tâche n'existe pas");
        }
        Task task=fetchedTskOpt.get();
        return this.mapperService.toTaskResponseBody(task);
    }

    public void deleteTaskById(Long id){
        Optional<Task> fetchedTaskOpt=taskRepository.findById(id);
        if(!fetchedTaskOpt.isPresent()){
            throw new IdNotFoundException("id de la tâche à supprimer n'existe pas");
        }
        this.taskRepository.deleteById(id);
    }

    public List<TaskResponseBody> getAllTasksByCategoryName(String categoryName){
        Optional<Category> categoryOptional=this.categoryRepository.findByName(categoryName);
        if(!categoryOptional.isPresent()){
            throw new CategoryNotFoundException("le nom de la catégorie est introuvable");
        }
        List<Task> taskList=this.taskRepository.findByCategoryName(categoryName);
        List<TaskResponseBody> taskResponseBodyList=new ArrayList<>();
        for(Task task:taskList){
            TaskResponseBody taskResponseBody=this.mapperService.toTaskResponseBody(task);
            taskResponseBodyList.add(taskResponseBody);
        }
        return taskResponseBodyList;
    }

}
