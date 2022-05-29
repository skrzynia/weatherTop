package controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilityValidators {

    public static boolean isEmailCorrect(String email)
    {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = pattern.matcher(email);

        return matcher.find();

    }

    public static boolean isDataCorrect(String data)
    {
        return data.length() > 0;
    }


}
