package com.kmilfeld.imagedisplaysampleapp.photogridscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import com.kmilfeld.imagedisplaysampleapp.R;
import com.kmilfeld.imagedisplaysampleapp.util.Logger;

/**
 * The Main Activity for the ImageDisplayCodingExercise application.  This
 * activity is responsible for coordinating the activity between the toolbar
 * and the PhotoGridFragment
 */
public class MainActivity extends AppCompatActivity
{

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
        setContentView( R.layout.activity_main );

        // Initialize the toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar( myToolbar );

        // Setup the search button
        View searchButton = findViewById( R.id.search_button );
        final EditText searchText = (EditText) findViewById( R.id.search_input );

        // Get the grid fragment in order
        final PhotoGridFragment gridFragment = (PhotoGridFragment) getFragmentManager().findFragmentById( R.id.photo_grid_fragment );

        // When the search button is clicked on the toolbar, we want
        // the grid fragment to display photo results for that text
        searchButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                String text = searchText.getText().toString();

                Logger.verbose( "Getting images for text: " + text );

                gridFragment.displayPhotosFor( text );
            }
        } );
    }
}
