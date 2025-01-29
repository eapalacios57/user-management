package com.pragma.usuarios.application.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class ValidationUtils {
    public static boolean emailValid(String email){
        String regex = "[A-Za-z0-9+_.-]+@(.+)$";
        return  email != null && email.matches(regex);
    }

    public static boolean phoneValid(String phone){
        String regex = "^\\+?\\d{10,13}";
        return  phone != null && phone.matches(regex);
    }

    public static boolean documentValid(String document){
        return  document != null && document.matches("\\d+");
    }

    public static boolean adult(String birthDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateNacFormat = LocalDate.parse(birthDate, formatter);
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateNacFormat, currentDate).getYears() >= 18;
    }

}
