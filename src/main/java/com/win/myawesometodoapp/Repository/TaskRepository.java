package com.win.myawesometodoapp.Repository;

import com.win.myawesometodoapp.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
