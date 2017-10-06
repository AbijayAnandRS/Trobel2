package com.abijayana.user.trobel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 12-01-2017.
 */

public class bookadapter extends ArrayAdapter<book> {
    Context context;
    int resource;
    ArrayList<book> objects;

    public bookadapter(Context context, int resource, ArrayList<book> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoldera vw=new ViewHoldera();
        if(convertView==null){
            LayoutInflater lf=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=lf.inflate(resource,null);
            vw.iv=(ImageView)convertView.findViewById(R.id.imageView2);
            vw.tv1=(TextView)convertView.findViewById(R.id.textView2);
            vw.tv2=(TextView)convertView.findViewById(R.id.textView3);
            vw.tv3=(TextView)convertView.findViewById(R.id.button42);

            convertView.setTag(vw);



        }
        else  vw=(ViewHoldera)convertView.getTag();
        Picasso.with(getContext()).load(objects.get(position).getImgurl()).into(vw.iv);
        String r=objects.get(position).getName();
        String t="";
        if(r.length()>10)for(int i=0;i<10;i++){t=t+r.charAt(i);}
        t=t+"...";
        vw.tv1.setText(t);
        vw.tv2.setText(objects.get(position).getAuthor());
        vw.tv3.setText(objects.get(position).getPrice());

        return convertView;
    }




    public class ViewHoldera{
        ImageView iv;
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;

    }
}





