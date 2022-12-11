package com.brunopbrito31.financecontrolapi.services.base;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<T> {

    List<T> getAll();

    Optional<T> getById(Integer id);

    T create(T object);

    void deleteById(Integer id);

    void delete(T object);

}
