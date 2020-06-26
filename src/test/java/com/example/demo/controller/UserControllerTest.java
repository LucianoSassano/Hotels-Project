package com.example.demo.controller;

import com.example.demo.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserControllerTest {
  @Mock
  UserRepository userRepository;

  @BeforeEach
  void setUp() throws Exception{

  }
  @Test
  void getAll() {
    // when(userRepository.findAll()).thenReturn(null);
    //Assert.assertEquals(ResponseEntity,);
  }

  @Test
  void getById() {}

  @Test
  void getCardsByDni() {}

  @Test
  void insert() {}

  @Test
  void testInsert() {}

  @Test
  void updateByDni() {}

  @Test
  void delete() {}
}