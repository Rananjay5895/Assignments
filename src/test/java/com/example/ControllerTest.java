package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ControllerTest {

        UserService service = mock(UserService.class);
        Runner runner;

    @BeforeEach
    void setUp() {
        runner = new Runner(service);
    }

    @Test
        void testIfEntitySaved(){
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
        when(repoMock.count()).thenReturn(list.stream().count()).thenReturn(3L);
        Assertions.assertEquals(2,repoMock.count());
        Assertions.assertEquals(3,repoMock.count());
    }

        @Test
        void testFindById(){
            UserDetail user = new UserDetail("Rananjay");
            UserRepo repoMock = mock(UserRepo.class);
            when(repoMock.save(user)).thenReturn(user);
            when(repoMock.findById(user.getId())).thenReturn(Optional.of(user));
            when(service.findById(user.getId())).thenReturn(Optional.of(user));
            UserDetail savedUser = repoMock.save(user);
            Optional<UserDetail> userDetail = runner.getById(savedUser.getId());
            Assertions.assertEquals("Rananjay", userDetail.get().getName());
    }

        @Test
        void testUpdatedUser_2(){
        UserDetail userDetail = new UserDetail("My Name");
        UserRepo repoMock = mock(UserRepo.class);
        when(repoMock.save(userDetail)).thenReturn(userDetail);
        when(service.updateUser(userDetail.getId())).thenReturn(Optional.of(new UserDetail("New Name")));
        UserDetail user = repoMock.save(userDetail);
        System.out.println(user.getName());
        Optional<UserDetail> user1 = runner.updateUser(user.getId());
        Assertions.assertEquals("New Name" ,user1.get().getName());

    //    verify(service).updateUser("12344");
    }


        @Test
        void testDeleteUser(){
        UserDetail userDetail = new UserDetail("Rananjay");
        UserRepo repoMock = mock(UserRepo.class);
        when(repoMock.save(userDetail)).thenReturn(userDetail);
        UserDetail savedUser = repoMock.save(userDetail);
        runner.deleteUser(savedUser.getId());
        Assertions.assertEquals(List.of(),repoMock.findAll().stream().toList());//runner.getById(savedUser.getId()).stream().toList()
    }

    }
