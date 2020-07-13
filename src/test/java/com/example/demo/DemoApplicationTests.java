package com.example.demo;

import com.example.demo.controller.CityController;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
class DemoApplicationTests {}
