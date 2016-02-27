package com.example.lenovo.carzz.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.carzz.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by lenovo on 2/27/2016.
 */
public class DescriptionAdapter extends RecyclerView.Adapter<DescriptionAdapter.MyViewHolder> {
    private final LayoutInflater inflater;
    private Context context;
    List<String> descriptionList = Collections.emptyList();

    public void setDescriptionList(List<String> descriptionList) {
        this.descriptionList = descriptionList;
        notifyItemRangeChanged(0, descriptionList.size());
    }

    public DescriptionAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.description, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String currentDesc = descriptionList.get(position);
        holder.textView.setText(currentDesc);
        holder.imageView.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return descriptionList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.listIcon);
            textView = (TextView) itemView.findViewById(R.id.listText);
        }
    }
}
