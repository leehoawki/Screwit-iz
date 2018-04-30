package com.movitech.{{ project }}.core.{{ module }}.service;

import com.movitech.{{ project }}.core.{{ module }}.entity.{{ module.capitalize() }};
import com.movitech.{{ project }}.core.{{ module }}.dao.{{ module.capitalize() }}Dao;
import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class {{ module.capitalize() }}Service {
    @Autowired
    {{ module.capitalize() }}Dao {{ module }}dao;

    public {{ module.capitalize() }} get{{ module.capitalize() }}(String id) {
        return {{ module }}dao.findOne(id);
    }

    public {{ module.capitalize() }} add{{ module.capitalize() }}({{ module.capitalize() }} {{ module }}) {
        return {{ module }}dao.save({{ module }});
    }

    public List<{{ module.capitalize() }}> findAll() {
        return {{ module }}dao.findAll();
    }

    public List<{{ module.capitalize() }}> find({{ module.capitalize() }} {{ module }}, Pageable pageable) {
        Example<{{ module.capitalize() }}> example = Example.of({{ module }});
        return {{ module }}dao.findAll(example, pageable).getContent();
    }
}