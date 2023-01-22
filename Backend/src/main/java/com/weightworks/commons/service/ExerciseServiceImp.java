package com.weightworks.commons.service;

import com.weightworks.commons.dao.ExerciseDao;
import com.weightworks.commons.entity.Exercise;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImp implements ExerciseService {
    private final EntityManager entityManager;

    @Autowired
    public ExerciseServiceImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    private ExerciseDao exerciseDao;

    @Override
    public Exercise getExercise(Exercise exerciseDetails) {
        TypedQuery<Exercise> typedQuery = entityManager.createQuery("FROM Exercise WHERE name = :name AND type = :type", Exercise.class);
        try {
            Exercise exercise = typedQuery.setParameter("name", exerciseDetails.getName()).setParameter("type", exerciseDetails.getType()).getSingleResult();
            return exercise;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Exercise insertIntoDatabase(Exercise exercise) {
        return exerciseDao.save(exercise);
    }
}
