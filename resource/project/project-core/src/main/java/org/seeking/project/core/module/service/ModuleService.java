package org.seeking.{{ project }}.core.{{ module }}.service;

import org.seeking.{{ project }}.core.{{ module }}.entity.{{ module.capitalize() }};
import org.seeking.{{ project }}.core.{{ module }}.dao.{{ module.capitalize() }}Dao;
import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class {{ module.capitalize() }}Service {
    @Autowired
    {{ module.capitalize() }}Dao {{ module }}dao;

    public {{ module.capitalize() }} get{{ module.capitalize() }}(int id) {
        return {{ module }}dao.findOne(id);
    }

    public {{ module.capitalize() }} add({{ module.capitalize() }} {{ module }}) {
        return {{ module }}dao.save({{ module }});
    }

    public {{ module.capitalize() }} update({{ module.capitalize() }} {{ module }}) {
        return {{ module }}dao.save({{ module }});
    }

    public Page<{{ module.capitalize() }}> find({{ module.capitalize() }} {{ module }}, Pageable pageable) {
        if ({{ module }} == null) return {{ module }}dao.findAll(pageable);
        Example<{{ module.capitalize() }}> example = Example.of({{ module }});
        return {{ module }}dao.findAll(example, pageable);
    }

    public int delete(int id) {
        {{ module }}dao.delete(id);
        return id;
    }
}