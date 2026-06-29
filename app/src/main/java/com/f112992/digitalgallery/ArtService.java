package com.f112992.digitalgallery;

import org.json.JSONException;
import org.json.JSONObject;

public class ArtService {

    public static ArtData getHarvardArtObjectData(String objID) {
        JSONObject obj = HarvardArtRouter.getObject(objID);
        ArtData data;
        try {
            data = new ArtData(objID, HarvardArtRouter.getObjectTitle(obj), DBHelper.harvardSourceModel.ID);
            String description = HarvardArtRouter.getObjectDesc(obj);
            if (!description.isEmpty()) {
                data.description = description;
            }

            data.imageURL = HarvardArtRouter.getObjectImageURL(obj);
            data.source = obj.getString("creditline");
            data.externalLink = obj.getString("url");
        } catch (JSONException e) {
            return null;
        }
        return data;
    }

    public static ArtData getHarvardArtRandObjectData() {
        String objID = HarvardArtRouter.getRandObjectRecordString();
        return getHarvardArtObjectData(objID);
    }
}
