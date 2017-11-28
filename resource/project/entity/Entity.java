package com.movitech.{{ project.lower() }}.base.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.sql.Timestamp;

@Entity
@Table(name = "{{ entity.lower() }}")
public class {{ entity }} {
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    {% for type, name in fields %}
    private {{ type }} {{ name.lower() }};

    public {{ type }} get{{ name }}() {
        return {{ name.lower() }};
    }

    public void set{{ name }}({{ type }} {{ name.lower() }}) {
        this.{{ name.lower() }} = {{ name.lower() }};
    }
    {% endfor %}
    @Override
    public String toString() {
        return "{{ entity }}{" +
                "id='" + id + '\'' +
                {% for type, name in fields %}
                ", {{ name.lower() }}='" + {{ name.lower() }} + "'" + {% endfor %}
                '}';
    }
}