package com.google.firebase.udacity.friendlychat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;


public class chatroom extends AppCompatActivity {

    public static Button mcs100_close;
    public static Button mcs180_close;
    public static Button mihateDT_close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        mcs100_close = (Button)findViewById(R.id.cs100_close);
        mcs100_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(chatroom.this, R.style.Theme_AppCompat_Dialog_Alert);

                builder.setTitle("Confirm");
                builder.setMessage("Do you want to close this group chat?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        mcs100_close.setText("it is closed");
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        mcs180_close = (Button)findViewById(R.id.cs180_close);
        mcs180_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(chatroom.this, R.style.Theme_AppCompat_Dialog_Alert);

                builder.setTitle("Confirm");
                builder.setMessage("Do you want to close this group chat?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        mcs180_close.setText("it is closed");
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        mihateDT_close = (Button)findViewById(R.id.ihate_close);
        mihateDT_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(chatroom.this, R.style.Theme_AppCompat_Dialog_Alert);

                builder.setTitle("Confirm");
                builder.setMessage("Do you want to close this group chat?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        mihateDT_close.setText("it is closed");
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        Button btn = (Button)findViewById(R.id.cs100_chat);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(chatroom.this, CS100GROUP.class));
            }
        });


        Button btn3 = (Button)findViewById(R.id.ihateDT_chat);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(chatroom.this, ihateDTGROUP.class));
            }
        });

        Button btn4 = (Button)findViewById(R.id.cs180_chat);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cs180 = new Intent(chatroom.this, CS180GROUP.class);
                startActivity(cs180);
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.chatroom_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.messageboard_menu:
                Intent intent = new Intent(chatroom.this, MainActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }





}
