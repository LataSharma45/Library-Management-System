package project.LibraryManage.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.LibraryManage.dto.UserDto;
import project.LibraryManage.entity.UserEntity;
import project.LibraryManage.mapper.UserMapper;
import project.LibraryManage.service.UserService;

@RestController
public class UserController {
    private UserService userService;

    private UserMapper userMapping;

    public UserController(UserService userService, UserMapper userMapping) {
        this.userService = userService;
        this.userMapping = userMapping;
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto){
        UserEntity userEntity = userMapping.mapFrom(userDto);
        UserEntity savedUser = userService.saveUser(userEntity);
        UserDto requestedUser  = userMapping.mapTo(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(requestedUser);
    }

    @GetMapping("/user")
    public Page<UserDto>
    findAllUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size,
            @RequestParam (defaultValue = "name") String sortBy){
        Page<UserEntity> users = userService.findAll(page,size,sortBy);
        return  users.map(userMapping::mapTo);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> findOneUser(@PathVariable Long id){
        return userService.findOne(id).map(findUser -> {

            UserDto foundUser =   userMapping.mapTo(findUser);
            return new ResponseEntity<>(foundUser,HttpStatus.OK);

        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> fullUpdateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        Boolean exist = userService.isExist(id);
        if (!exist)
            return ResponseEntity.notFound().build();

        UserEntity user = userMapping.mapFrom(userDto);
        user.setId(id);
        UserEntity updatedUser = userService.saveUser(user);

        return new ResponseEntity<>(userMapping.mapTo(updatedUser),HttpStatus.OK);
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<UserDto> partialUpdate(@PathVariable Long id, @RequestBody UserDto userDto){
        if (userService.isExist(id))
            return ResponseEntity.notFound().build();

        UserEntity user = userMapping.mapFrom(userDto);
        UserEntity updateUser = userService.partialUpdate(id,user);
        return new ResponseEntity<>(userMapping.mapTo(updateUser),HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id){
        userService.deleteBook(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
