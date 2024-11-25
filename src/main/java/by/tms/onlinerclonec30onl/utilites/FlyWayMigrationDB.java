package by.tms.onlinerclonec30onl.utilites;

import org.flywaydb.core.Flyway;

public class FlyWayMigrationDB {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/postgres", "postgres" , "root" )
                .schemas("public")
                .locations("classpath:db/migration")
                .load();
//        flyway.baseline();
        flyway.migrate();
    }
}
