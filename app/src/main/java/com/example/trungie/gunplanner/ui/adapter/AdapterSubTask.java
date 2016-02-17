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
import com.example.trungie.gunplanner.object.SubTask;

import java.util.List;

public class AdapterSubTask extends BaseAdapter{

    private Context context = null;
    private List<SubTask> datalist = null;

    public AdapterSubTask(Context context, List<SubTask> subTasks) {
        this.context = context;
        this.datalist = subTasks;
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
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
        SubTask subTask = datalist.get(position);
        if(subTask.getIsCompleted()) {
            holder.iv.setVisibility(View.VISIBLE);
        } else {
            holder.iv.setVisibility(View.INVISIBLE);
        }
        holder.tv.setText(datalist.get(position).getName());
        return convertView;
    }

    public void setData(List<SubTask> tasks) {
        this.datalist = tasks;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        RelativeLayout layout;
        TextView tv;
        ImageView iv;
    }
}
