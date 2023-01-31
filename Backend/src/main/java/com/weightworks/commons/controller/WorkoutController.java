package com.weightworks.commons.controller;

import com.weightworks.commons.dao.WorkoutDao;
import com.weightworks.commons.entity.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import static com.weightworks.commons.values.UrlString.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutDao workoutDao;

    @PostMapping
    public ResponseEntity<Workout> create(@RequestBody Workout workout) {
        Workout savedWorkout = workoutDao.save(workout);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedWorkout.getId()).toUri();
        return ResponseEntity.created(location).body(savedWorkout);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workout> getById(@PathVariable Integer id) {
        Optional<Workout> optionalWorkout = workoutDao.findById(id);
        return optionalWorkout.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.unprocessableEntity().build()
        );
    }

    @GetMapping
    public ResponseEntity<ArrayList<Workout>> getAll() {
        ArrayList<Workout> allWorkouts = new ArrayList<>();
        workoutDao.findAll().forEach(allWorkouts::add);
        return ResponseEntity.ok(allWorkouts);
    }
}
