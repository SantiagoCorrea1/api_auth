package com.authb.api_auth.util;

import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    public  Boolean isPasswordValid(String password) {
        // Regex pattern to check the password
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";

        // Check if the password matches the regex pattern
        return password != null && password.matches(regex);
    }

    public  Boolean isEmailValid(String email){
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(regex);
    }
    public  Boolean isIdTypeValid(String idType){
        String regex = "^[123]$";
        return idType != null && idType.matches(regex);
    }

    public  Boolean isGenderValid(String gender){
        String regex = "^[123]$";
        return gender != null && gender.matches(regex);
    }
    //
    public  Boolean isCityValid(String city){
        String regex = "^(1(2[0-1]|[01]?\\d)|[1-9])$";
        return city != null && city.matches(regex);
    }

    public  Boolean isIdValid(String id){
        //id solo acepta 12 nuemeros o numero de pasaporte
        String regex = "^(\\d{6,12}|[A-Z0-9]{6,9})$";
        return id != null && id.matches(regex);
    }

//    public static Boolean isAddressValid(String address){
//        String regex = ;
//        return address != null && address.matches(regex);
//    }

    public  Boolean isFirstNameValid(String name){
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+([ '-][a-zA-ZáéíóúÁÉÍÓÚñÑ]+)*$";
        return name != null && name.matches(regex);
    }

    public  Boolean isLastNameValid(String lastName){
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+([ '-][a-zA-ZáéíóúÁÉÍÓÚñÑ]+)?$";
        return lastName != null && lastName.matches(regex);
    }

    public  Boolean isBirthDateValid(String date){
        String regex = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
        return date != null && date.matches(regex);
    }

    public  Boolean isPhoneNumberValid(String number){
        String regex = "^\\d{10}$";
        return number != null && number.matches(regex);
    }

//    public static Boolean isAvatarUrlValid(String url){
//        String regex = ;
//        return url != null && url.matches(regex);
//    }
}
