package com.kmilfeld.imagedisplaysampleapp.data;

import com.kmilfeld.imagedisplaysampleapp.network.QueryFlickrAsyncTask;

import java.util.ArrayList;

/**
 * Manages the photo data.  This handles any calls to get the photo data.  Can
 * also be expanded to store the data if it is eventually needed to be
 * persistent and/or will be referenced from other parts of the app.
 *
 * Created by karenm on 2/4/17.
 */
public class PhotoManager implements QueryFlickrAsyncTask.FlickrAsyncTaskListener
{
    /** The singleton instance of this class */
    private static PhotoManager mPhotoManager;

    /** The object to callback when the photo data is ready to consume */
    private PhotoDataReadyListener mListener;

    /**
     * Callback listener for when the photos are ready
     */
    public interface PhotoDataReadyListener
    {
        /** Called when photo data is ready to consume */
        void onPhotoDataReady( ArrayList<PhotoObject> photos );

        /** Called if there was an error updating the photo data */
        void onPhotoDataUpdateError();
    }

    /**
     * Private constructor to ensure nobody calls it
     */
    private PhotoManager()
    {
        // Empty on purpose
    }

    /**
     * Gets a singleton instance of this class
     * @return
     */
    public static PhotoManager getInstance()
    {
        if ( mPhotoManager == null )
        {
            mPhotoManager = new PhotoManager();
        }

        return mPhotoManager;
    }

    /**
     * Indicates that we should update the image data
     *
     * @param text     The string to search on
     * @param listener The listener to call back to once the photos are ready
     */
    public void startPhotoDataUpdate( String text, PhotoDataReadyListener listener )
    {
        mListener = listener;
        QueryFlickrAsyncTask task = new QueryFlickrAsyncTask( text, this );
        task.execute();
    }

    /**
     * Called when the QueryFlickrAsyncTask is done running and if no errors occurred
     * @param photos The photo data that was returned
     */
    @Override
    public void onAsyncTaskComplete( ArrayList<PhotoObject> photos )
    {
        mListener.onPhotoDataReady( photos );
    }

    /**
     * Called when the QueryLifkrAsyncTask is done running if there was an error
     */
    @Override
    public void onAsyncTaskError()
    {
        mListener.onPhotoDataUpdateError();
    }
}
