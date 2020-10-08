package com.example.mobiledevproject.Models.Beatsaver.beatsavermap;

import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.MetaData;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.Stats;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.Uploader;

public class BeatsaverMap {

    public MetaData getMetaData() {
        return metaData;
    }

    public Stats getStats() {
        return stats;
    }

    public String getDescription() {
        return description;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public String get_id() {
        return _id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public Uploader getUploader() {
        return uploader;
    }

    public String getHash() {
        return hash;
    }

    public String getUploaded() {
        return uploaded;
    }

    public String getDirectDownload() {
        return directDownload;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public String getCoverURL() {
        return coverURL;
    }

    private MetaData metaData;
    private Stats stats;
    private String description;
    private String deletedAt;
    private String _id;
    private String key;
    private String name;
    private Uploader uploader;
    private String hash;
    private String uploaded;
    private String directDownload;
    private String downloadURL;
    private String coverURL;


}
