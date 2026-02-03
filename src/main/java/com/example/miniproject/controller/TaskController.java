package com.example.miniproject.controller;

import com.example.miniproject.dto.TaskRequest;
import com.example.miniproject.dto.TaskResponse;
import com.example.miniproject.service.TaskService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @PostMapping
  public ResponseEntity<TaskResponse> create(@Valid @RequestBody TaskRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(request));
  }

  @GetMapping
  public List<TaskResponse> list() {
    return taskService.list();
  }

  @GetMapping("/{id}")
  public TaskResponse get(@PathVariable long id) {
    return taskService.get(id);
  }

  @PutMapping("/{id}")
  public TaskResponse update(@PathVariable long id, @Valid @RequestBody TaskRequest request) {
    return taskService.update(id, request);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable long id) {
    taskService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<Map<String, String>> handleNotFound(NoSuchElementException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(Map.of("error", ex.getMessage()));
  }
}
