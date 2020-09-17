package org.seeking.{{ project }}.model;

public class {{ module.capitalize() }} {
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
                {% for type, name in fields %}", {{ name }}='" + {{ name }} + "'" + {% endfor %}'}';
    }
}