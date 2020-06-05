package com.example.demo.repository;

import com.example.demo.model.Country;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {

    @Override
    default List<Country> findAll() {
        return null;
    }

    @Override
    default List<Country> findAll(Sort sort) {
        return null;
    }

    @Override
    default List<Country> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    default <S extends Country> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    default void flush() {

    }

    @Override
    default <S extends Country> S saveAndFlush(S s) {
        return null;
    }

    @Override
    default void deleteInBatch(Iterable<Country> iterable) {

    }

    @Override
    default void deleteAllInBatch() {

    }

    @Override
    default Country getOne(Integer integer) {
        return null;
    }

    @Override
    default <S extends Country> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    default <S extends Country> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    default Page<Country> findAll(Pageable pageable) {
        return null;
    }

    @Override
    default <S extends Country> S save(S s) {
        return null;
    }

    @Override
    default Optional<Country> findById(Integer integer) {
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
    default void delete(Country country) {

    }

    @Override
    default void deleteAll(Iterable<? extends Country> iterable) {

    }

    @Override
    default void deleteAll() {

    }

    @Override
    default <S extends Country> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    default <S extends Country> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    default <S extends Country> long count(Example<S> example) {
        return 0;
    }

    @Override
    default <S extends Country> boolean exists(Example<S> example) {
        return false;
    }
}
