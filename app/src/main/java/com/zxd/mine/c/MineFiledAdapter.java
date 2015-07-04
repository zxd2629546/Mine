package com.zxd.mine.c;

import android.content.Context;
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
public class MineFiledAdapter extends BaseAdapter{
    private MineField field;
    private GridView gridView;
    private Context context;

    public MineFiledAdapter(MineField field, GridView gridView, Context context){
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
        block.setImageResource(getRes(getItem(position), field.getShown(position)));
//        AbsListView.LayoutParams param = new AbsListView.LayoutParams(
//                gridView.getWidth() / field.getMap().getMapWidth(),
//                gridView.getWidth() / field.getMap().getMapWidth());
//        convertView.setLayoutParams(param);
//        convertView.setPadding(0, 0, 0, 0);
        return convertView;
    }

    private int getRes(int value, boolean shown){
        int id = 0;
        if(!shown){
            id = R.drawable.i00;
        } else if(value == -1){
            id = R.drawable.i12;
        } else if(value == 0){
            id = R.drawable.i09;
        } else {
            id = context.getResources().getIdentifier("i0" + value, "drawable", context.getPackageName());
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
