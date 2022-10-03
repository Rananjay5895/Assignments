package com.example;

import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class UserService {

   private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public UserDetail save(UserDetail user){
        return repo.save(user);
    }

    public List<UserDetail> findAll() {
        System.out.println("check this is working");
        return repo.findAll();
    }

    public List<UserDetail> updateUser() {
        UserDetail userDetail = null;
        Optional<UserDetail> user = repo.findById(1L);
        if(user.isPresent()) {
            userDetail = user.get();
        }
        else throw new RuntimeException("user not available");
        userDetail.setName("New Name");
        repo.update(userDetail);
        return repo.findAll();
    }

    public List<UserDetail> delete(){
        repo.deleteById(1L);
        // todo override the findall to have return type of list
        return repo.findAll();
    }

    public Optional<UserDetail> findById(Long id) {
        return repo.findById(id);
    }

    public List<UserDetail> updateUserNameById(Long id, String name) {

        UserDetail user = repo.findById(id).orElseThrow(()-> new RuntimeException("user not present"));
     //   List<UserDetail> user1 = repo.findAll();
        user.setName(name);
        repo.update(user);
        return repo.findAll();
    }
}
