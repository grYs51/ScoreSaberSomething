package be.grys.scoresabersomething.Models.Beatsaver.beatsavermap;

import java.io.Serializable;
import java.util.Date;

public class BeatsaverMap implements Serializable {

    private String id;
    private String name;
    private String description;
    private Uploader uploader;
    private MetaData metadata;
    private Stats stats;
    private String uploaded;
    private Boolean automapper;
    private Boolean ranked;
    private Boolean qualified;
    private Version[] version;
    private Date createdAt;
    private Date updatedAt;
    private Date lastPublishedAt;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Uploader getUploader() {
        return uploader;
    }

    public MetaData getMetadata() {
        return metadata;
    }

    public Stats getStats() {
        return stats;
    }

    public String getUploaded() {
        return uploaded;
    }

    public Boolean getAutomapper() {
        return automapper;
    }

    public Boolean getRanked() {
        return ranked;
    }

    public Boolean getQualified() {
        return qualified;
    }

    public Version[] getVersion() {
        return version;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getLastPublishedAt() {
        return lastPublishedAt;
    }


    public BeatsaverMap() {
    }
}
