package com.win.myawesometodoapp.Repository;

import com.win.myawesometodoapp.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TaskRepository extends JpaRepository<Task,Long> {

    Collection<Task> findByUserId(Long userId);
}
