package com.weightworks.commons.service;

import com.weightworks.commons.entity.Exercise;
import org.springframework.stereotype.Component;

@Component
public interface ExerciseService {

    public Exercise getExercise(Exercise exercise);

    public Exercise insertIntoDatabase(Exercise exercise);
}