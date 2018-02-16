package by.nutrition.dao.common;

import by.nutrition.entity.util.IdentifiableEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author a.shestovsky
 */
@Repository
public abstract class GenericDaoImpl<T extends IdentifiableEntity> implements GenericDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        this.entityClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericDaoImpl.class);
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public Long save(T entity) {
        return (Long) sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public T findById(Long id) {
        return sessionFactory.getCurrentSession().get(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("select o from " + entityClass.getSimpleName() + " o", entityClass)
                .getResultList();
    }

    @Override
    public void update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }
}
