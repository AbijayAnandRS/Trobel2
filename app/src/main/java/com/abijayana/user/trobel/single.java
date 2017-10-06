package com.abijayana.user.trobel;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * Created by user on 29-03-2017.
 */

public class single  extends AppCompatActivity{

Firebase fr,ffm,im;ImageView iv;TextView tv1,tv2,tv3;SharedPreferences sp;String node;Button b1,b2;String price="0";double prce;
    ImageView fg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.sigle_item);
        iv=(ImageView)findViewById(R.id.imageView);
        tv1=(TextView)findViewById(R.id.tvsf1);
        tv2=(TextView)findViewById(R.id.tvsf2);
        tv3=(TextView)findViewById(R.id.tvsf3);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
        fg=(ImageView)findViewById(R.id.backdrop) ;
        ffm=new Firebase("https://trabel.firebaseio.com");
        fr=ffm.child("books");im=ffm.child("images");
        sp=this.getSharedPreferences("TROBEL", Context.MODE_PRIVATE);
        node=sp.getString("HAI","BOY");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prce=(double)stringtoint(price);
               new_dialog(0.1*prce,10);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prce=(double)stringtoint(price);
               new_dialog(0.8*prce,80);
            }
        });


        ffm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tv1.setText(String.valueOf(dataSnapshot.child("books").child(node).child("name").getValue()));
                tv2.setText(String.valueOf(dataSnapshot.child("books").child(node).child("Author").getValue()));
                Picasso.with(single.this).load(String.valueOf(dataSnapshot.child("books").child(node).child("imgurl").getValue())).into(iv);
                price=String.valueOf(dataSnapshot.child("books").child(node).child("price").getValue());
                tv3.setText(price);
                int cprze=stringtoint(price);
                if(cprze<=200){
                Picasso.with(single.this).load(String.valueOf(dataSnapshot.child("images").child("1234").child("url").getValue())).into(fg);
                }
                else if(cprze>200&&cprze<=300){
                    Picasso.with(single.this).load(String.valueOf(dataSnapshot.child("images").child("2356").child("url").getValue())).into(fg);

                }
                else{
                    Picasso.with(single.this).load(String.valueOf(dataSnapshot.child("images").child("3333").child("url").getValue())).into(fg);

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



    }
    public void new_dialog(double pprc,int percent){

        Dialog g =new Dialog(single.this);
        g.requestWindowFeature(Window.FEATURE_NO_TITLE);
        g.setContentView(R.layout.message);
        g.setCancelable(true);
        g.show();
        TextView tv=(TextView)g.findViewById(R.id.textViewp);
        Button b=(Button)g.findViewById(R.id.buttony);
        tv.setText("You have to pay "+percent+ "% of total which is MRP :"+pprc+"RS to proceed");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceed();
            }
        });


    }
    public int stringtoint(String s){
        String g="";int y;int i;

        for(i=0;(i<s.length()&&(s.charAt(i)!='/'));i++){

            y=(int)s.charAt(i);
            if((y>=48)&&(y<=57)){

                g=g+String.valueOf(s.charAt(i));

            }

        }



        if(g.compareTo("")==0)return 0;


        int h=Integer.parseInt(g);
        return h;


    }
    public void proceed(){



    }
}
