package com.weightworks.commons.controller;

import com.weightworks.commons.entity.Exercise;
import com.weightworks.commons.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    @GetMapping("/getExercise")
    public Exercise getExercise(@RequestBody Exercise exercise) {
        return exerciseService.getExercise(exercise);
    }

    @PostMapping("/addExercise")
    public Exercise addExercise(@RequestBody Exercise exercise) {
        return exerciseService.insertIntoDatabase(exercise);
    }
}
