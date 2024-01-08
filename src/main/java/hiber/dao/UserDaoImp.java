package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.util.List;

@Repository

public class UserDaoImp implements UserDao {




   @PersistenceContext
   private EntityManager entityManager;



   @Override
   public void add(User user) {
      entityManager.persist(user);
   }


   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      String jpql = "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.car";
      TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
      return query.getResultList();
   }

   @Override
   public void delete(Long user_id) {
      User user = listUsers().get(Math.toIntExact(user_id));
      if (user != null) {
         Car car = user.getCar();
         if (car != null) {
            user.setCar(null);
            car.setUser(null);
         }
      }
   }

   @Override
   @Transactional
   public List<User> getUserByModelSeries(String model, Integer series) {
      String hql = "SELECT u FROM User u JOIN u.car c WHERE c.model = :model AND c.series = :series";
      Query query = (Query) entityManager.createQuery(hql);
      query.setParameter("model", model);
      query.setParameter("series", series);

      return query.getResultList();
   }
   }


