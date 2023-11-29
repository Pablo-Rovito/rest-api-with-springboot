package com.techu.apitechu.controllers;

import com.techu.apitechu.error.UserException;
import com.techu.apitechu.models.UserModel;
import com.techu.apitechu.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.techu.apitechu.ApitechuApplication.API_BASE_URL;

@RestController
public class UserController {
    private final String NAME = this.getClass().getSimpleName();
    @Autowired
    UserService userService;

    @GetMapping(API_BASE_URL + "/users")
    public ResponseEntity<List<UserModel>> getUsersOfAge(
            @RequestParam(required = false) String age,
            @RequestParam(required = false) String id
    ) {
        final String METHOD_NAME = "getUsersOfAge";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s with age: %s and id: %s", LOCATOR, age, id);

        Integer ageInt = null;

        if(age != null) {
            ageInt = Integer.parseInt(age);
        }

        List<UserModel> userList = this.userService.getUsers(ageInt, id);

        return ResponseEntity.ok(userList);
    }

    @PostMapping(API_BASE_URL + "/users")
    public ResponseEntity<UserModel> createUser(
            @RequestBody UserModel userModel
    ) throws UserException {
        final String METHOD_NAME = "createUser";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);
        System.out.printf("  Id: %s  ", userModel.getId());
        System.out.printf("  Age: %s  ", userModel.getAge());
        System.out.printf("  Name: %s  ", userModel.getName());

        try {
            return ResponseEntity.ok(this.userService.createUser(userModel));
        } catch (UserException exception) {
            return new ResponseEntity<>(
                    UserWithException(exception.getMessage()),
                    exception.getStatusCode()
            );
        }
    }

    @PutMapping(API_BASE_URL + "/users/{id}")
    public ResponseEntity<UserModel> updateUser(
            @RequestBody UserModel userModel,
            @PathVariable String id
    ) throws UserException {
        final String METHOD_NAME = "updateUser";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s(%s)", LOCATOR, id);
        try {
            return ResponseEntity.ok(userService.updateUser(userModel, id));
        } catch (UserException exception) {
            return new ResponseEntity<>(
                    UserWithException(exception.getMessage()),
                    exception.getStatusCode()
            );
        }
    }

    @DeleteMapping(API_BASE_URL + "/users/{id}")
    public ResponseEntity<UserModel> deleteUser(
            @PathVariable String id
    ) throws UserException {
        final String METHOD_NAME = "deleteUser";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s(%s)", LOCATOR, id);
        try {
            return ResponseEntity.ok(this.userService.deleteUser(id));
        } catch (UserException exception) {
            return new ResponseEntity<>(
                    UserWithException(exception.getMessage()),
                    exception.getStatusCode()
            );
        }
    }

    private UserModel UserWithException(String message) {
        return new UserModel(message);
    }

}
