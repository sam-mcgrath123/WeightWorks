package com.weightworks.commons.dao;

import com.weightworks.commons.entity.Set;
import org.springframework.data.repository.CrudRepository;

public interface SetDao extends CrudRepository<Set, Long> {

    Set findById(int id);
}
