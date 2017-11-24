package com.movitech.{{ project.lower() }}.{{ entity.lower() }}.dao;

import com.movitech.{{ project.lower() }}.base.entity.{{ entity }};
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface {{ entity }}Dao extends CrudRepository<{{ entity }}, String> {

}