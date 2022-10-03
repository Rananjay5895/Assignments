package com.example;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import jakarta.inject.Inject;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<UserDetail, Long> {

     List<UserDetail> findAll();
}
