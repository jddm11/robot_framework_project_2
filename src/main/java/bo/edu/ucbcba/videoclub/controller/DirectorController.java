package bo.edu.ucbcba.videoclub.controller;

import bo.edu.ucbcba.videoclub.dao.VideoClubEntityManager;
import bo.edu.ucbcba.videoclub.exceptions.ValidationException;
import bo.edu.ucbcba.videoclub.model.Director;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by USER on 28/05/2016.
 */
public class DirectorController {
    public  void  saveDirector(String Fname , String Lname)
    {
        EntityManager em = VideoClubEntityManager.createEntityManager();
        em.getTransaction().begin();
        Director director = new Director();
      // validacion en blancos
        if(Fname.isEmpty())
        {
            throw new ValidationException("First Name can't be blank");
        }

        if(Lname.isEmpty())
        {
            throw new ValidationException("Last Name can't be blank");
        }
        // valiadcion de longitud

        if (Fname.length() > 25)
        {
            throw new ValidationException("First Name is too long, must have less than 25 characters");
        }
        if (Lname.length() > 25)
        {
            throw new ValidationException("Last Name is too long, must have less than 25 characters");
        }

       if(Validar(Fname,Lname)>0) {
            throw new ValidationException("director already exists");
        }
        director.setFirstName(Fname);
        director.setLastName(Lname);
        em.persist(director);
        em.getTransaction().commit();
        em.close();

    }

    public int Validar(String Fname , String Lname) {
        EntityManager entityManager = VideoClubEntityManager.createEntityManager();
        TypedQuery<Director> query = entityManager.createQuery("SELECT c FROM Director c WHERE lower(c.lastName) = :lname AND lower(c.firstName) = :fname ", Director.class);
        query.setParameter("fname", Fname.toLowerCase());
        query.setParameter("lname",Lname.toLowerCase());
        List<Director> response = query.getResultList();
        int a=response.size();
        entityManager.close();
        return a;
    }

    /*public List<Director> searchDirector(String q) {
        EntityManager entityManager = VideoClubEntityManager.createEntityManager();
        TypedQuery<Director> query = entityManager.createQuery("select c from Client c WHERE lower(c.ci) = :ci", Client.class);
        query.setParameter("ci", "%" + q.toLowerCase() + "%");
        List<Client> response = query.getResultList();
        entityManager.close();
        return response;
    }*/


    public List<Director> getAlldirectors()
    {
        EntityManager em = VideoClubEntityManager.createEntityManager();
        TypedQuery<Director> query = em.createQuery("select d from Director d order by d.firstName",Director.class);
        List<Director> list = query.getResultList();
        em.close();
        return list;
    }
    public List<Director> searchDirector(String q) {
        EntityManager entityManager = VideoClubEntityManager.createEntityManager();
        TypedQuery<Director> query = entityManager.createQuery("select m from Director m WHERE lower(concat(m.firstName,m.lastName)) like :title", Director.class);
        query.setParameter("title", "%" + q.toLowerCase() + "%");
        List<Director> response = query.getResultList();
        entityManager.close();
        return response;
    }

    public Director getDirector(String Fname , String Lname)
    {
            EntityManager entityManager = VideoClubEntityManager.createEntityManager();
            TypedQuery query = entityManager.createQuery("select m.id from Director m WHERE lower(m.firstName) like :codigo and lower(m.lastName) like :lastname", Director.class);
            query.setParameter("codigo",Fname.toLowerCase());
            query.setParameter("lastname",Lname.toLowerCase());
            entityManager.getTransaction().begin();
            Director response = entityManager.find(Director.class,query.getSingleResult());
            entityManager.getTransaction().commit();
            entityManager.close();
            return response;
    }

    public void update(int id,String Fname,String Lname)
    {
        EntityManager entityManager = VideoClubEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        Director director = entityManager.find(Director.class, id);

        if(Fname.isEmpty())
        {
            throw new ValidationException("First Name can't be blank");
        }

        if(Lname.isEmpty())
        {
            throw new ValidationException("Last Name can't be blank");
        }
        // valiadcion de longitud

        if (Fname.length() > 25)
        {
            throw new ValidationException("First Name is too long, must have less than 25 characters");
        }
        if (Lname.length() > 25)
        {
            throw new ValidationException("Last Name is too long, must have less than 25 characters");
        }

        if(Validar(Fname,Lname)>0) {
            throw new ValidationException("the director already exists or no changes were detected");
        }
        director.setFirstName(Fname);
        director.setLastName(Lname);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}

