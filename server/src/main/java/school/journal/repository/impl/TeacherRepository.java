package school.journal.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;
import school.journal.entity.Teacher;
import school.journal.entity.User;
import school.journal.repository.RepositoryAbstractClass;
import school.journal.repository.exception.RepositoryException;
import school.journal.repository.specification.HibernateSpecification;

import java.util.List;

@Component("TeacherRepository")
public class TeacherRepository extends RepositoryAbstractClass<Teacher> {
    @Override
    public Teacher create(Teacher teacher, Session session) throws RepositoryException {
        session.save(teacher);
        return teacher;
    }

    @Override
    public Teacher update(Teacher teacher, Session session) throws RepositoryException {
        session.update(teacher);
        return teacher;
    }

    @Override
    public Teacher delete(Teacher teacher, Session session) throws RepositoryException {
        session.delete(teacher);
        return teacher;
    }

    @Override
    public List<Teacher> query(HibernateSpecification specification, Session session) throws RepositoryException {
        Criteria criteria =  session.createCriteria(Teacher.class);
        Criterion criterion;
        if((specification != null) && ((criterion = specification.toCriteria()) != null)){
            criteria.add(criterion);
        }
        criteria.addOrder(Order.asc("lastName")).addOrder(Order.asc("firstName")).addOrder(Order.asc("pathronymic"));
        return criteria.list();
    }

    @Override
    public Teacher get(int id, Session session) throws RepositoryException {
        Teacher teacher = (Teacher)session.get(Teacher.class, id);
        if (teacher == null) throw new RepositoryException("Teacher not found");
        return teacher;
    }
}
