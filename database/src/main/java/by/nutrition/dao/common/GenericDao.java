package by.nutrition.dao.common;

import by.nutrition.entity.util.IdentifiableEntity;

import java.util.List;

/**
 * @author a.shestovsky
 */
public interface GenericDao<T extends IdentifiableEntity> {

    Long save(T entity);

    T findById(Long id);

    List<T> findAll();

    void update(T entity);

    void delete(T entity);
}