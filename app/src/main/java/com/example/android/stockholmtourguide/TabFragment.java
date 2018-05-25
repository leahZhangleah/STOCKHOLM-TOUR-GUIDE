package com.example.android.stockholmtourguide;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android.stockholmtourguide.data.StockholmContract.StockholmEntry;



public class TabFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private static Uri uriToQuery;
    private static final int LOADER_VERSION = 1;
    private ListAdapter listAdapter;
    private RecyclerView recyclerView;

    public static TabFragment newInstance(String tab){
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putString("tabTitle",tab);
        fragment.setArguments(args);
        return fragment;
    }
    public TabFragment(){
        //required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String tabTitle = getArguments()!=null? getArguments().getString("tabTitle"):null;
        if (tabTitle!=null){
            if (tabTitle.equals(getString(R.string.fragment_two))){
                uriToQuery = StockholmEntry.ATTRACTION_CONTENT_URI;
            }else if (tabTitle.equals(getString(R.string.fragment_three))){
                uriToQuery = StockholmEntry.HOTEL_CONTENT_URI;
            }else if (tabTitle.equals(getString(R.string.fragment_four))){
                uriToQuery = StockholmEntry.RESTAURANT_CONTENT_URI;
            }
        }

        getLoaderManager().initLoader(LOADER_VERSION,null,this);
        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) inflater.inflate(R.layout.list_view, container, false);
        listAdapter = new ListAdapter(null,getContext(),tabTitle);
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        return recyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scrollToPosition();
    }
    private void scrollToPosition(){
        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                recyclerView.removeOnLayoutChangeListener(this);
                final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                View viewAtPosition = layoutManager.findViewByPosition(MainActivity.currentPosition);
                if (viewAtPosition == null || layoutManager.isViewPartiallyVisible(viewAtPosition,false,true)){
                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            layoutManager.scrollToPosition(MainActivity.currentPosition);
                        }
                    });
                }
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = new String[]{StockholmEntry.TABLE_COLUMN_ID,StockholmEntry.TABLE_COLUMN_NAME,StockholmEntry.TABLE_COLUMN_PHOTO};
        switch (id){
            case LOADER_VERSION:
                return new CursorLoader(getContext(),uriToQuery,projection,null,null,null);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data!= null){
            listAdapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        listAdapter.swapCursor(null);
    }

}
