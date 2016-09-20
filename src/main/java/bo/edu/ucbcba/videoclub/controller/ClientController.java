package bo.edu.ucbcba.videoclub.controller;

import bo.edu.ucbcba.videoclub.dao.VideoClubEntityManager;
import bo.edu.ucbcba.videoclub.exceptions.ControllerException;
import bo.edu.ucbcba.videoclub.exceptions.ValidationException;
import bo.edu.ucbcba.videoclub.model.Client;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.*;
import java.util.List;

/**
 * Created by privado on 16/05/2016.
 */
public class ClientController {
    public void create(String ci,
                       String firstname,
                       String lastname,
                       String address) {

        Client client = new Client();

        //------------------Validacion blancos
        if (ci.isEmpty()){
            throw new ValidationException("CI can't be blank");
        }
        if (firstname.isEmpty()){
            throw new ValidationException("First Name can't be blank");
        }
        if (lastname.isEmpty()){
            throw new ValidationException("Last Name can't be blank");
        }
        if (address.isEmpty()){
            throw new ValidationException("Address can't be blank");
        }

        //-----------validacion de longitud

        if(ci.length() > 10)
            throw new ValidationException("CI can't have more than 10 characters");
        if(ci.length() < 7)
        	throw new ValidationException("CI can't have less than 7 characters");

        if(firstname.length() > 25)
            throw new ValidationException("First Name is too long, must have less than 25 characters");
        if(firstname.length() < 2)
            throw new ValidationException("First Name is too short, must have more than 2 characters");

        if(lastname.length() > 25)
            throw new ValidationException("Last Name is too long, must have less than 25 characters");
        if(lastname.length() < 2)
            throw new ValidationException("Last Name is too short, must have more than 2 characters");
        if(address.length() > 100)
            throw new ValidationException("Address is too long, must have less than 100 characters");

        //-------------Validacion cliente
        if (!ci.matches("[0-9]+")) {
            throw new ValidationException("Ci isn't a number");
        }
        else{
            if(firstname.matches("[0-9]+"))
                throw new ValidationException("First name can't be a number");
            else{
                if(lastname.matches("[0-9]+")){
                    throw new ValidationException("Last name can't be a number");
                }
                else{
                    if(address.matches("[0-9]+"))
                        throw new ValidationException("Address can't be only a number");
                    else{
                        EntityManager entityManager = VideoClubEntityManager.createEntityManager();
                        TypedQuery<Client> query = entityManager.createQuery("select c from Client c WHERE lower(c.ci) like :ci", Client.class);
                        query.setParameter("ci", ci);
                        List<Client> response = query.getResultList();

                        if(response.size() == 0){
                            client.setCi(ci);
                            client.setFirstname(firstname);
                            client.setLastname(lastname);
                            client.setAddress(address);

                            entityManager.getTransaction().begin();
                            entityManager.persist(client);
                            entityManager.getTransaction().commit();
                            entityManager.close();
                        }else {
                            entityManager.close();
                            throw new ValidationException("Already exist a Client with CI: '" + ci +"'");
                        }
                    }
                }
            }
        }
    }


    public List<Client> searchClient(String q) {
        EntityManager entityManager = VideoClubEntityManager.createEntityManager();
        TypedQuery<Client> query = entityManager.createQuery("select c from Client c WHERE lower(c.lastname) like :search OR lower(c.firstname) like :search", Client.class);
        query.setParameter("search", "%" + q.toLowerCase() + "%");
        List<Client> response = query.getResultList();
        entityManager.close();
        return response;
    }

    public int deleteClient(String q){
        EntityManager entityManager = VideoClubEntityManager.createEntityManager();
        try {

            entityManager.getTransaction().begin();
            TypedQuery<Client> query = entityManager.createQuery("select c from Client c WHERE lower(c.ci) like :ci", Client.class);
            query.setParameter("ci", q);
            List<Client> response = query.getResultList();

            if(response.size() == 0){
                entityManager.close();
                return 2;
            }
            else{

                query = entityManager.createQuery("delete from Client c WHERE lower(c.ci) like :ci", Client.class);
                query.setParameter("ci", q);
                query.executeUpdate();
                entityManager.getTransaction().commit();
                entityManager.close();
                return 1;
            }

        } catch(Exception ex) {
            entityManager.getTransaction().rollback();
            return 2; // let upper methods know this did not go well
        }
    }


}
