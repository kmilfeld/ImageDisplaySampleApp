package com.kmilfeld.imagedisplaysampleapp.photogridscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.kmilfeld.imagedisplaysampleapp.R;
import com.squareup.picasso.Picasso;

/**
 * Activity used for displaying a full-screen photo.  Since there is so little
 * code going on, this is an activity for now, but if functionality expanded or if
 * it were to be used as part of an activity (say, in a tablet implementation), then
 * this should be converted to a fragment.
 *
 * Created by karenm on 2/4/17.
 */

public class FullScreenPhotoActivity extends AppCompatActivity
{
    /** Intent extra used for getting the photo URL when this activity is started */
    private static String INTENT_EXTRA_PHOTO_URL = "FullScreenPhotoActivity.PhotoURL";

    /**
     * Launches the FullScreenPhotoActivity
     * @param activity The activity that is launching the FullScreenPhotoActivity
     */
    public static void launch( Activity activity, String photoUrl )
    {
        Intent intent = new Intent( activity, FullScreenPhotoActivity.class );
        intent.putExtra( INTENT_EXTRA_PHOTO_URL, photoUrl );
        activity.startActivity( intent );
    }

    /**
     * Called when the activity is starting. This is where most initialization should go
     * @param savedInstanceState  If the activity is being re-initialized after previously
     *                            being shut down then this Bundle contains the data it most
     *                            recently supplied in onSaveInstanceState(Bundle).
     *                            Note: Otherwise it is null.
     */
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.full_screen_photo_activity );
        String url = getIntent().getStringExtra( INTENT_EXTRA_PHOTO_URL );
        ImageView imageView = (ImageView) findViewById( R.id.imageView );

        Picasso.with( this )
                .load( url )
                .into( imageView );
    }
}
