package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.print.DocFlavor;
import java.util.List;
@PersistenceContext
@Component
@Service
@Transactional
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;
   @PersistenceContext
   private EntityManager entityManager;


   @Override
   public void add(User user) {
      userDao.add(user);
   }




   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }
@Override
   public List<User> getUserByModelSeries(String model, Integer series) {
      return userDao.getUserByModelSeries(model, series);
   }

}


