package com.f112992.digitalgallery;

import java.sql.Date;

public class ArtData {
    public int ID;
    public String externalID;
    public String title;
    public String description;
    public String imageURL;
    public String source;
    public String externalLink;
    public Date dateAdded;
    public int rating;
    public boolean isDaily;
    public int sourceID;

    public ArtData(String externalID, String title, int sourceID) {
        this.externalID = externalID;
        this.title = title;
        this.dateAdded = new Date(System.currentTimeMillis());
        this.rating = -1;
    }

    public ArtData(int ID, String externalID, int sourceID, String title, String description, String imageURL, String source, String externalLink, Date dateAdded, int rating) {
        this.ID = ID;
        this.externalID = externalID;
        this.sourceID = sourceID;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.source = source;
        this.externalLink = externalLink;
        this.dateAdded = dateAdded;
        this.rating = rating;
    }

}
