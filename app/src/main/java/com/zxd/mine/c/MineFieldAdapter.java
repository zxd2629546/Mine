package com.zxd.mine.c;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.zxd.mine.R;

/**
 * Created by zxd on 2015/7/3.
 */
public class MineFieldAdapter extends BaseAdapter{
    private MineField field;
    private GridView gridView;
    private Context context;

    public MineFieldAdapter(MineField field, GridView gridView, Context context){
        super();
        this.gridView = gridView;
        this.context = context;
        this.field = field;
    }


    @Override
    public int getCount() {
        return field.getCount();
    }

    @Override
    public Integer getItem(int position) {
        return field.getMap().getValue(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.mine_block, null);
        }
        ImageView block = (ImageView)convertView.findViewById(R.id.mine_block);
        int id = getRes(getItem(position), field.getShown(position));
        if (id != 0){
            block.setImageResource(id);
        } else {
            block.setImageDrawable(new BitmapDrawable());
        }
        if (field.isMine(position)){
            block.setBackgroundColor(Color.parseColor("#40e0d0"));
        } else {
            block.setBackgroundColor(Color.parseColor("#d3d3d3"));
        }
        return convertView;
    }

    private int getRes(int value, boolean shown){
        int id = 0;
        if(!shown){
            id = R.drawable.block;
        } else if(value == -1){
            id = R.drawable.mine;
        } else if(value > 0){
            id = context.getResources().getIdentifier("num_" + value, "drawable", context.getPackageName());
        }
        return id;
    }

    public void reset(int[] scale){
        field.reset(scale);
        notifyDataSetChanged();
    }
    public int update(int position){
        int ret = field.update(position);
        notifyDataSetChanged();
        return ret;
    }
}
