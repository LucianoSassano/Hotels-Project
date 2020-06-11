package com.example.demo.service;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.UserUtils;
import lombok.Data;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Data
public class UserService {

   final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public List<UserDTO> findAll(){
      List<User>users =  userRepository.findAll();
      return UserUtils.listEntityToDTO(users);
    }

    public UserDTO insert(UserDTO userNew){
        User toSave = User.generateInstanceFromDTO(userNew);
        toSave.setCreateAt(LocalDateTime.now());
        toSave.setDeleteAt(LocalDateTime.now());
        toSave.setUpdateAt(LocalDateTime.now());
        return UserDTO.generateInstanceFromUser(userRepository.save(toSave));

    }

}
