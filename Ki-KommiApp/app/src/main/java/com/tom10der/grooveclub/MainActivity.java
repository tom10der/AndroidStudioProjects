package com.tom10der.grooveclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
//------------------------------------------------------------------------------
    protected void onCreate(Bundle savedInstanceState) {
//------------------------------------------------------------------------------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Remove Notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        HandleClick hc = new HandleClick();
        findViewById(R.id.butHO).setOnClickListener(hc);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendEmailMessage("Info an Service Desk",
                        "Text",
                        "Bitte wähle Deinen E-Mail Client ");


                Snackbar.make(view, "Sende eMail an ServiceDesk", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

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
//------------------------------------------------------------------------------
    public void onBackPressed() {
//------------------------------------------------------------------------------
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
//------------------------------------------------------------------------------
    public boolean onCreateOptionsMenu(Menu menu) {
//------------------------------------------------------------------------------
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
//------------------------------------------------------------------------------
    public boolean onOptionsItemSelected(MenuItem item) {
//------------------------------------------------------------------------------
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        /*
        if (id == R.id.action_settings) {
            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override

//------------------------------------------------------------------------------
    public boolean onNavigationItemSelected(MenuItem item) {
//------------------------------------------------------------------------------
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle home action
            Intent myIntent = new Intent(MainActivity.this, WebViewActivity.class);
            myIntent.putExtra("url2","");
            MainActivity.this.startActivity(myIntent);

        } else if (id == R.id.nav_prog) {
            Intent myIntent = new Intent(MainActivity.this, WebViewActivity.class);
            myIntent.putExtra("url2","programm.shtml");
            MainActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_prodapp) {
           /* Intent myIntent = new Intent(MainActivity.this, WebViewActivity.class);
            myIntent.putExtra("url2","anfahrt.shtml");
            MainActivity.this.startActivity(myIntent); */
            Intent myIntent = new Intent(MainActivity.this, Map.class);
            MainActivity.this.startActivity(myIntent);

        } else if (id == R.id.nav_kontakt) {
            Intent myIntent = new Intent(MainActivity.this, Verein.class);
            MainActivity.this.startActivity(myIntent);
        /*} else if (id == R.id.nav_sponsoren) {
            Intent myIntent = new Intent(MainActivity.this, WebViewActivity.class);
            myIntent.putExtra("url2","gönner-sponsoren.shtml");
            MainActivity.this.startActivity(myIntent); */
        /*} else if (id == R.id.nav_gallery) {
            Intent myIntent = new Intent(MainActivity.this, WebViewActivity.class);
            myIntent.putExtra("url2","galerie.shtml");
            MainActivity.this.startActivity(myIntent); */
        /*} else if (id == R.id.nav_facebook) {
            Intent myIntent = new Intent(MainActivity.this, WebViewActivity.class);
            myIntent.putExtra("url2","facebook");
            MainActivity.this.startActivity(myIntent);*/
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }//------------------------------------------------------------------------------
    private class HandleClick implements View.OnClickListener {
        //------------------------------------------------------------------------------
        public void onClick(View arg0) {

            switch(arg0.getId()){
                case R.id.butHO:

                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.openDrawer(Gravity.LEFT); //Edit Gravity.End need API 14
                    break;

            }
        }
    }


//------------------------------------------------------------------------------
    public void sendEmailMessage(String subject, String body, String chooserTitle) {
//------------------------------------------------------------------------------
        Intent mailIntent = new Intent();
        mailIntent.setAction(Intent.ACTION_SEND);
        mailIntent.setType("message/rfc822");


        mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]
                        {"kikommi@kistler.com"
                        }
        );

        mailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        mailIntent.putExtra(Intent.EXTRA_TEXT, body);

        try {
            startActivity(Intent.createChooser(mailIntent, chooserTitle));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "Es ist keine Email App installiert",
                    Toast.LENGTH_SHORT).show();
        }
        //startActivity(Intent.createChooser(mailIntent, chooserTitle));
    }

    //------------------------------------------------------------------------------



}
