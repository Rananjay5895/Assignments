package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
class DemoTest {

    @Inject
    @Client("/crud")
    HttpClient client;
    @Inject
    Runner runner;

    @Inject
    UserService service;
    @Inject
    EmbeddedApplication<?> application;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    void testIfEntitySaved(){
        UserRepo repoMock = mock(UserRepo.class);
        UserDetail user = new UserDetail("Rananjay");
        when(repoMock.save(user)).thenReturn(user);

        //     UserDetail savedUser = repoMock.save(user);
        Assertions.assertNotNull(user);
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
        runner.save();
       List<UserDetail> list = service.findAll();
        UserDetail user = list.get(0);
        Assertions.assertEquals("Rananjay", user.getName());
    }

    @Test
    void testUpdatedUser(){
      //  String username = "Singh";
//        Optional<UserDetail> optional = repo.findById(1L);
//        UserDetail user1 = optional.get();
//        user1.setName(username);
//        repo.update(user1);
        runner.save();
        Assertions.assertEquals("New Name" , runner.updateUser().get(0).getName());
    }

    @Test
    void testDeleteUser(){
        Long id = 1L;
          runner.deleteUser();
          Assertions.assertEquals(List.of(),service.findById(id).stream().toList());
    }

    @Test
    void endpointRespondsWithProperContent(){
       // UserDetail user = new UserDetail("Rananjay");
        runner.save();
        var response = client.toBlocking().exchange("/" , UserDetail.class);
        System.out.println(response.body().toString());
        Assertions.assertEquals("Rananjay", response.body().getName());
        Assertions.assertEquals(1, response.getBody().stream().count());
    }

    @Test
    void testUpdateWithBody(){
        UserDetail user = new UserDetail("Last Name Singh");
        System.out.println(user.getId());
        UserDetail user1 = runner.save();
        System.out.println(user1.getId());
        System.out.println(user1.getName());
        var request = HttpRequest.PUT("/update/body" , user);
        var response = client.toBlocking().exchange(request);
        System.out.println(response.getBody());
        Assertions.assertEquals(HttpStatus.OK , response.getStatus());
        Assertions.assertEquals(user.getName() , response.body());

    }

}