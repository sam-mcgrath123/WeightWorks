package com.weightworks.commons.dao;

import com.weightworks.commons.entity.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetDao extends CrudRepository<Set, Integer> {
}
