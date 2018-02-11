package dao;

import entity.util.IdentifiableEntity;
import org.hibernate.Session;
import util.SessionFactoryManager;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author a.shestovsky
 */
public abstract class GenericDao<T extends IdentifiableEntity> {

    private Class<T> entityClass;

    public GenericDao() {
        ParameterizedType parameterizedSuperclass
                = (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class<T>) parameterizedSuperclass.getActualTypeArguments()[0];
    }

    public Long save(T entity) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        session.beginTransaction();
        Long id = (Long) session.save(entity);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public T findById(Long id) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        session.beginTransaction();
        T result = session.get(entityClass, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }

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

    public void update(T entity) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(T entity) {
        Session session = SessionFactoryManager.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }
}
