package com.houseofwisdom.houseofwisdom.utils;

public class HouseOfWisdomUtilityMethods {
    public static boolean confirmEmailSending(String body, String userName, Long bookId) {
        if(body != null
        && userName != null
        && bookId != null
        ) return true;
        else return false;
    }

    public static boolean sendMail(String userName, Long bookId) {
        boolean status = false;
        String messageBody = "Dear " + userName + ",\n"
                + "Your requested book with book Id " + bookId + "had been sent to you\n"
                + "Kind Regards\n"
                + "Librarian, House Of Wisdom";

        if(confirmEmailSending(messageBody, userName, bookId)) return status = true;
        return status;
    }
}
