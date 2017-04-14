package school.journal.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Component;
import school.journal.entity.User;
import school.journal.repository.IRepository;
import school.journal.repository.exception.RepositoryException;
import school.journal.repository.specification.HibernateSpecification;

import java.util.List;

@Component("UserRepository")
public class UserRepository implements IRepository<User> {

    @Override
    public User create(User user, Session session) throws RepositoryException {
        session.save(user);
        return user;
    }

    @Override
    public User update(User user, Session session) throws RepositoryException {
        session.update(user);
        return user;
    }

    @Override
    public User delete(User user, Session session) throws RepositoryException {
        session.delete(user);
        return user;
    }

    @Override
    public List<User> query(HibernateSpecification specification, Session session) throws RepositoryException {
        Criteria criteria =  session.createCriteria(User.class);
        Criterion criterion;
        if((specification != null) && ((criterion = specification.toCriteria()) != null)){
            criteria.add(criterion);
        }
        return criteria.list();
    }

    @Override
    public User get(int id, Session session) throws RepositoryException {
        User user = (User)session.get(User.class, id);
        if (user == null) throw new RepositoryException("User not found");
        return user;
    }
}
