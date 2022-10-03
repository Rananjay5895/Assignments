package com.example;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Controller("/crud")
public class Runner {

  public static final Logger log = LoggerFactory.getLogger(Runner.class);
   private final UserService service;

    public Runner(UserService service) {
        this.service = service;
    }


    @Post
    public UserDetail save(){
        UserDetail user = new UserDetail("Rananjay");
        return service.save(user);
    }
    @Get
    public UserDetail hsave(){
        UserDetail user = new UserDetail("Rananjay");
        return service.save(user);
    }

    @Get("/all/filter{?max,offset}")
    public List<UserDetail> findAll(@QueryValue Optional<Integer> max ,@QueryValue Optional<Integer> offset) {
  //      List<UserDetail> userDetails1 = service.findAll();
       return service.findAll().stream().skip(offset.orElse(0)).limit(max.orElse(0)).toList();

    }

    @Get(uri = "/{id}")
    public Optional<UserDetail> getById(@PathVariable Long id){
        return service.findById(id);
    }

    @Put("/update")
    public List<UserDetail> updateUser(){
        return service.updateUser();
    }

    @Get(uri="/update/{id}/{name}" , produces = MediaType.TEXT_PLAIN)
    public List<UserDetail> updateUserById(@PathVariable Long id , @PathVariable String name){
        return service.updateUserNameById(id , name);
    }

    @Put(uri="/update/body" , produces = MediaType.APPLICATION_JSON)
    public List<UserDetail> updateUserByRequestBody(@Body @NotNull UserDetail userDetail){
        System.out.println(userDetail.getId());
        System.out.println(userDetail.getName());
        return service.updateUserNameById(userDetail.getId(), userDetail.getName());
    }
    @Post("/delete")
    public List<UserDetail> deleteUser(){
        return service.delete();
    }
}
