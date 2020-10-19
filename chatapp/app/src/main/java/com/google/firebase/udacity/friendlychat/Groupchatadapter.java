package com.google.firebase.udacity.friendlychat;
import android.content.Intent;
import android.widget.TextView;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;


import java.util.List;
//public class Groupchatadapter extends ArrayAdapter<String> {

   /* public Groupchatadapter(MainActivity mContext, int resource ,List<groupchatname> objects) {
        super(mContext, resource, objects);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.activity_chatroom, parent, false);
        }
        Button superbutt = (Button) convertView.findViewById(R.id.groupbutton);
        groupchatname gpn = getItem(position);
        superbutt.setText(gpn.getChatname());
        superbutt.setVisibility(View.VISIBLE);
        return convertView;

    }
}

private class Groupchatadapter extends BaseAdapter {

    // override other abstract methods here

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.activity_chatroom, container, false);
        }

        ((Button) convertView.findViewById(R.id.groupbutton)).setText(getItem(position).toString());
        return convertView;
    }
}

   public class Groupchatadapter extends ArrayAdapter<String> {
       private int layout;
       private List<String> mObjects;

       public Groupchatadapter(Context context, int resource, List<String> objects) {
           super(context, resource, objects);
           mObjects = objects;
           layout = resource;
       }

       @Override
       public View getView(final int position, View convertView, ViewGroup parent) {
           ViewHolder mainViewholder = null;
           if (convertView == null) {
               LayoutInflater inflater = LayoutInflater.from(getContext());
               convertView = inflater.inflate(R.layout.activity_chatroom, parent, false);
               ViewHolder viewHolder = new ViewHolder();
               viewHolder.button = (Button) convertView.findViewById(R.id.groupbutton);
               convertView.setTag(viewHolder);
           }
           mainViewholder = (ViewHolder) convertView.getTag();
           mainViewholder.button.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Toast.makeText(getContext(), "Button was clicked for list item " + position, Toast.LENGTH_SHORT).show();
               }
           });
           //mainViewholder.title.setText(getItem(position));

           return convertView;
       }

       public class ViewHolder {
           Button button;
       }
   }*/

public class Groupchatadapter extends ArrayAdapter<groupchatname> {
    public Groupchatadapter(MainActivity context, int resource, List<groupchatname> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.activity_groupchat, parent, false);
        }

        TextView photoImageView = (TextView) convertView.findViewById(R.id.groupbutton);
        photoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.groupyg = getItem(position).getChatname();
                Intent notagain = new Intent(getContext(), MainActivity.class);
                getContext().startActivity(notagain);
            }
        });
        String message = getItem(position).getChatname();

        photoImageView.setText(message);

        return convertView;
    }
}