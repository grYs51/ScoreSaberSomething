package com.example.mobiledevproject.Models.Beatsaver.beatsavermap;

import java.io.Serializable;

public class Uploader implements Serializable {
    public String get_id() {
        return _id;
    }

    public String getUsername() {
        return username;
    }

    private String _id;
    private String username;
}
