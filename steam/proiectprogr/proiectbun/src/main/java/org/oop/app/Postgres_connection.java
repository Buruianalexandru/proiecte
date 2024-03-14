package org.oop.app;
import jakarta.persistence.*;

public class Postgres_connection {
    public static void main(String[] args) {
        //Comutare in persistence.xml
        // <property name="jakarta.persistence.schema-generation.database.action" value="drop-and-create"/>
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("proiectbun");
        EntityManager em = emf.createEntityManager();
        //
    }
}
