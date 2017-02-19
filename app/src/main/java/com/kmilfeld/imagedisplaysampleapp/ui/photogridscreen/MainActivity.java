package com.kmilfeld.imagedisplaysampleapp.ui.photogridscreen;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.kmilfeld.imagedisplaysampleapp.R;
import com.kmilfeld.imagedisplaysampleapp.databinding.ActivityMainBinding;
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
        final ActivityMainBinding binding = DataBindingUtil.setContentView( this, R.layout.activity_main );

        // Initialize the toolbar
        setSupportActionBar( binding.myToolbar );

        // Get the grid fragment
        final PhotoGridFragment gridFragment = (PhotoGridFragment) getFragmentManager().findFragmentById( R.id.photo_grid_fragment );

        // When the search button is clicked on the toolbar, we want
        // the grid fragment to display photo results for that text
        binding.searchButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                String text = binding.searchInput.getText().toString();

                Logger.verbose( "Getting images for text: " + text );

                // TODO karenm - figure out how to do data binding with a fragment
                gridFragment.displayPhotosFor( text );
            }
        } );
    }
}
