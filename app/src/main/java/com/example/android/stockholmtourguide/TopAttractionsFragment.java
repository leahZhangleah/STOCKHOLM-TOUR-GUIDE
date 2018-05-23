package com.example.android.stockholmtourguide;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.stockholmtourguide.data.StockholmContract.StockholmEntry;



public class TopAttractionsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final Uri attractionUri = StockholmEntry.ATTRACTION_CONTENT_URI;
    private static final int LOADER_VERSION = 1;
    private ListAdapter listAdapter;
    public TopAttractionsFragment(){
        //required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getLoaderManager().initLoader(LOADER_VERSION,null,this);
        // Inflate the layout for this fragment
        RecyclerView view = (RecyclerView) inflater.inflate(R.layout.list_view, container, false);
        listAdapter = new ListAdapter(null,getContext());
        view.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        view.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = new String[]{StockholmEntry.TABLE_COLUMN_ID,StockholmEntry.TABLE_COLUMN_NAME,StockholmEntry.TABLE_COLUMN_PHOTO};
        switch (id){
            case LOADER_VERSION:
                Log.i("Topattractionfragment","oncreateloader");
                return new CursorLoader(getContext(),attractionUri,projection,null,null,null);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data!= null){
            Log.i("Topattractionfragment","onloadfinished");
            listAdapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.i("Topattractionfragment","onloaderreset");
        listAdapter.swapCursor(null);
    }
}
