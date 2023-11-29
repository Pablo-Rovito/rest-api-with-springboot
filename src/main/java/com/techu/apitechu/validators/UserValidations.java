package com.techu.apitechu.validators;

public class UserValidations {
    public static final Integer MAX_AGE = 65;

    public static boolean userIsTooOld(Integer age) {
        return age > MAX_AGE;
    }

}
