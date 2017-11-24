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

    @Version
    private Timestamp ts;

    {% for field in fields %}
    private {{ type }} {{ field.lower() }};

    public {{ type }} get{{ field }}() {
        return id;
    }

    public void set{{ field }}({{ type }} field.lower()) {
        this.{{ field.lower() }} = {{ field.lower() }};
    }

    {% endfor %}

    public Timestamp getTs() {
        return ts;
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "{{ entity }}{" +
                "id='" + id + '\'' +
                {% for field in fields %}
                ", {{ field.lower() }}='" + {{ field.lower() }} + "'" +
                {% endfor %}
                ", ts=" + ts +
                '}';
    }
}