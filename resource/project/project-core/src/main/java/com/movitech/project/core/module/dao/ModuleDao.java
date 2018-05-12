package com.movitech.{{ project }}.core.{{ module }}.dao;

import com.movitech.{{ project }}.core.{{ module }}.entity.{{ module.capitalize() }};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Transactional
@Repository
public interface {{ module.capitalize() }}Dao extends JpaRepository<{{ module.capitalize() }}, Integer> {

}