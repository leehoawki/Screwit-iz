package com.movitech.{{ project }}.core.{{ module }}.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "{{ module }}")
public class {{ module.capitalize() }} {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    {% for type, name in fields %}
    private {{ type }} {{ name }};

    public {{ type }} get{{ name }}() {
        return {{ name }};
    }

    public void set{{ name }}({{ type }} {{ name }}) {
        this.{{ name }} = {{ name }};
    }
    {% endfor %}
    @Override
    public String toString() {
        return "{{ module }}{" +
                "id='" + id + '\'' +
                {% for type, name in fields %}", {{ name }}='" + {{ name }} + "'" + {% endfor %}
                '}';
    }
}