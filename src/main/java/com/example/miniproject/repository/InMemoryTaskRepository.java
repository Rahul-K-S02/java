package com.example.miniproject.repository;

import com.example.miniproject.model.Task;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryTaskRepository implements TaskRepository {
  private final Map<Long, Task> store = new ConcurrentHashMap<>();
  private final AtomicLong idSequence = new AtomicLong(1);

  @Override
  public Task save(Task task) {
    if (task.getId() == 0) {
      long id = idSequence.getAndIncrement();
      Task created = new Task(
          id,
          task.getTitle(),
          task.getDescription(),
          task.getStatus(),
          task.getCreatedAt(),
          task.getUpdatedAt());
      store.put(id, created);
      return created;
    }
    store.put(task.getId(), task);
    return task;
  }

  @Override
  public List<Task> findAll() {
    List<Task> tasks = new ArrayList<>(store.values());
    tasks.sort(Comparator.comparing(Task::getCreatedAt).reversed());
    return tasks;
  }

  @Override
  public Optional<Task> findById(long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public void deleteById(long id) {
    store.remove(id);
  }
}
