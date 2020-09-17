package org.seeking.{{ project }}.service;

import org.seeking.{{ project }}.dao.{{ module.capitalize() }}Dao;
import org.seeking.{{ project }}.model.{{ module.capitalize() }};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class {{ module.capitalize() }}Service {
    @Autowired
    {{ module.capitalize() }}Dao {{ module }}dao;

    public List<{{ module.capitalize() }}> findAll() {
        return {{ module }}dao.findAll();
    }
}