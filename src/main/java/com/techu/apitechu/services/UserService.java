package com.techu.apitechu.services;

import com.techu.apitechu.error.ProductException;
import com.techu.apitechu.error.UserException;
import com.techu.apitechu.models.UserModel;
import com.techu.apitechu.repositories.UserRepository;
import com.techu.apitechu.utils.ProductEnum;
import com.techu.apitechu.utils.UserEnum;
import com.techu.apitechu.validators.UserValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final String NAME = this.getClass().getSimpleName();

    @Autowired
    UserRepository userRepository;

    public List<UserModel> getUsers(Integer age, String id) {
        final String METHOD_NAME = "getUsers";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s with age: %s and id: %s", LOCATOR, age, id);

        return this.userRepository.getUsers(age, id);
    }

    public UserModel createUser(UserModel userModel) throws UserException {
        final String METHOD_NAME = "createUser";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s with id = %s", LOCATOR, userModel.getId());

        List<UserModel> userById = this.getUsers(null, userModel.getId());

        if(!userById.isEmpty()) {
            return new UserModel(
                UserEnum.USER_ALREADY_IN_LIST.getMessage(),
                UserEnum.USER_ALREADY_IN_LIST.getStatusCode()
            );
        }

        boolean success = userRepository.createUser(userModel);

        if(!success) {
            return new UserModel(
                    UserEnum.USER_NOT_CREATED.getMessage(),
                    UserEnum.USER_NOT_CREATED.getStatusCode()
            );
        }

        return userModel;
    }

    public UserModel updateUser(UserModel userModel, String id) throws UserException {
        final String METHOD_NAME = "updateUser";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s with id = %s", LOCATOR, userModel.getId());

        List<UserModel> userInList = this.getUsers(null, id);

        if(userInList.isEmpty()) {
            throw new UserException(
                    LOCATOR,
                    UserEnum.USER_NOT_IN_LIST.getMessage(),
                    UserEnum.USER_NOT_IN_LIST.getStatusCode()
            );
        }

        userRepository.updateUser(userModel, id);

        // TODO: Check consistency between userModel and userInList, like in ProductService

        // I need to make sure the change has been successful
        if(!userModel.equals(this.getUsers(null, id).get(0))) {
            throw new UserException(
                    LOCATOR,
                    UserEnum.USER_NOT_UPDATED.getMessage(),
                    UserEnum.USER_NOT_UPDATED.getStatusCode()
            );
        }

        return userModel;
    }

    public UserModel deleteUser(String id) throws UserException {
        final String METHOD_NAME = "deleteUser";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s(%s)", LOCATOR, id);

        List<UserModel> userInList = this.getUsers(null, id);

        if(userInList.isEmpty()) {
            throw new UserException(
                    LOCATOR,
                    UserEnum.USER_NOT_IN_LIST.getMessage(),
                    UserEnum.USER_NOT_IN_LIST.getStatusCode()
            );
        }

        if(UserValidations.userIsTooOld(userInList.get(0).getAge())) {
            throw new UserException(
                    LOCATOR,
                    UserEnum.USER_TOO_OLD.getMessage(),
                    UserEnum.USER_TOO_OLD.getStatusCode()
            );
        }

        if(!this.userRepository.deleteUser(id)) {
            throw new UserException(
                    LOCATOR,
                    UserEnum.USER_NOT_DELETED.getMessage(),
                    UserEnum.USER_NOT_DELETED.getStatusCode()
            );
        }

        return userInList.get(0);
    }
}
