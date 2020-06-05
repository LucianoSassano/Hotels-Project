package com.example.demo.repository;

import com.example.demo.model.City;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CityRepository extends JpaRepository<City,Integer> {

    @Override
    default List<City> findAll() {
        return null;
    }

    @Override
    default List<City> findAll(Sort sort) {
        return null;
    }

    @Override
    default List<City> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    default <S extends City> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    default void flush() {

    }

    @Override
    default <S extends City> S saveAndFlush(S s) {
        return null;
    }

    @Override
    default void deleteInBatch(Iterable<City> iterable) {

    }

    @Override
    default void deleteAllInBatch() {

    }

    @Override
    default City getOne(Integer integer) {
        return null;
    }

    @Override
    default <S extends City> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    default <S extends City> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    default Page<City> findAll(Pageable pageable) {
        return null;
    }

    @Override
    default <S extends City> S save(S s) {
        return null;
    }

    @Override
    default Optional<City> findById(Integer integer) {
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
    default void delete(City city) {

    }

    @Override
    default void deleteAll(Iterable<? extends City> iterable) {

    }

    @Override
    default void deleteAll() {

    }

    @Override
    default <S extends City> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    default <S extends City> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    default <S extends City> long count(Example<S> example) {
        return 0;
    }

    @Override
    default <S extends City> boolean exists(Example<S> example) {
        return false;
    }
}
