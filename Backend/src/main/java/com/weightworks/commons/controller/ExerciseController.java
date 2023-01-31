package com.weightworks.commons.controller;

import com.weightworks.commons.dao.ExerciseDao;
import com.weightworks.commons.entity.Exercise;
import com.weightworks.commons.entity.Routine;
import com.weightworks.commons.entity.Set;
import com.weightworks.commons.entity.Workout;
import com.weightworks.commons.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.weightworks.commons.values.UrlString.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/exercises")
public class ExerciseController {

    @Autowired
    ExerciseDao exerciseDao;

    @Autowired
    ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<Exercise> create(@RequestBody Exercise exercise) {
        Exercise savedExercise = exerciseDao.save(exercise);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedExercise.getId()).toUri();
        return ResponseEntity.created(location).body(savedExercise);
    }

    @PutMapping("/routines/{routine_id}")
    public ResponseEntity<Routine> updateRoutines(@PathVariable Integer routine_id, @RequestBody List<Integer> exercises) {
        Routine routine = exerciseService.updateExerciseRoutines(routine_id, exercises);
        if (routine == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(routine.getId()).toUri();
        return ResponseEntity.created(location).body(routine);
    }

    @PutMapping("/workouts/{workout_id}")
    public ResponseEntity<Workout> updateWorkouts(@PathVariable Integer workout_id, @RequestBody List<Integer> exercises) {
        Workout workout = exerciseService.updateExerciseWorkouts(workout_id, exercises);
        if (workout == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(workout.getId()).toUri();
        return ResponseEntity.created(location).body(workout);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getById(@PathVariable Integer id) {
        Optional<Exercise> optionalExercise = exerciseDao.findById(id);
        return optionalExercise.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.unprocessableEntity().build()
        );
    }

    @GetMapping("/{id}/sets")
    public ResponseEntity<java.util.Set<Set>> getAllSets(@PathVariable Integer id) {
        Optional<Exercise> optionalExercise = exerciseDao.findById(id);
        if (optionalExercise.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalExercise.get().getSets());
    }

    @GetMapping
    public ResponseEntity<ArrayList<Exercise>> getAll() {
        ArrayList<Exercise> allExercises = new ArrayList<>();
        exerciseDao.findAll().forEach(allExercises::add);
        return ResponseEntity.ok(allExercises);
    }
}
