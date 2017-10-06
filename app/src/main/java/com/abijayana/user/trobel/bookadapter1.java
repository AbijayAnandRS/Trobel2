package com.abijayana.user.trobel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by user on 12-01-2017.
 */

public class bookadapter1 extends RecyclerView.Adapter<bookadapter1.MyViewHolder> {

    List<book> list;
    Context context;
    SharedPreferences sp;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  tv1,tv2;ImageView img;

        public MyViewHolder(View view) {
            super(view);

            img = (ImageView) view.findViewById(R.id.imagevwpic);
            tv1=(TextView)view.findViewById(R.id.textView4);
            tv2=(TextView)view.findViewById(R.id.textView5);

        }
    }
    public bookadapter1(List<book> list) {
        this.list = list;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simpleitempic, parent, false);


        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Picasso.with(holder.itemView.getContext()).load(list.get(position).getImgurl()).into(holder.img);
        holder.tv1.setText(list.get(position).getName());
        holder.tv2.setText(list.get(position).getAuthor());



    }
    @Override
    public int getItemCount() {
        return list.size();
    }





}
