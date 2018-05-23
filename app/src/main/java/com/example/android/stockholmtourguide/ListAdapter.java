package com.example.android.stockholmtourguide;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android.stockholmtourguide.data.StockholmContract.StockholmEntry;

import java.util.ArrayList;

/**
 * Created by ceciliaHumlelu on 2018-02-09.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>  {
    private LayoutInflater inflater;
    private Context context;
    private Cursor mCursor;
    private int cursorPosition;

    public ListAdapter(Cursor c,Context context) {
        this.context = context;
        mCursor = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        CardView cardView = (CardView) inflater.inflate(R.layout.one_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(cardView);
        Log.i("ListAdapter","oncreateviewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mCursor!=null && mCursor.moveToPosition(position)){
            int nameColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_NAME);
            String name = mCursor.getString(nameColumnIndex);
            int photoColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_PHOTO);
            int photoId = mCursor.getInt(photoColumnIndex);
            Log.i("ListAdapter","onBindviewHolder with cursor"+mCursor);
            holder.imageView.setImageResource(photoId);
            holder.nameView.setText(name);
        }
    }

    @Override
    public int getItemCount() {
        if (mCursor!=null){
            return mCursor.getCount();
        }else{
            return 0;
        }
    }

    @Override
    public long getItemId(int position) {
        if (mCursor==null){
            throw new IllegalStateException("Cannot lookup item id when cursor is in invalid state.");
        }
        if (!mCursor.moveToPosition(position)) {
            throw new IllegalStateException("Could not move cursor to position " + position + " when trying to get an item id");
        }
        //Log.i("custom adapter","get item id" + mCursor.getLong(mRowIdColumn));
        return mCursor.getLong(mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_ID));
    }

    public void swapCursor(Cursor newCursor){
        if (newCursor == mCursor){
            Log.i("custom adapter","new cursor and old cursor is same");
            return;
        }
        if (newCursor!=null){
            mCursor=newCursor;
            notifyDataSetChanged();
        }else{
            notifyItemRangeRemoved(0,getItemCount());
            mCursor = null;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView nameView;
        public ViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.photo_view);
            nameView = itemView.findViewById(R.id.name_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            cursorPosition = getAdapterPosition();
            Intent intent = new Intent(context,ScreenSlidePagerActivity.class);
            intent.putExtra("cursorPosition",cursorPosition);
            Log.i("listadapter","the clicked position is: "+ cursorPosition);
            context.startActivity(intent);
        }
    }

}

