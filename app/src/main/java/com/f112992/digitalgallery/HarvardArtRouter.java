package com.f112992.digitalgallery;

import android.os.Looper;
import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HarvardArtRouter {
    private static final String BASE_URL = "https://api.harvardartmuseums.org";
    private static final String API_KEY = Env.HARVARD_ART_API_KEY;
    private static final OkHttpClient client = new OkHttpClient();
    private static final Random rng = new Random();
    public static int totalRecords = 200;

    public static void config() {
        JSONObject resObj = new JSONObject();
        String result = "";

        Request request = new Request.Builder()
                .url(BASE_URL + "/object" + "?apikey=" + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                result = response.body().string();
                resObj = new JSONObject(result);
                int test = resObj.getJSONObject("info").getInt("totalrecords");
                totalRecords = test;
            } else {
                System.out.println("Request failed: " + response.code());
            }
        } catch (IOException e) {
            System.out.println("Config failed HarvardArtRouter");
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("JSON failed to parse");
        }
    }
    public static JSONObject getExhibitionInfo() {
        JSONObject resObj = new JSONObject();
        String result = "";

        Request request = new Request.Builder()
                .url(BASE_URL + "/exhibition/4236" + "?apikey=" + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                result = response.body().string();
                resObj = new JSONObject(result);
            } else {
                System.out.println("Request failed: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("JSON failed to parse");
        }

        return resObj;
    }

    public static Request buildObjectRequest(String objID, String requestedJSONData) {
        return new Request.Builder()
                .url(BASE_URL + "/object/"+ objID + "/" + requestedJSONData + "?apikey=" + API_KEY)
                .build();
    }

    public static String getRandObjectRecordString() {
        int randRecordID = rng.nextInt(totalRecords);
        while (Objects.equals(getObjectTitle(Integer.toString(randRecordID)), "{error:Not found}") ||
                Objects.equals(getObjectTitle(Integer.toString(randRecordID)), "")) {
            randRecordID = rng.nextInt(totalRecords);
        }
        return Integer.toString(randRecordID);
    }

    public static JSONObject getObject(String objID) {
        JSONObject resObj = new JSONObject();
        String result = "";

        Request request = buildObjectRequest(objID, "");

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                result = response.body().string();
                resObj = new JSONObject(result);
            } else {
                System.out.println("Request failed: " + response.code());
            }
        } catch (IOException e) {
            return resObj;
        } catch (JSONException e) {
            System.out.println("JSON failed to parse");
        }

        return resObj;
    }

    public static String getObjectTitle(String objID) {
        String result = "";

        Request request = buildObjectRequest(objID, "title");

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                result = response.body().string();
            } else {
                System.out.println("Request failed: " + response.code());
            }
        } catch (IOException e) {
            return result;
        }

        return result.replace("\"", "");
    }

    public static String getObjectTitle(JSONObject obj) throws JSONException {
        return obj.getString("title");
    }

    public static String getObjectDesc(JSONObject obj) {
        JSONArray contextualText = null;
        try {
            contextualText = obj.getJSONArray("contextualtext");
            return contextualText
                    .getJSONObject(0)
                    .getString("context");
        } catch (JSONException e) {
            return "No Description available";
        }
    }

    public static JSONArray getObjectContextualText(String objID) {
        JSONArray resObj = new JSONArray();
        String result = "";

        Request request = buildObjectRequest(objID, "contextualtext");

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                result = response.body().string();
                resObj = new JSONArray(result);
            } else {
                System.out.println("Request failed: " + response.code());
            }
        } catch (IOException e) {
            return resObj;
        } catch (JSONException e) {
            System.out.println("JSON failed to parse");
        }

        return resObj;
    }

    public static String getObjectPrimaryImageURL(String objID) {
        String result = "";

        Request request = buildObjectRequest(objID, "primaryimageurl");

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                result = response.body().string();
            } else {
                System.out.println("Request failed: " + response.code());
            }
        } catch (IOException e) {
            return result;
        }

        return result.replace("\"", "");
    }

    public static JSONArray getObjectImages(String objID) {
        JSONArray resObj = new JSONArray();
        String result = "";

        Request request = buildObjectRequest(objID, "images");

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                result = response.body().string();
                resObj = new JSONArray(result);
            } else {
                System.out.println("Request failed: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("JSON failed to parse");
        }

        return resObj;
    }

    public static String getObjectImageURL(String objID) throws JSONException {
        String imageURL = getObjectPrimaryImageURL(objID);
        if (imageURL != null) {
            return imageURL;
        }
        JSONArray images = getObjectImages(objID);
        if (images == null) {
            return "";
        }
        imageURL = images
            .getJSONObject(0)
            .getString("baseimageurl");
        return imageURL;
    }

    public static String getObjectImageURL(JSONObject obj) throws JSONException {
        String imageURL = obj.getString("primaryimageurl");
        if (imageURL != null) {
            return imageURL;
        }
        JSONArray images = obj.getJSONArray("images");
        if (images == null) {
            return "";
        }
        imageURL = images
                .getJSONObject(0)
                .getString("baseimageurl");
        return imageURL;
    }
}
