package com.weightworks.commons.service;

import com.weightworks.commons.dao.ExerciseDao;
import com.weightworks.commons.dao.RoutineDao;
import com.weightworks.commons.dao.WorkoutDao;
import com.weightworks.commons.entity.Exercise;
import com.weightworks.commons.entity.Routine;
import com.weightworks.commons.entity.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    ExerciseDao exerciseDao;

    @Autowired
    RoutineDao routineDao;

    @Autowired
    WorkoutDao workoutDao;

    public Routine updateExerciseRoutines(int routineId, List<Integer> exercises) {
        Optional<Routine> optionalRoutine = routineDao.findById(routineId);
        if (optionalRoutine.isEmpty()) {
            return null;
        }
        Routine routine = optionalRoutine.get();
        for (int i : exercises) {
            Optional<Exercise> optionalExercise = exerciseDao.findById(i);
            if (optionalExercise.isEmpty()) {
                return null;
            }
            Exercise exercise = optionalExercise.get();
            exercise.setRoutine(routine);
            exerciseDao.save(exercise);
        }
        return routine;
    }

    public Workout updateExerciseWorkouts(int workoutId, List<Integer> exercises) {
        Optional<Workout> optionalWorkout = workoutDao.findById(workoutId);
        if (optionalWorkout.isEmpty()) {
            return null;
        }
        Workout workout = optionalWorkout.get();
        for (int i : exercises) {
            Optional<Exercise> optionalExercise = exerciseDao.findById(i);
            if (optionalExercise.isEmpty()) {
                return null;
            }
            Exercise exercise = optionalExercise.get();
            exercise.setWorkout(workout);
            exerciseDao.save(exercise);
        }
        return workout;
    }
}
