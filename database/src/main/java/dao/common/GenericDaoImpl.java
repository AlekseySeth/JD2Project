package dao.common;

import entity.util.IdentifiableEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import util.SessionFactoryManager;

import java.util.List;

/**
 * @author a.shestovsky
 */
public abstract class GenericDaoImpl<T extends IdentifiableEntity> implements GenericDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        this.entityClass = (Class<T>) GenericTypeResolver
                .resolveTypeArgument(getClass(), GenericDaoImpl.class);
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override

    public Long save(T entity) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        session.beginTransaction();
        Long id = (Long) session.save(entity);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    @Override
    public T findById(Long id) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        session.beginTransaction();
        T result = session.get(entityClass, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List<T> findAll() {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        session.beginTransaction();
        List<T> resultList = session
                .createQuery("select o from " + entityClass.getSimpleName() + " o", entityClass)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return resultList;
    }

    @Override
    public void update(T entity) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(T entity) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }
}
