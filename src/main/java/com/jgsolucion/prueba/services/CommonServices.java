package com.jgsolucion.prueba.services;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CommonServices<E> {

    List<E> findAll() throws ExecutionException, InterruptedException;

    E findById(String id) throws ExecutionException, InterruptedException;

    E save(E entity, String id) throws InterruptedException, ExecutionException;

    E save(E entity) throws InterruptedException, ExecutionException;

    E update(E entity) throws InterruptedException, ExecutionException;

    void deleteById(String id) throws InterruptedException, ExecutionException;
}
