package com.example;

import com.example.excp.CustomException;
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

    public Optional<UserDetail> updateUser(Long id) {
        UserDetail userDetail = null;
        Optional<UserDetail> user = repo.findById(id);
        if(user.isPresent()) {
            userDetail = user.get();
            System.out.println(userDetail.getName());
        }
        else throw new CustomException("user not available");
        userDetail.setName("New Name");
        UserDetail user12 = repo.update(userDetail);
        System.out.println(user12.toString());
        return repo.findById(id);
    }

    public List<UserDetail> delete(Long id){
        repo.deleteById(id);
        return repo.findAll();
    }

    public Optional<UserDetail> findById(Long id) {
        return repo.findById(id);
    }

    public List<UserDetail> updateUserNameById(long id, String name) {

        UserDetail user = repo.findById(id).orElseThrow(()-> new CustomException("user not present"));
        user.setName(name);
        repo.update(user);
        return repo.findAll();
    }
}
