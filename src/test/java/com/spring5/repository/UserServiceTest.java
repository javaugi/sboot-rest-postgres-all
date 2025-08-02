/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.repository;

import com.abc.utils.IterableToList;
import com.spring5.dao.UserDao;
import com.spring5.entity.User;
import com.spring5.service.UserServiceImp;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 *
 * @author javaugi
 */
@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class UserServiceTest {
    @Mock
    private UserDao userDao;
    
    //When using Mockito Use @InjectMocks to inject Mocked beans to following class
    @InjectMocks
    private UserServiceImp userService;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testNothing() {        
    }

    //@Test
    public void testGetAllUsers() {
        //given
        long id = 1;
        long id2 = 2;
        User user = new User(id, "John Smith", "jsmith@email.com");
        User user2 = new User(id2, "Kevin Hart", "khart@email.com");
        //When
        given(this.userDao.findAll())
                .willReturn(List.of(user, user2));
        List<User> userList = IterableToList.iterableToList(userService.findAll());
        //Then
        //Make sure to import assertThat From org.assertj.core.api package
        assertThat(userList).isNotNull();
        assertThat(userList.size()).isEqualTo(2);
    }    
}
