package com.hw.repository;

import com.hw.model.TaskStatus;
import org.springframework.data.repository.CrudRepository;

public interface TaskStatusRepository extends CrudRepository<TaskStatus, Long> {
}
