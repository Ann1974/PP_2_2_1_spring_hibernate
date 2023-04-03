package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getUserHasCarByModelAndSeries(String model, int series) {
      User user =(User) sessionFactory.getCurrentSession()
              .createQuery(("from User where car.model = :model and car.series = :series"))
              .setParameter("model", model).setParameter("series", series)
              .uniqueResult(); // если результат будет в единственном варианте,
      // то  uniqueResult().
      return user;
   }


}
