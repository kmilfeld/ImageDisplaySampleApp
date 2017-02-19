package com.kmilfeld.imagedisplaysampleapp.network;

/**
 * Created by karenm on 2/19/17.
 */

public class APIKeyManager
{
    // API Key for Flickr
    private static final String FLICKR_API_KEY = "YOUR_API_KEY_HERE";

    /* package private */ static String getFlickrAPIKey()
    {
        return FLICKR_API_KEY;
    }
}
