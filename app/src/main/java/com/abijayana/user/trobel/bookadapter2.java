package com.abijayana.user.trobel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
public class bookadapter2 extends RecyclerView.Adapter<bookadapter2.MyViewHolder> {

    List<book> list;
    Context context;
    SharedPreferences sp;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv1,tv2,tv3;ImageView img;

        public MyViewHolder(View view) {
            super(view);

            img = (ImageView) view.findViewById(R.id.imageView2);
            tv1=(TextView)view.findViewById(R.id.textView2);
            tv2=(TextView)view.findViewById(R.id.textView3);

        }
    }
    public bookadapter2(List<book> list) {
        this.list = list;


    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simplelist, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Picasso.with(holder.itemView.getContext()).load(list.get(position).getImgurl()).into(holder.img);
        String r=list.get(position).getName();
        String t="";
        final Context con=holder.itemView.getContext();
        if(r.length()>10)for(int i=0;i<10;i++){t=t+r.charAt(i);}
         t=t+"...";
        holder.tv1.setText(t);
        holder.tv2.setText(list.get(position).getAuthor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

  sp=con.getSharedPreferences("TROBEL",Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();
                ed.putString("HAI",list.get(position).getNode());
                ed.commit();
                con.startActivity(new Intent(con,single.class));
            }
        });


    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}

