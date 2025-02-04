package com.algostyle.PersoManagTasks.repository;

import com.algostyle.PersoManagTasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByCategoryName(String name);
}
