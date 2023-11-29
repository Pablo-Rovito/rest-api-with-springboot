package com.techu.apitechu.repositories;

import com.techu.apitechu.ApitechuApplication;
import com.techu.apitechu.models.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    private final String NAME = this.getClass().getSimpleName();

    public List<UserModel> getUsers(Integer age, String id) {
        final String METHOD_NAME = "getUsers";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s", LOCATOR);

        List<UserModel> userList = ApitechuApplication.userList;

        if(age != null) {
            userList = userList.stream().filter(u -> age.equals(u.getAge())).collect(Collectors.toList());
        }

        if(id != null) {
            userList = userList.stream().filter(u -> id.equals(u.getId())).collect(Collectors.toList());

        }

        return userList;
    }


    public boolean createUser(UserModel userModel) {
        final String METHOD_NAME = "createUser";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s with id = %s", LOCATOR, userModel.getId());

        return ApitechuApplication.userList.add(userModel);
    }

    public void updateUser(UserModel userModel, String id) {
        final String METHOD_NAME = "updateUser";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s with id = %s", LOCATOR, id);

        // just in case...
        userModel.setId(id);

        int productIndex = ApitechuApplication.userList.indexOf(this.getUsers(null, id));
        ApitechuApplication.userList.set(productIndex, userModel);
    }

    public boolean deleteUser(String id) {
        final String METHOD_NAME = "deleteUser";
        final String LOCATOR = NAME + " - " + METHOD_NAME;
        System.out.printf("%n%s(%s)", LOCATOR, id);

        // User existence is checked in service
        return ApitechuApplication.userList.removeIf(p -> id.equals(p.getId()));
    }
}
