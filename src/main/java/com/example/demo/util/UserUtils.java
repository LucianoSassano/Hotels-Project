package com.example.demo.util;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import java.util.List;
import java.util.stream.Collectors;

public class UserUtils {

    public static List<UserDTO> listEntityToDTO(List<User>users){
        return users.stream()
                .map(user->new UserDTO(user.getName(),user.getDni(),user.getAddress(),user.getEmail(),user.getPhone(),user.getRol()))
                .collect(Collectors.toList());
    }

    public static UserDTO entitytoDTO(User user){
        return new UserDTO(user.getName(),user.getDni(),user.getAddress(),user.getEmail(),user.getPhone(),user.getRol());
    }
}
