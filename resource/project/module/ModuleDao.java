package com.movitech.{{ project }}.box.dao;

import com.movitech.{{ project }}.base.entity.Box;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface {{ module }}Dao extends CrudRepository<{{ module }}, String> {

}