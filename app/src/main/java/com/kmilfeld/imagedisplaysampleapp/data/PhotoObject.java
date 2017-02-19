package com.kmilfeld.imagedisplaysampleapp.data;

/**
 * Object representation of a Photo and all of it associated data.
 *
 * Created by karenm on 2/4/17.
 */
public class PhotoObject
{
    /** URL for the thumbnail representation of this photo */
    private final String mThumbnailUrl;

    /** URL for the full-size representation of this photo */
    private final String mFullSizeUrl;

    /**
     * Creates a new photo object
     * @param thumbnailUrl The thumbnail URL
     * @param fullSizeUrl The URL of the full-sized photo
     */
    public PhotoObject( String thumbnailUrl, String fullSizeUrl )
    {
        mThumbnailUrl = thumbnailUrl;
        mFullSizeUrl = fullSizeUrl;
    }

    /**
     * Get the Thumbnail URL for this photo
     * @return The Thumbnail URL
     */
    public String getThumbnailUrl()
    {
        return mThumbnailUrl;
    }

    /**
     * Get the full-size URL for this photo
     * @return The full-size URL
     */
    public String getFullSizeUrl()
    {
        return mFullSizeUrl;
    }
}
