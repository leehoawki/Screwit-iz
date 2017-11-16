package com.movitech.{{ project }}.{{ module.lower() }}.service;

import com.movitech.{{ project }}.base.entity.{{ module }};
import com.movitech.{{ project }}.base.remote.Hello;
import com.movitech.{{ project }}.{{ module.lower() }}.dao.{{ module }}Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class {{ module }}Service {

    @Autowired
    {{ module }}Dao dao;

    @Autowired
    Hello hello;

    public {{ module }} get{{ module }}(String id) {
        return dao.findOne(id);
    }

    public {{ module }} add{{ module }}({{ module }} {{ module.lower() }}) {
        return dao.save({{ module.lower() }});
    }

    public String hello() {
        return hello.hello("{{ module.lower() }}");
    }
}
