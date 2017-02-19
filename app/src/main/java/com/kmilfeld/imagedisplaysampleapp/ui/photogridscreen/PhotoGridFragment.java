package com.kmilfeld.imagedisplaysampleapp.ui.photogridscreen;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.kmilfeld.imagedisplaysampleapp.R;
import com.kmilfeld.imagedisplaysampleapp.data.PhotoManager;
import com.kmilfeld.imagedisplaysampleapp.ui.fullphotoscreen.FullScreenPhotoActivity;

/**
 * Fragment for displaying photos to the user in a grid
 *
 * Created by karenm on 2/4/17.
 */
public class PhotoGridFragment extends Fragment
{
    /**
     * The adapter used to show data for the grid view
     */
    private ImageAdapter mAdapter;

    /**
     * Called to have the fragment instantiate its user interface view.
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     *                  The fragment should not add the view itself, but this can be used to generate the
     *                  LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return
     */
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        View fragmentView = inflater.inflate( R.layout.photo_list_fragment, null );

        GridView gridview = (GridView) fragmentView.findViewById( R.id.gridview );
        mAdapter = new ImageAdapter( getActivity() );
        gridview.setAdapter( mAdapter );

        gridview.setOnItemClickListener( new AdapterView.OnItemClickListener()
        {
            public void onItemClick( AdapterView<?> parent, View v,
                    int position, long id )
            {
                FullScreenPhotoActivity.launch( getActivity(), mAdapter.getItem( position ).getFullSizeUrl() );
            }
        } );

        return fragmentView;
    }

    /**
     * Displays photos relating to the given text string
     * @param text The string to search for photos for
     */
    public void displayPhotosFor( String text )
    {
        PhotoManager.getInstance().startPhotoDataUpdate( text, mAdapter );
    }

}
