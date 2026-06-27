package com.f112992.digitalgallery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ArtService {
    public static ArtData getHarvardArtObjectData() {
        ArtData data = new ArtData();
        String objID = HarvardArtRouter.getRandObjectRecordString();
        JSONObject obj = HarvardArtRouter.getObject(objID);

        try {
            data.title = HarvardArtRouter.getObjectTitle(obj);
            String description = HarvardArtRouter.getObjectDesc(obj);
            if (!description.isEmpty()) {
                data.description = description;
            }

            data.imageURL = HarvardArtRouter.getObjectImageURL(obj);
            data.source = obj.getString("creditline");
            data.externalLink = obj.getString("url");
        } catch (JSONException e) {
            return data;
        }
        return data;
    }
}
