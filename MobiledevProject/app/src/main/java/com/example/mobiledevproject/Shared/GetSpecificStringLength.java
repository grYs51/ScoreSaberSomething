package com.example.mobiledevproject.Shared;

public class GetSpecificStringLength {

    public String getShorterString(String string, int legnth){
        if(string.length() >= legnth){
            String s;
            s = string.substring(0, Math.min(+ string.length(), legnth));
            return s+"...";
        } else{
            return string;
        }
    }
}
