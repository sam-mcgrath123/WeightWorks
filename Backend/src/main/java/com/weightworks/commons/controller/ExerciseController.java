package com.weightworks.commons.controller;

import com.weightworks.commons.dao.ExerciseDao;
import com.weightworks.commons.entity.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("weightworks/exercises")
public class ExerciseController {

    @Autowired
    ExerciseDao exerciseDao;

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getById(@PathVariable Integer id) {
        Optional<Exercise> optionalExercise = exerciseDao.findById(id);
        return optionalExercise.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.unprocessableEntity().build()
        );
    }

    @PostMapping
    public ResponseEntity<Exercise> create(@RequestBody Exercise exercise) {
        Exercise savedExercise = exerciseDao.save(exercise);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedExercise.getId()).toUri();
        return ResponseEntity.created(location).body(savedExercise);
    }
}
