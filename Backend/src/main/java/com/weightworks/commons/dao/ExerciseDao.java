package com.weightworks.commons.dao;

import com.weightworks.commons.entity.Exercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseDao extends CrudRepository<Exercise, Integer> {
}
