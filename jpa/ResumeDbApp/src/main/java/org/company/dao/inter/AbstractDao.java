package org.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractDao {

    public static Connection connect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "kmrn1708";
        return DriverManager.getConnection(url, username, password);
    }
    private static EntityManagerFactory entityManagerFactory = null;

    public EntityManager em() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("resumeappPU");
        }
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

    public void closeEmf() {
        entityManagerFactory.close();
    }
}
