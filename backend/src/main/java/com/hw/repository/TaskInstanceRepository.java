package com.hw.repository;

import com.hw.model.TaskInstance;
import org.springframework.data.repository.CrudRepository;

public interface TaskInstanceRepository extends CrudRepository<TaskInstance, Long> {
}
