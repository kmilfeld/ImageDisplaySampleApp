package com.kmilfeld.imagedisplaycodingexercise.network;

import com.kmilfeld.imagedisplaycodingexercise.data.PhotoObject;
import com.kmilfeld.imagedisplaycodingexercise.util.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Handles parsing the JSON response from the Flickr search functionality
 *
 * Created by karenm on 2/4/17.
 */
public class FlickrResponseParser
{
    private static String TAG_PHOTO_ARRAY = "photo";
    private static String TAG_PHOTOS = "photos";
    /* package private */ static String TAG_THUMBNAIL_URL = "url_sq";
    /* package private */ static String TAG_LARGE_URL = "url_l";

    /**
     * Handles parsing the JSON response from the Flickr search functionality
     * @param jsonData The JSON data passed back
     * @return An array of Photo Objects.
     */
    /* package private */ static ArrayList<PhotoObject> parseSearchJSON( String jsonData )
    {
        ArrayList<PhotoObject> photoURLs = new ArrayList<>();

        try
        {
            JSONObject jsonObject = new JSONObject( jsonData );
            JSONObject photoObject = jsonObject.getJSONObject( TAG_PHOTOS );
            JSONArray photos = photoObject.getJSONArray( TAG_PHOTO_ARRAY );

            JSONObject photo;
            for ( int i = 0; i < photos.length(); i++ )
            {
                photo = photos.getJSONObject( i );
                String thumbnailPhotoURL = photo.getString( TAG_THUMBNAIL_URL );
                if ( photo.has( TAG_LARGE_URL ) )
                {
                    String largePhotoURL = photo.getString( TAG_LARGE_URL );
                    photoURLs.add( new PhotoObject( thumbnailPhotoURL, largePhotoURL ) );
                }

            }
        }
        catch( JSONException e )
        {
            Logger.error( e.getMessage() );
            e.printStackTrace();
            return null;
        }

        return photoURLs;
    }
}
