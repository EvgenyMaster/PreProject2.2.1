package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from User e");
        return (List<User>) query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User findOwner(String car_name, int car_series) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from User e where e.car.model = :car_name and e.car.series = :car_series")
                .setParameter("car_name", car_name)
                .setParameter("car_series", car_series);
        List<User> userList = query.getResultList();

        return userList.stream().findAny().orElse(null);
    }
}