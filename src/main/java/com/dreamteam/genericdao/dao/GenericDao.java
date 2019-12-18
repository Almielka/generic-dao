package com.dreamteam.genericdao.dao;

import java.util.List;
import java.util.Optional;

/**
 * Generic Dao interface with CRUD methods
 *
 * @author Anna S. Almielka
 */

public interface GenericDao<T> {
    void create(T newInstance);
    Optional<T> read(Long id);
    List<T> readAll();
    void update(T transientObject);
    void delete(T persistentObject);
}
