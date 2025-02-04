package com.algostyle.PersoManagTasks.service;

import com.algostyle.PersoManagTasks.dto.category.CategoryRequestBody;
import com.algostyle.PersoManagTasks.dto.category.CategoryResponseBody;
import com.algostyle.PersoManagTasks.dto.task.TaskRequestBody;
import com.algostyle.PersoManagTasks.dto.task.TaskResponseBody;
import com.algostyle.PersoManagTasks.model.Category;
import com.algostyle.PersoManagTasks.model.Task;
import org.springframework.stereotype.Service;

@Service
public class MapperService {
    public Category toCategory(CategoryRequestBody categoryRequestBody){
        Category category=new Category();
        category.setName(categoryRequestBody.getName());
        return category;
    }

    public CategoryResponseBody toCategoryResponseBody(Category category){
        CategoryResponseBody categoryResponseBody=new CategoryResponseBody();
        categoryResponseBody.setName(category.getName());
        return categoryResponseBody;
    }

    public Task toTask(TaskRequestBody taskRequestBody){
        Task task=new Task();
        task.setTitle(taskRequestBody.getTitle());
        task.setDescription(taskRequestBody.getDescription());
        task.setStatus(taskRequestBody.getStatus());
        task.setCategoryName(taskRequestBody.getCategoryName());
        task.setDueDate(taskRequestBody.getDueDate());
        return task;
    }

    public TaskResponseBody toTaskResponseBody(Task task){
        TaskResponseBody taskResponseBody=new TaskResponseBody();
        taskResponseBody.setTitle(task.getTitle());
        taskResponseBody.setDescription(task.getDescription());
        taskResponseBody.setStatus(task.getStatus());
        taskResponseBody.setDueDate(task.getDueDate());
        taskResponseBody.setCategoryName(task.getCategoryName());
        return taskResponseBody;
    }
}
