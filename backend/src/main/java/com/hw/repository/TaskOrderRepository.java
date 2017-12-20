package com.hw.repository;

import com.hw.model.TaskOrder;
import org.springframework.data.repository.CrudRepository;

public interface TaskOrderRepository extends CrudRepository<TaskOrder, Long> {
}
