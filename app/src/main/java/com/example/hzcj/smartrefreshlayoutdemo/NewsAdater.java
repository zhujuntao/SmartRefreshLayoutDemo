package com.example.hzcj.smartrefreshlayoutdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HZCJ on 2017/8/23.
 * <pre>
 * author:zhu
 * e-mail:18911664778@163.com
 * desc:
 * version:
 * </pre>
 */

public class NewsAdater extends RecyclerView.Adapter<NewsAdater.ViewHolder> {
    private List<String> list;
    private Context context;
    public  OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        public  void  OnItemClick(View v,int position);
        public  void  OnItemLongClick(View v,int position);
    }
    public NewsAdater(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public NewsAdater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_title.setText(list.get(position));
         if (onItemClickListener!=null){
             holder.itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     onItemClickListener.OnItemClick(holder.itemView,position);
                 }
             });
             holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                 @Override
                 public boolean onLongClick(View v) {
                     onItemClickListener.OnItemLongClick(holder.itemView,position);
                     return true;
                 }
             });
         }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class  ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title= (TextView) itemView.findViewById(R.id.tv_title_item);
        }
    }

}
