package com.abijayana.user.trobel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import static android.util.JsonToken.NULL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static View rootview1a;int fgh=0;RelativeLayout llout;
    public static View rootview2a;
    public static View rootview3a;
    public static View rootview4a;
    ArrayList<book> tlist,nlist,clist,p1list,p2list;bookadapter2 adp1,adp2,adp3;
    bookadapter tboook;GridView gv;
    LayoutInflater lf1,lf2,lf3,lf4,lf5,lf6;
    TabLayout tabLayout;SharedPreferences sp;
    public SectionsPagerAdapter mSectionsPagerAdapter;bookadapter1 bdp;

    RecyclerView rv,cbkv,p1,p2;
    public ViewPager mViewPager;Firebase fr,im;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        fr=new Firebase("https://trabel.firebaseio.com/books");
        im=new Firebase("https://trabel.firebaseio.com");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final AppBarLayout lli=(AppBarLayout) findViewById(R.id.clyout) ;
        setSupportActionBar(toolbar);
        tlist=new ArrayList<book>();
        nlist=new ArrayList<book>();
        clist=new ArrayList<book>();
        p1list=new ArrayList<book>();
        p2list=new ArrayList<book>();

        sp=this.getSharedPreferences("TROBEL",Context.MODE_PRIVATE);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.container12);


        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout = (TabLayout)findViewById(R.id.tabs12);
        tabLayout.setupWithViewPager(mViewPager);
        lf1=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE) ;
        lf2=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE) ;
        lf3=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE) ;
        lf4=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE) ;
        lf5=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE) ;
        lf6=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE) ;
        rootview1a=lf1.inflate(R.layout.bgy1,null);
        rootview2a=lf2.inflate(R.layout.bgy,null);
  ;
        bdp=new bookadapter1(nlist);
        adp1=new bookadapter2(clist);
        adp2=new bookadapter2(p1list);
        adp3=new bookadapter2(p2list);

        rv=(RecyclerView)rootview1a.findViewById(R.id.recyclerview1);
        cbkv=(RecyclerView)rootview1a.findViewById(R.id.recyclerview2) ;
        p1=(RecyclerView)rootview1a.findViewById(R.id.recyclerview3);
        p2=(RecyclerView)rootview1a.findViewById(R.id.recyclerview4) ;
        gv=(GridView)rootview2a.findViewById(R.id.gridView1);

        tboook=new bookadapter(MainActivity.this,R.layout.griditem,tlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager m1LayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager m2LayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager m3LayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        rv.setLayoutManager(mLayoutManager);
        rv.setAdapter(bdp);

        rv.setItemAnimator(new DefaultItemAnimator());
        gv.setAdapter(tboook);
        cbkv.setLayoutManager(m1LayoutManager);
        cbkv.setAdapter(adp1);
        p1.setLayoutManager(m2LayoutManager);
        p1.setAdapter(adp2);
        p2.setLayoutManager(m3LayoutManager);
        p2.setAdapter(adp3);
        cbkv.setItemAnimator(new DefaultItemAnimator());
        p1.setItemAnimator(new DefaultItemAnimator());p2.setItemAnimator(new DefaultItemAnimator());
        Query av=fr.orderByKey();
        Query iv=im.child("images").orderByKey();
        iv.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nlist.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                   book b=new book();
                    b.setImgurl(String.valueOf(dataSnapshot1.child("url").getValue()));
                    b.setName(String.valueOf(dataSnapshot1.child("title").getValue()));
                    b.setAuthor(String.valueOf(dataSnapshot1.child("text").getValue()));
                    b.setNode(String.valueOf(dataSnapshot1.getKey()));
                    nlist.add(b);

                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        gv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int distance=gv.getScrollY();
                lli.scrollBy(0,distance);

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        av.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                tlist.clear();
                p1list.clear();
                p2list.clear();

                clist.clear();int price;String prz;
              


                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    book b=new book();

                    b.setName(String.valueOf(dataSnapshot1.child("name").getValue()));
                    b.setImgurl(String.valueOf(dataSnapshot1.child("imgurl").getValue()));
                    b.setAuthor(String.valueOf(dataSnapshot1.child("Author").getValue()));
                    prz=String.valueOf(dataSnapshot1.child("price").getValue());
                    b.setPrice(prz);
                    b.setNode(String.valueOf(dataSnapshot1.getKey()));
                    tlist.add(b);
                    price=stringtoint(prz);
                    switch(price/100){
                        case 1:clist.add(b);break;
                        case 2:p1list.add(b);break;
                        default:p2list.add(b);break;


                    }





                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        im.child("images").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                bdp.notifyDataSetChanged();
                rv.scrollToPosition(1);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                bdp.notifyDataSetChanged();
                rv.scrollToPosition(1);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                bdp.notifyDataSetChanged();  rv.scrollToPosition(1);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                bdp.notifyDataSetChanged();  rv.scrollToPosition(1);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        fr.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                tboook.notifyDataSetChanged();
                bdp.notifyDataSetChanged();
                adp1.notifyDataSetChanged();
                adp2.notifyDataSetChanged();
                adp3.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                tboook.notifyDataSetChanged();
                bdp.notifyDataSetChanged();
                adp1.notifyDataSetChanged();
                adp2.notifyDataSetChanged();
                adp3.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                tboook.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                tboook.notifyDataSetChanged();
                bdp.notifyDataSetChanged();
                adp1.notifyDataSetChanged();
                adp2.notifyDataSetChanged();
                adp3.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });






        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public  static class abi1a extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater1, ViewGroup container,
                                 Bundle savedInstanceState) {



            return rootview2a;
        }

    }
    public  static class abi2a extends Fragment{

        @Override
        public View onCreateView(LayoutInflater inflater2, ViewGroup container,
                                 Bundle savedInstanceState) {


            return rootview1a;
        }

    }




    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:

                    return new abi1a();


                case 1:

                    return new abi2a();

                default:
                    //this page does not exists
                    return null;
            }
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

        }

        @Override
        public int getCount() {

            return 2;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "ALL";
                case 1:
                    return "CATEGORIES";



            }
            return null;
        }
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
    void inside_listiem(ListView lv,final ArrayList<book> listt){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              SharedPreferences.Editor ed=sp.edit();
                ed.putString("HAI",listt.get(position).getNode());
                ed.commit();
                goclass(single.class);
            }
        });



    }


    public void goclass(Class g){
        Intent i=new Intent(MainActivity.this,g);
        startActivity(i);

    }

}
