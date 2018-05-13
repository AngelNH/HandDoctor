package com.iteso.handdoctor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.handdoctor.beans.Room;
import com.iteso.handdoctor.utils.AdapterRoomChat;

import java.util.ArrayList;

public class ActivityRoomChat extends AppCompatActivity {

    ListView chats;
    FloatingActionButton fab;
    String type, gen_id;

    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<String> id_chats = new ArrayList<>();
    DatabaseReference databaseReference;
    AdapterRoomChat adapterRoomChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_chat);

        chats = findViewById(R.id.activity_room_chat_list_view);
        fab = findViewById(R.id.activity_room_chat_fab);
        chats.clearChoices();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Log.e("ACT_ROOM","Rooms: "+rooms.toString());
        loadID();
        adapterRoomChat = new AdapterRoomChat(ActivityRoomChat.this,rooms,type);


        databaseReference.child("Salas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chats.clearChoices();
                rooms.clear();
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    id_chats.add(data.getKey());
                    Room room = data.getValue(Room.class);
                    Log.e("ACTIVITY_ROOM","Room: "+room.toString());
                    rooms.add(room);
                    adapterRoomChat.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.e("ACT_ROOM_RES","Rooms: "+rooms.toString());
        chats.setAdapter(adapterRoomChat);





        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRoomChat.this,ActivityContactos.class);
                startActivity(intent);
            }
        });

        chats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chats.clearChoices();
                Intent i = new Intent(ActivityRoomChat.this,ActivityChat.class);
                i.putExtra("ID_CHAT",id_chats.get(position));
                i.putExtra("DOC_NAME",rooms.get(position).getNameDoc());
                i.putExtra("PAC_NAME",rooms.get(position).getNamePac());
                i.putExtra("TYPE",type);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        chats.clearChoices();

    }
    public void loadID(){
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
        gen_id = sharedPreferences.getString("ID","555");
        type = sharedPreferences.getString("TYPE","555");
        sharedPreferences = null;
    }
}
