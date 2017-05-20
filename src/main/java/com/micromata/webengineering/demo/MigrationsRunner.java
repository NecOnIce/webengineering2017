package com.micromata.webengineering.demo;

import org.flywaydb.core.Flyway;

/**
 * This class is executed while the release-phase of the heroku deployment to execute the migrations.
 *
 * Created by Jonas Scherbaum on 20.05.2017.
 */
public class MigrationsRunner {

    public static void main(String[] args) throws Exception {
        Flyway flyway = new Flyway();
        flyway.setDataSource(System.getenv("JDBC_DATABASE_URL"),
                System.getenv("JDBC_DATABASE_USERNAME"),
                System.getenv("JDBC_DATABASE_PASSWORD"));
        flyway.baseline();
        flyway.migrate();
    }
}
