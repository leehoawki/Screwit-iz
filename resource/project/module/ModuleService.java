package com.movitech.{{ project.lower() }}.{{ module.lower() }}.service;

{% for entity in entities %}
import com.movitech.{{ project.lower() }}.base.entity.{{ entity }};
import com.movitech.{{ project.lower() }}.{{ module.lower() }}.dao.{{ entity }}Dao;
{% endfor %}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class {{ module }}Service {

    {% for entity in entities %}
    @Autowired
    {{ entity }}Dao {{ entity.lower() }}dao;

    public {{ entity }} get{{ entity }}(String id) {
        return {{ entity.lower() }}dao.findOne(id);
    }

    public {{ entity }} add{{ entity }}({{ entity }} {{ entity.lower() }}) {
        return {{ entity.lower() }}dao.save({{ entity.lower() }});
    }
    {% endfor %}
}
