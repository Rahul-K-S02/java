package com.example.miniproject.service;

import com.example.miniproject.dto.TaskRequest;
import com.example.miniproject.dto.TaskResponse;
import com.example.miniproject.model.Task;
import com.example.miniproject.model.TaskStatus;
import com.example.miniproject.repository.TaskRepository;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public TaskResponse create(TaskRequest request) {
    Instant now = Instant.now();
    Task task = new Task(
        0,
        request.getTitle(),
        request.getDescription(),
        defaultStatus(request.getStatus()),
        now,
        now);
    Task saved = taskRepository.save(task);
    return toResponse(saved);
  }

  public List<TaskResponse> list() {
    return taskRepository.findAll().stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
  }

  public TaskResponse get(long id) {
    Task task = taskRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Task not found"));
    return toResponse(task);
  }

  public TaskResponse update(long id, TaskRequest request) {
    Task task = taskRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Task not found"));
    task.setTitle(request.getTitle());
    task.setDescription(request.getDescription());
    task.setStatus(defaultStatus(request.getStatus()));
    task.setUpdatedAt(Instant.now());
    Task saved = taskRepository.save(task);
    return toResponse(saved);
  }

  public void delete(long id) {
    if (taskRepository.findById(id).isEmpty()) {
      throw new NoSuchElementException("Task not found");
    }
    taskRepository.deleteById(id);
  }

  private TaskStatus defaultStatus(TaskStatus status) {
    return status == null ? TaskStatus.TODO : status;
  }

  private TaskResponse toResponse(Task task) {
    TaskResponse response = new TaskResponse();
    response.setId(task.getId());
    response.setTitle(task.getTitle());
    response.setDescription(task.getDescription());
    response.setStatus(task.getStatus());
    response.setCreatedAt(task.getCreatedAt());
    response.setUpdatedAt(task.getUpdatedAt());
    return response;
  }
}
