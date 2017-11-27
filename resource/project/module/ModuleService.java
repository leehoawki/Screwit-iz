package com.movitech.{{ project.lower() }}.{{ module.lower() }}.service;

{% for entity in entities %}
import com.movitech.{{ project.lower() }}.base.entity.{{ entity }};
import com.movitech.{{ project.lower() }}.{{ module.lower() }}.dao.{{ entity }}Dao;
{% endfor %}
import com.movitech.{{ project.lower() }}.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class {{ module }}Service {

    @Autowired
    HelloRemote hello;

    {% for entity in entities %}
    @Autowired
    {{ entity }}Dao dao;

    public {{ entity }} get{{ entity }}(String id) {
        return dao.findOne(id);
    }

    public {{ entity }} add{{ entity }}({{ entity }} {{ entity.lower() }}) {
        return dao.save({{ entity.lower() }});
    }

    {% endfor %}
    public String hello() {
        return hello.hello("{{ module.lower() }}");
    }
}
