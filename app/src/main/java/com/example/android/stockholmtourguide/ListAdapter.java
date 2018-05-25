package com.example.android.stockholmtourguide;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private String tabTitle;

    public ListAdapter(Cursor c,Context context,String tabTitle) {
        this.context = context;
        mCursor = c;
        this.tabTitle = tabTitle;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        CardView cardView = (CardView) inflater.inflate(R.layout.one_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(cardView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mCursor!=null && mCursor.moveToPosition(position)){
            Log.i("ListAdapter", "the current position is: "+ position);
            int nameColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_NAME);
            String name = mCursor.getString(nameColumnIndex);
            int photoColumnIndex = mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_PHOTO);
            byte[] blob = mCursor.getBlob(photoColumnIndex);
            Bitmap photo = BitmapFactory.decodeByteArray(blob,0,blob.length);
            holder.imageView.setImageBitmap(photo);
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
            throw new IllegalStateException(context.getString(R.string.cursor_invalid_msg));
        }
        if (!mCursor.moveToPosition(position)) {
            throw new IllegalStateException(context.getString(R.string.cannot_move_cursor_msg) +" " + position );
        }
        return mCursor.getLong(mCursor.getColumnIndex(StockholmEntry.TABLE_COLUMN_ID));
    }

    public void swapCursor(Cursor newCursor){
        if (newCursor == mCursor){
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
            MainActivity.currentPosition = getAdapterPosition();
            Log.i("ListAdapter", "the current adapter position is: "+getAdapterPosition());
            Intent intent = new Intent(context,ScreenSlidePagerActivity.class);
            intent.putExtra(context.getString(R.string.tab_title_key),tabTitle);
            context.startActivity(intent);
        }
    }

}

