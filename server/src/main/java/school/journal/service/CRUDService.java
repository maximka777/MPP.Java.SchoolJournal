package school.journal.service;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import school.journal.repository.IRepository;
import school.journal.repository.exception.RepositoryException;
import school.journal.service.exception.ServiceException;

import java.util.List;

public abstract class CRUDService<T> extends ServiceAbstractClass{
    protected IRepository<T> repository;
    protected static Logger LOGGER;

    public T create(T obj) throws ServiceException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            obj = repository.create(obj, session);
            transaction.commit();
        } catch (RepositoryException exc) {
            transaction.rollback();
            LOGGER.error(exc);
            throw new ServiceException(exc);
        } finally {
            session.close();
        }
        return obj;
    }


    public T update(T obj) throws ServiceException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            obj = repository.update(obj, session);
            transaction.commit();
        } catch (RepositoryException exc) {
            transaction.rollback();
            LOGGER.error(exc);
            throw new ServiceException(exc);
        } finally {
            session.close();
        }
        return obj;
    }

    public void delete(T obj) throws ServiceException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            repository.delete(obj, session);
            transaction.commit();
        } catch (RepositoryException exc) {
            transaction.rollback();
            LOGGER.error(exc);
            throw new ServiceException(exc);
        } finally {
            session.close();
        }
    }

    public List<T> read() throws ServiceException {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            List<T> list;
            try {
                list = repository.query(null, session);
                transaction.commit();
            } catch (RepositoryException exc) {
                transaction.rollback();
                LOGGER.error(exc);
                throw new ServiceException(exc);
            } finally {
                session.close();
            }
            return list;
    }

    public T getOne(int id) throws ServiceException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        T t ;
        try {
            t = repository.get(id,session);
            transaction.commit();
        } catch (RepositoryException exc) {
            transaction.rollback();
            LOGGER.error(exc);
            throw new ServiceException(exc);
        } finally {
            session.close();
        }
        return t;
    }


}
