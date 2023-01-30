package com.weightworks.commons.controller;

import com.weightworks.commons.dao.ExerciseDao;
import com.weightworks.commons.dao.RoutineDao;
import com.weightworks.commons.entity.Routine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import static com.weightworks.commons.values.UrlString.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/routines")
public class RoutineController {

    @Autowired
    RoutineDao routineDao;

    @Autowired
    ExerciseDao exerciseDao;

    @PostMapping
    public ResponseEntity<Routine> create(@RequestBody Routine routine) {
        Routine savedRoutine = routineDao.save(routine);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedRoutine.getId()).toUri();
        return ResponseEntity.created(location).body(savedRoutine);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Routine> getById(@PathVariable Integer id) {
        Optional<Routine> optionalRoutine = routineDao.findById(id);
        return optionalRoutine.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.unprocessableEntity().build()
        );
    }

    @GetMapping
    public ResponseEntity<ArrayList<Routine>> getAll() {
        ArrayList<Routine> allRoutines = new ArrayList<>();
        routineDao.findAll().forEach(allRoutines::add);
        return ResponseEntity.ok(allRoutines);
    }
}