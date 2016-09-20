package bo.edu.ucbcba.videoclub.controller;

import bo.edu.ucbcba.videoclub.dao.VideoClubEntityManager;
import bo.edu.ucbcba.videoclub.exceptions.ValidationException;
import bo.edu.ucbcba.videoclub.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.metamodel.Type;
import java.util.List;

/**
 * Created by privado on 30/05/2016.
 */
public class UserController {
    public void create(String username,
                       String password,int type) {

        User user = new User();

        //-----------validacion de longitud

        if(username.isEmpty())
            throw new ValidationException("Username can't be blank");

        if(password.isEmpty())
            throw new ValidationException("Password can't be blank");
        if(username.length() > 25)
            throw new ValidationException("Username is too long, must have less than 10 characters");

        if(username.length() < 4)
            throw new ValidationException("Username is too short, must have more than 4 characters");

        if(password.length() > 25)
            throw new ValidationException("Password is too long, must have less than 25 characters");

        if(password.length() < 6)
            throw new ValidationException("Password is too short, must have more than 6 characters");

        if (username.matches("[0-9]+")) {
            throw new ValidationException("Username can't be only a number, must have letters");
        }
        if(password.matches("[0-9]+")){
            throw new ValidationException("Password can't be only a number, must have letters");
        }
        if(password.matches("[a-zA-Z]")){
            throw new ValidationException("Password can't be only a letters, must have numbers");
        }
        else{
            EntityManager entityManager = VideoClubEntityManager.createEntityManager();
            TypedQuery<User> query = entityManager.createQuery("select u from User u WHERE u.username like :username", User.class);
            query.setParameter("username", username);
            List<User> response = query.getResultList();

            if(response.size() == 0){

                user.setUsername(username);
                user.setPassword(password);
                user.setUsertype(type);
                entityManager.getTransaction().begin();
                entityManager.persist(user);
                entityManager.getTransaction().commit();
                entityManager.close();

            }else {
                entityManager.close();
                throw new ValidationException("Username already exist");
            }
        }
    }

    public int getUsertype(String q) {
        EntityManager entityManager = VideoClubEntityManager.createEntityManager();
        TypedQuery<User> query = entityManager.createQuery("select u.usertype from User u WHERE u.username = :username AND u.usertype = :usertype", User.class);
        query.setParameter("username",q);
        query.setParameter("usertype",1);
        List<User> response = query.getResultList();
        if(response.size()==1){
            entityManager.close();
            return 1;
        }
        query.setParameter("username",q);
        query.setParameter("usertype",2);
        response = query.getResultList();
        if(response.size()==1){
            entityManager.close();
            return 2;
        }
        entityManager.close();
        return 0;
    }

    public boolean validateUser(String usr, String pswd){

        EntityManager entityManager = VideoClubEntityManager.createEntityManager();
        TypedQuery<User> query = entityManager.createQuery("select u from User u WHERE u.username like :username AND u.password like :password", User.class);
        query.setParameter("username", usr);
        query.setParameter("password", pswd);
        List<User> response = query.getResultList();
        entityManager.close();
        if (response.size() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> searchUser(String q) {
        EntityManager entityManager = VideoClubEntityManager.createEntityManager();
        TypedQuery<User> query = entityManager.createQuery("select u from User u WHERE lower(u.username) like :username AND u.usertype = 2", User.class);
        query.setParameter("username", "%" + q.toLowerCase() + "%");
        List<User> response = query.getResultList();
        entityManager.close();
        return response;
    }

    public int verifyExistenceofAdminAndStaff(){
        EntityManager entityManager = VideoClubEntityManager.createEntityManager();
        TypedQuery<User> query = entityManager.createQuery("select u from User u WHERE u.username like :username", User.class);
        query.setParameter("username", "admin");
        List<User> response = query.getResultList();
        TypedQuery<User> query2 = entityManager.createQuery("select u from User u WHERE u.username like :username", User.class);
        query2.setParameter("username", "staff");
        List<User> response2 = query.getResultList();
        entityManager.close();
        if(response.size()==0 && response2.size()==0 ){
            return 1;
        }
        if(response.size()==1 && response2.size()==0){
            return 2;
        }
        if(response.size()==0 && response2.size()==1){
            return 3;
        }
        else{

            return 4;
        }
    }

    public void ChangePassword(String username, String newpassword){
        if(newpassword.length() > 25)
            throw new ValidationException("Password is too long, must have less than 25 characters");
        if(newpassword.length() < 6)
            throw new ValidationException("Password is too short, must have more than 6 characters");
        if(newpassword.matches("[0-9]+")){
            throw new ValidationException("Password can't be only a number, must have letters");
        }
        else {
            EntityManager entityManager = VideoClubEntityManager.createEntityManager();
            entityManager.getTransaction().begin();
            TypedQuery<User> query = entityManager.createQuery("UPDATE User u SET u.password = :pswd WHERE u.username like :username ", User.class);
            query.setParameter("pswd", newpassword);
            query.setParameter("username", username);
            if(query.executeUpdate()==1){
                entityManager.getTransaction().commit();
                entityManager.close();
            }
            else
                entityManager.close();
        }
    }

    public int deleteUser(String q){
        EntityManager entityManager = VideoClubEntityManager.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            TypedQuery<User> query = entityManager.createQuery("select u from User u WHERE u.username = :username", User.class);
            query.setParameter("username", q);
            List<User> response = query.getResultList();
            if(response.size() == 0){
                entityManager.close();
                return 2;
            }
            else{
                query = entityManager.createQuery("delete from User u WHERE u.username = :username", User.class);
                query.setParameter("username", q);
                query.executeUpdate();
                entityManager.getTransaction().commit();
                entityManager.close();
                return 1;
            }
        } catch(Exception ex) {
            entityManager.getTransaction().rollback();
            return 2;
        }
    }
}
