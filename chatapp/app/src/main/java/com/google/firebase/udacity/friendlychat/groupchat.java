package com.google.firebase.udacity.friendlychat;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import java.util.ArrayList;
import java.util.Map;

public class groupchat extends MainActivity {
    private Button butt;
    private FirebaseDatabase mData;
    private DatabaseReference mData2;
    private StorageReference mData3;
    private DatabaseReference mStor;
    private DatabaseReference mStor2;
    private FirebaseStorage mGod;
    private ListView lview;
    private Groupchatadapter mGroupAdapter;
    private int flag = 0;
    private int flagger = 0;
    public static int god = 0;
    private int o = 0;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grouplist);
        Button butt = (Button) findViewById(R.id.creategroupbutton);
        mData = FirebaseDatabase.getInstance();
        mData2 = mData.getReference();
        final List<groupchatname> gcarray = new ArrayList<>();
        lview = (ListView) findViewById(R.id.messageListView2);

        StorageReference strf = FirebaseStorage.getInstance().getReference().child("god.txt");
        try {
            final File localfile = File.createTempFile("god", "txt");

            final FileReader fr = new FileReader(localfile);
            strf.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(groupchat.this, "Success!", Toast.LENGTH_LONG).show();
                    try {
                        god = fr.read() - 48;
                    }
                    catch (Exception e) {
                        Toast.makeText(groupchat.this,e.getMessage() + " 1", Toast.LENGTH_LONG).show();

                    }
                    localfile.deleteOnExit();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(groupchat.this,e.getMessage() + " 2",Toast.LENGTH_LONG).show();
                }
            });

        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage() + " 3", Toast.LENGTH_LONG).show();
        }


        //mGroupAdapter = new Groupchatadapter(groupchat.this, R.layout.activity_groupchat, gcarray);
        //lview.setAdapter(mGroupAdapter);
        mData2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull final DataSnapshot dataSnapshot, String parent) {
                if (dataSnapshot.getKey().startsWith("Group Chat ")) {
                    for (int i = 0; i < gcarray.size(); i++) {
                        if (gcarray.get(i).getChatname().equals(dataSnapshot.getKey()       )) {

                            flag = 1;
                        } else {
                        }
                    }
                    if (flag != 1) {
                        groupchatname gcr = new groupchatname(dataSnapshot.getKey(), 0, null);
                        gcarray.add(gcr);
                    }


                    String ster;
                    File newfl;

                    try {
                        newfl = File.createTempFile(gcarray.get(gcarray.size() - 1).getChatname(), ".txt");
                        final FileReader fr = new FileReader(newfl);
                        final BufferedReader bfr = new BufferedReader(fr);
                        newfl.deleteOnExit();
                        ster = gcarray.get(gcarray.size() - 1).getChatname();
                        ster = ster.concat("uid.txt");
                        int booler = ster.compareTo("Group Chat 0uid.txt");
                        StorageReference reffer = FirebaseStorage.getInstance().getReference().child(ster);
                        reffer.getFile(newfl).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                try {
                                    String mer = bfr.readLine();
                                    while (mer != null) {
                                        if (FirebaseAuth.getInstance().getCurrentUser().getUid().compareTo(mer) == 0) {
                                            flagger = 1;
                                        }
                                        mer = bfr.readLine();
                                    }

                                } catch (Exception uio) {
                                }

                                if (flagger != 1) {
                                    for (int q = 0; q < gcarray.size(); q++) {
                                        if (gcarray.get(q).getChatname().equals(dataSnapshot.getKey())) {
                                            gcarray.remove(q);
                                            break;
                                        }
                                    }
                                }try {
                                    bfr.close();
                                    fr.close();

                                }
                                catch (Exception et) {}

                                flagger = 0;
                                mGroupAdapter = new Groupchatadapter(groupchat.this, R.layout.activity_groupchat, gcarray);
                                lview.setAdapter(mGroupAdapter);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(groupchat.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    } catch (Exception ui) {
                    }
               /* if (dataSnapshot.getKey().startsWith("groupchat")) {
                    for (int k = 0; k < gcarray.size(); k++) {
                        if (dataSnapshot.child(gcarray.get(k).getChatname()).exists()) {
                            String f = dataSnapshot.child(gcarray.get(k).getChatname()).getValue().toString();
                            f = f.substring(22, 50);
                            String str = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            int g = f.compareTo(str);
                            if (f.compareTo(str) != 0) {
                                gcarray.remove(k);
                                k--;
                            }
                        }
                    }
                }*/
                    flag = 0;
                    //mGroupAdapter = new Groupchatadapter(groupchat.this, R.layout.activity_groupchat, gcarray);
                    // lview.setAdapter(mGroupAdapter);
                }
            }

            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String parent) {

            }

            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String parent) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(groupchat.this, "MMMMMMMMMM TOASTY", Toast.LENGTH_LONG).show();
            }
        });

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = "Group Chat ";
                String b = String.valueOf(god);
                a = a.concat(b);
                god++;
                try {
                    File lefile = File.createTempFile("god", "txt");
                    FileWriter fwr = new FileWriter(lefile);
                    BufferedWriter bfwr = new BufferedWriter(fwr);
                    bfwr.write(god + 48);
                    bfwr.close();
                    Uri uri = Uri.parse(lefile.toURI().toString());
                    FirebaseStorage.getInstance().getReference().child("god.txt").putFile(uri).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(groupchat.this, "bamboozed", Toast.LENGTH_LONG).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        }
                    });
                    lefile.deleteOnExit();
                }
                catch (Exception e) {Toast.makeText(groupchat.this, e.getMessage(), Toast.LENGTH_LONG).show(); }
                groupchatname gfr = new groupchatname(a, 0, null);
                gcarray.add(gfr);
                mStor = FirebaseDatabase.getInstance().getReference().child(gcarray.get(gcarray.size() - 1).getChatname());
                mData3 = FirebaseStorage.getInstance().getReference().child(gcarray.get(gcarray.size() - 1).getChatname() + "uid"  + ".txt");
                //mStor2 = FirebaseDatabase.getInstance().getReference().child("groupchatuids").child(gcarray.get(gcarray.size() - 1).getChatname());
                //mStor2.push().setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
                try {
                    final File filer = File.createTempFile("groupchatuids", "txt");
                    final FileWriter fgwr = new FileWriter(filer);
                    final BufferedWriter bfgwr = new BufferedWriter(fgwr);
                    mData3.getFile(filer).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            try {
                                fgwr.write(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                fgwr.close();
                                Uri ury = Uri.parse(filer.toURI().toString());
                                mData3.putFile(ury).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    }
                                });
                            }
                            catch (Exception io) {
                                Toast.makeText(groupchat.this, io.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            try {
                                fgwr.write(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                fgwr.write('\n');
                                fgwr.close();
                                Uri ury2 = Uri.parse(filer.toURI().toString());
                                mData3.putFile(ury2).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    }
                                });
                            }
                            catch (Exception io) {
                                Toast.makeText(groupchat.this, io.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
                catch (Exception o) {
                    Toast.makeText(groupchat.this, o.getMessage(), Toast.LENGTH_LONG).show();
                }
                FriendlyMessage msg = new FriendlyMessage("Welcome to the chat!", FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), "", FirebaseAuth.getInstance().getCurrentUser().getUid());
                mStor.push().setValue(msg);
                mGroupAdapter = new Groupchatadapter(groupchat.this, R.layout.activity_groupchat, gcarray);
                lview.setAdapter(mGroupAdapter);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.groupchat_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Messageboard_menu:
                groupyg = "messages";
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}