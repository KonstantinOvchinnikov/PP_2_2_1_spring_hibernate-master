package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User ");
        return query.getResultList();
    }

    @Override
    public void cleanUsersTable() {
        sessionFactory.getCurrentSession().createQuery("delete from User").executeUpdate();
    }

    public User getUserWhithCar(String model, int series) {
        String hql = "from User where car.model =: model and car.series =: series ";

        return (User) sessionFactory.getCurrentSession()
                .createQuery(hql)
                .setParameter("model", model)
                .setParameter("series", series)
                .getSingleResult();
    }

}
