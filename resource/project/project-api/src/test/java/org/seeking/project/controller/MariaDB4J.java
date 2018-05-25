package org.seeking.{{ project }}.controller;


import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;

public class MariaDB4J {
    public static DB db;

    public static void setup() {
        if (db == null) {
            synchronized (MariaDB4J.class) {
                if (db == null) {
                    try {
                        DBConfigurationBuilder configBuilder = DBConfigurationBuilder.newBuilder();
                        configBuilder.setPort(3306);
                        configBuilder.addArg("--user=root");
                        db = DB.newEmbeddedDB(configBuilder.build());
                        db.start();
                        db.source("test.sql");
                    } catch (ManagedProcessException e) {
                        throw new IllegalStateException(e);
                    }
                }
            }
        }
    }
}
