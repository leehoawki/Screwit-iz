package org.seeking.{{ project }}.dao;

import org.springframework.stereotype.Repository;
import org.seeking.{{ project }}.model.{{ module.capitalize() }};

import java.util.List;

@Repository
public interface {{ module.capitalize() }}Dao {

    List<{{ module.capitalize() }}> findAll();
}