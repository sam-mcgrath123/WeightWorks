package com.weightworks.commons.controller;

import com.weightworks.commons.dao.ExerciseDao;
import com.weightworks.commons.entity.Exercise;
import com.weightworks.commons.entity.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.print.Pageable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("weightworks/exercises")
public class ExerciseController {

    @Autowired
    ExerciseDao exerciseDao;

    @PostMapping
    public ResponseEntity<Exercise> create(@RequestBody Exercise exercise) {
        Exercise savedExercise = exerciseDao.save(exercise);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedExercise.getId()).toUri();
        return ResponseEntity.created(location).body(savedExercise);
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
        if(optionalExercise.isEmpty()) {
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
