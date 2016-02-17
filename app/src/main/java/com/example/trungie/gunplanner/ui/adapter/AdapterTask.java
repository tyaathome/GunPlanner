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
import com.example.trungie.gunplanner.object.Project;
import com.example.trungie.gunplanner.object.Task;

import java.util.ArrayList;
import java.util.List;

public class AdapterTask extends BaseAdapter{

    private Context context;
    private List<Task> datalist = null;

    public AdapterTask(Context context, Project project) {
        this.context = context;
        if(project != null) {
            datalist = project.tasks;
        } else {
            datalist = new ArrayList<Task>();
        }
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
        Task task = datalist.get(position);
        if(task.getIsCompleted()) {
            holder.iv.setVisibility(View.VISIBLE);
        } else {
            holder.iv.setVisibility(View.INVISIBLE);
        }
        holder.tv.setText(datalist.get(position).getName());
        return convertView;
    }

    public void setData(Project project) {
        if(project != null) {
            datalist = project.tasks;
        } else {
            datalist = new ArrayList<Task>();
        }
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        RelativeLayout layout;
        TextView tv;
        ImageView iv;
    }
}
