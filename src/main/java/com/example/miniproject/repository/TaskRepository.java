package com.example.miniproject.repository;

import com.example.miniproject.model.Task;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {
  Task save(Task task);

  List<Task> findAll();

  Optional<Task> findById(long id);

  void deleteById(long id);
}
