package bo.edu.ucbcba.videoclub.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// This is a singleton that give us a entity manager to manage
// in the entire DAO
public class VideoClubEntityManager {
    private static VideoClubEntityManager entityManager;

    private EntityManagerFactory entityManagerFactory;

    private VideoClubEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("VideoClub");
    }

    private static VideoClubEntityManager getInstance() {
        if (entityManager == null)
            entityManager = new VideoClubEntityManager();
        return entityManager;
    }

    public static EntityManager createEntityManager() {
        return getInstance().entityManagerFactory.createEntityManager();
    }
}
