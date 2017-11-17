package com.movitech.{{ project.lower() }}.{{ module.lower() }}.dao;

import com.movitech.{{ project.lower() }}.base.entity.{{ module }};
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface {{ module }}Dao extends CrudRepository<{{ module }}, String> {

}