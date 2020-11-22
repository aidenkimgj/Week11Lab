package dataaccess;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import models.User;


public class UserDB {
    
    private EntityManager em;
    private EntityTransaction trans;
            
    public User get(String username) {
        em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            User user = em.find(User.class, username);
            return user;
        } finally {
            em.close();
        }
    }
    
     public User getByEmail(String email) {
        em = DBUtil.getEmFactory().createEntityManager();
        
        String qString = "SELECT u FROM User u WHERE u.email = :email";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("email", email);
        try {
            User user = q.getSingleResult();
//            User user = em.find(User.class, email);
            System.out.println(user);
            return user;
        } finally {
            em.close();
        }
    }

    public void update(User user) throws Exception{
        em = DBUtil.getEmFactory().createEntityManager();
        trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            throw new Exception("Error updating user");
        } finally {
            em.close();
        }
    }

    public User getByUUID(String uuid) {
        em = DBUtil.getEmFactory().createEntityManager();
        
        String qString = "SELECT u FROM User u WHERE u.resetpasswordUUID = :resetpasswordUUID";
        TypedQuery<User> q = em.createQuery(qString, User.class);
        q.setParameter("resetpasswordUUID", uuid);
        try {
            User user = q.getSingleResult();
            return user;
        } finally {
            em.close();
        }
    }
}
