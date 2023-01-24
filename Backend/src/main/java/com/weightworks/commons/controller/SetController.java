package com.weightworks.commons.controller;

import com.weightworks.commons.dao.ExerciseDao;
import com.weightworks.commons.dao.SetDao;
import com.weightworks.commons.entity.Exercise;
import com.weightworks.commons.entity.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

import static com.weightworks.commons.values.UrlString.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "sets")
public class SetController {

    @Autowired
    ExerciseDao exerciseDao;

    @Autowired
    SetDao setDao;

    @GetMapping("{id}")
    public ResponseEntity<Set> getById(@PathVariable Integer id) {
        Optional<Set> optionalSet = setDao.findById(id);
        return optionalSet.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.unprocessableEntity().build()
        );
    }

    @PostMapping
    public ResponseEntity<Set> create(@RequestBody Set set) {
        Optional<Exercise> optionalExercise = exerciseDao.findById(set.getExercise().getId());
        if (optionalExercise.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        set.setExercise(optionalExercise.get());

        Set savedSet = setDao.save(set);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedSet.getId()).toUri();

        return ResponseEntity.created(location).body(savedSet);
    }
}
