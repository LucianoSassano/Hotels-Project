package com.example.demo.service;
import com.example.demo.repository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserService {

   final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }
}
