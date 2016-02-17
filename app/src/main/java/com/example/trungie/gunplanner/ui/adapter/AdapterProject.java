package com.example.trungie.gunplanner.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.trungie.gunplanner.R;
import com.example.trungie.gunplanner.object.DataBase;
import com.example.trungie.gunplanner.object.Project;

import java.util.ArrayList;
import java.util.List;

public class AdapterProject extends BaseAdapter{

    private Context context;
    private List<Project> dataList;

    public AdapterProject(Context context, DataBase data) {
        this.context = context;
        if(data != null) {
            this.dataList = data.projectList;
        } else {
            this.dataList = new ArrayList<Project>();
        }
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_project, null);
            holder.layout = (RelativeLayout) convertView.findViewById(R.id.layout);
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(position % 2 == 0) {
            holder.layout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_odd));
        } else {
            holder.layout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg_even));
        }
        holder.tv.setText(dataList.get(position).getName());
        return convertView;
    }

    public void setData(DataBase db) {
        if(db != null) {
            this.dataList = db.projectList;
        } else {
            this.dataList = new ArrayList<Project>();
        }
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        RelativeLayout layout;
        TextView tv;
        ImageView iv;
    }
}
