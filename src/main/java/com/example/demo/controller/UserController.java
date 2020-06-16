package com.example.demo.controller;
import com.example.demo.dto.CardDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.CreditCardService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

//Controller to User request management.
@RestController
@RequestMapping("/users")
public class  UserController {

    @Autowired
    UserService userService;

    @Autowired
    CreditCardService creditCardService;

    //This endpoint return all users registered
    @GetMapping("")
    public ResponseEntity getAll(){
            return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    //This endpoint return a specific user information,searching by id
    @GetMapping ("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id){
            return ResponseEntity.ok().body(userService.findById(id));
    }

    //This endpoint return a all cards registered about specific user.
    @GetMapping("/{dni}/card")
    public ResponseEntity getCardsByDni(@PathVariable("dni")Integer dni) throws Exception {
        return ResponseEntity.ok().body(userService.findCardsByDni(dni));
    }

    //This endpoint register a new user.
    @PostMapping("")
    public ResponseEntity insert(@RequestBody @Valid UserDTO userNew){
        return ResponseEntity.ok().body(userService.insert(userNew));
    }

    //This endpoint register a new card for a specific user.
    @PostMapping("/{dni}/card")
    public ResponseEntity insert(@PathVariable("dni")Integer dni,@RequestBody @Valid CardDTO card){
            return ResponseEntity.ok().body(userService.insertCard(card,dni));
    }

    //This enpoint allow update a specific user.
    @PutMapping("")
    public ResponseEntity updateByDni(@RequestBody @Valid UserDTO toUpdate,Integer dni){
            return ResponseEntity.ok().body(userService.update(toUpdate,dni));
    }

    //This endpoint make a soft-delete for a specific user, identify by dni.
    @DeleteMapping("/{dni}")
    public void delete(@PathVariable("dni")Integer dni){
            userService.delete(dni);
    }
}
