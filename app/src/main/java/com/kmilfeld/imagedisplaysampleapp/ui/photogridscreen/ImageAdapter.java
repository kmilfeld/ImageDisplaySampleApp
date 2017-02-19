package com.kmilfeld.imagedisplaysampleapp.ui.photogridscreen;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.kmilfeld.imagedisplaysampleapp.data.PhotoManager;
import com.kmilfeld.imagedisplaysampleapp.data.PhotoObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Adapter that serves as an underlying bridge between the photo data
 * and the views that show it.
 *
 * Created by karenm on 2/4/17.
 */
public class ImageAdapter extends BaseAdapter
    implements PhotoManager.PhotoDataReadyListener
{
    private Context mContext;
    private ArrayList<PhotoObject> mPhotos = new ArrayList<>();
    private static final int IMAGE_PADDING = 2;
    private static final int IMAGE_SIZE = 200;

    public ImageAdapter( Context context )
    {
        mContext = context;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     * @return The number of items
     */
    public int getCount()
    {
        return mPhotos.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     * @param position The position to get the item for
     * @return
     */
    @Override
    public PhotoObject getItem( int position )
    {
        return mPhotos.get( position );
    }

    /**
     * Get the row id associated with the specified position in the list.
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    public long getItemId( int position )
    {
        // TODO karenm - does this need to be updated?
        return 0;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can
     * either create a View manually or inflate it from an XML layout file.
     * @param position The position of the item within the adapter's data set of the item whose view we want.
     * @param convertView The old view to reuse, if possible
     * @param parent The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    public View getView( int position, View convertView, ViewGroup parent )
    {
        ImageView imageView;
        // Recycle the view if we can
        if ( convertView == null )
        {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView( mContext );
            imageView.setLayoutParams( new GridView.LayoutParams( IMAGE_SIZE, IMAGE_SIZE ) );
            imageView.setScaleType( ImageView.ScaleType.CENTER_CROP );
            imageView.setPadding( IMAGE_PADDING, IMAGE_PADDING, IMAGE_PADDING, IMAGE_PADDING );
        }
        else // Create a new view
        {
            imageView = (ImageView) convertView;
        }

        // TODO karenm - consider putting in a default error image
        Picasso.with( mContext )
                .load( mPhotos.get( position ).getThumbnailUrl() )
                .into( imageView );

        return imageView;
    }

    /**
     * Called when photo data is ready to consume
     */
    @Override
    public void onPhotoDataReady( ArrayList<PhotoObject> photoURLs )
    {
        mPhotos = photoURLs;
        notifyDataSetChanged();
    }

    /**
     * Called if there was an error updating the photo data
     */
    // TODO karenm - some kind of error handling to alert the user should take place
    @Override
    public void onPhotoDataUpdateError()
    {
        mPhotos = new ArrayList<>();
    }
}
