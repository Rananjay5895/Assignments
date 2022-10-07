package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceTest {

    UserRepo repoMock = mock(UserRepo.class);
    UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService(repoMock);
    }

    @Test
        void testIfEntitySaved1(){
        UserRepo repoMock = mock(UserRepo.class);
        UserDetail user = new UserDetail("Rananjay");
        when(repoMock.save(user)).thenReturn(user);

        UserDetail savedUser = repoMock.save(user);
        Assertions.assertNotNull(savedUser);
    }

        @Test
        public void testCountOfUsers(){
        UserRepo repoMock = mock(UserRepo.class);
        UserDetail user = new UserDetail("Rananjay");
        UserDetail user1 = new UserDetail("Singh");
        List<UserDetail> list = new ArrayList<>();
        list.add(user);
        list.add(user1);
        //    when(repoMock.save(user)).thenReturn(user);
        when(repoMock.count()).thenReturn(list.stream().count()).thenReturn(3L);
        Assertions.assertEquals(2,repoMock.count());
        Assertions.assertEquals(3,repoMock.count());
    }

        @Test
        void testFindById(){
            UserDetail user = new UserDetail("Rananjay");
            when(repoMock.save(user)).thenReturn(user);
            when(repoMock.findById(user.getId())).thenReturn(Optional.of(user));
            UserDetail savedUser = repoMock.save(user);
            System.out.println(savedUser.getName());
            Optional<UserDetail> userDetail = service.findById(savedUser.getId());
            System.out.println(userDetail.get().getName());
            Assertions.assertEquals("Rananjay", userDetail.get().getName());
    }

        @Test
        void testUpdatedUser_1(){
            UserDetail userDetail = new UserDetail("Rananjay");
            when(repoMock.save(userDetail)).thenReturn(userDetail);
            when(repoMock.findById(userDetail.getId())).thenReturn(Optional.of(userDetail));
            when(repoMock.update(userDetail)).thenReturn(userDetail);
            UserDetail user = service.save(userDetail);
            System.out.println(user);
            Optional<UserDetail> user1 = service.updateUser(user.getId());
            Assertions.assertEquals("New Name" ,user1.get().getName());
    }

        @Test
        void testDeleteUser(){
            UserDetail userDetail = new UserDetail("Rananjay");
            repoMock.deleteById(userDetail.getId());
            when(repoMock.save(userDetail)).thenReturn(userDetail);
            UserDetail savedUser = repoMock.save(userDetail);
            service.delete(savedUser.getId());
            Assertions.assertEquals(List.of(),service.findById(savedUser.getId()).stream().toList());
    }

}
