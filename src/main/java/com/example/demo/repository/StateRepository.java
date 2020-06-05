package com.example.demo.repository;

import com.example.demo.model.Estate;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<Estate,Integer> {

    @Override
    default List<Estate> findAll() {
        return null;
    }

    @Override
    default List<Estate> findAll(Sort sort) {
        return null;
    }

    @Override
    default List<Estate> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    default <S extends Estate> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    default void flush() {

    }

    @Override
    default <S extends Estate> S saveAndFlush(S s) {
        return null;
    }

    @Override
    default void deleteInBatch(Iterable<Estate> iterable) {

    }

    @Override
    default void deleteAllInBatch() {

    }

    @Override
    default Estate getOne(Integer integer) {
        return null;
    }

    @Override
    default <S extends Estate> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    default <S extends Estate> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    default Page<Estate> findAll(Pageable pageable) {
        return null;
    }

    @Override
    default <S extends Estate> S save(S s) {
        return null;
    }

    @Override
    default Optional<Estate> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    default boolean existsById(Integer integer) {
        return false;
    }

    @Override
    default long count() {
        return 0;
    }

    @Override
    default void deleteById(Integer integer) {

    }

    @Override
    default void delete(Estate estate) {

    }

    @Override
    default void deleteAll(Iterable<? extends Estate> iterable) {

    }

    @Override
    default void deleteAll() {

    }

    @Override
    default <S extends Estate> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    default <S extends Estate> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    default <S extends Estate> long count(Example<S> example) {
        return 0;
    }

    @Override
    default <S extends Estate> boolean exists(Example<S> example) {
        return false;
    }
}
