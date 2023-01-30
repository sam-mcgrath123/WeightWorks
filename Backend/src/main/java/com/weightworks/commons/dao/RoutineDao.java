package com.weightworks.commons.dao;

import com.weightworks.commons.entity.Routine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineDao extends CrudRepository<Routine, Integer> {
}