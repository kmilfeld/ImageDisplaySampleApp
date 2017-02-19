package com.kmilfeld.imagedisplaysampleapp.network;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.kmilfeld.imagedisplaysampleapp.data.PhotoObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Async Task that queries flickr for photo data for a given line of text
 *
 * Created by karenm on 2/4/17.
 */
public class QueryFlickrAsyncTask extends AsyncTask<Void, Void, Void>
{
    public interface FlickrAsyncTaskListener
    {
        /**
         * Called when the QueryFlickrAsyncTask is done running and if no errors occurred
         * @param photos The photo data that was returned
         */
        void onAsyncTaskComplete( ArrayList<PhotoObject> photos );

        /**
         * Called when the QueryLifkrAsyncTask is done running if there was an error
         */
        void onAsyncTaskError();
    }

    // String to create Flickr API urls
    private static final String BASE_URL = "https://api.flickr.com/services/rest/?method=%s";
    private static final String PARAM_URL_METHOD = "flickr.photos.search";
    private static final String PARAM_API_KEY = "&api_key=%s";
    private static final String PARAM_TEXT = "&text=%s";
    private static final String FORMAT_JASON = "&format=json&nojsoncallback=?";
    private static final String PARAM_EXTRAS = "&extras=%s,%s";
    private static final String PARAM_PHOTOS_PER_PAGE = "&per_page=%d";
    private static final int NUM_PHOTOS = 50;

    /** Listener to callback when this task is complete */
    FlickrAsyncTaskListener mListener;

    /** The text to search for photos of */
    String mText;

    /** The results from the call to flickr */
    ArrayList<PhotoObject> mResult;

    /** The network client used for making network calls */
    OkHttpClient mClient;

    /**
     * Creates a new QueryFlickr Async Task
     * @param text The text to search for photos of
     * @param listener The listener to callback when the tast is complete
     */
    public QueryFlickrAsyncTask( @NonNull String text, @NonNull FlickrAsyncTaskListener listener )
    {
        mListener = listener;
        mText = text;
        mClient = new OkHttpClient();
    }

    /**
     * Perform a computation on a background thread
     *
     * @param voids The parameters of the task
     * @return Always returns null
     */
    @Override
    protected Void doInBackground( Void... voids )
    {
        try
        {
            Request request = new Request.Builder()
                    .url( getURLForPhotoSearch( mText ) )
                    .build();

            Response response = mClient.newCall( request ).execute();
            mResult = FlickrResponseParser.parseSearchJSON( response.body().string() );

            if ( mResult == null )
            {
                mResult = new ArrayList<>();
            }
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Invoked on the UI thread after the background computation finishes.
     */
    @Override
    protected void onPostExecute( Void aVoid )
    {
        if ( mResult != null )
        {
            mListener.onAsyncTaskComplete( mResult );
        }
        else
        {
            mListener.onAsyncTaskError();
        }
    }


    /**
     * Gets the URL to do a Flickr Photo search
     * @param text The text to search with
     * @return The URL as a String
     */
    private String getURLForPhotoSearch( String text )
    {
        String baseSearchURL = String.format( BASE_URL, PARAM_URL_METHOD );
        String textParam = String.format( PARAM_TEXT, text );
        String numPhotosParam = String.format( PARAM_PHOTOS_PER_PAGE, NUM_PHOTOS );
        String apiKeyParam = String.format( PARAM_API_KEY, APIKeyManager.getFlickrAPIKey() );
        String extras = String.format( PARAM_EXTRAS, FlickrResponseParser.TAG_THUMBNAIL_URL, FlickrResponseParser.TAG_LARGE_URL );

        StringBuilder searchURL = new StringBuilder()
                .append( baseSearchURL )
                .append( apiKeyParam )
                .append( textParam )
                .append( FORMAT_JASON )
                .append( extras )
                .append( numPhotosParam );

        return searchURL.toString();
    }
}