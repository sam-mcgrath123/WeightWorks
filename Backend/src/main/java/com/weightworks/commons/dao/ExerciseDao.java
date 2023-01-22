package com.weightworks.commons.dao;

import com.weightworks.commons.entity.Exercise;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseDao extends CrudRepository<Exercise, Long> {
    Exercise findById(int id);
}
