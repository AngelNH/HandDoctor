package com.iteso.handdoctor;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iteso.handdoctor.utils.AdapterMensajes;
import com.iteso.handdoctor.beans.*;

public class ActivityChat extends AppCompatActivity {
    Button send;
    EditText input;
    TextView nombre;
    RecyclerView recyclerView;
    ImageButton sendimg;

    private static final int PHOTO_SEND = 555;

    private AdapterMensajes adapter;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseParent;
    StorageReference storageReference;

    String chat_id;
    String docName, pacName, type;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("ID_CHAT")!= null)
        {
            chat_id = bundle.getString("ID_CHAT");
            docName = bundle.getString("DOC_NAME");
            pacName = bundle.getString("PAC_NAME");
            type = bundle.getString("TYPE");
        }

        send = findViewById(R.id.activity_chat_send);
        input = findViewById(R.id.activity_chat_input_text);
        nombre = findViewById(R.id.activity_chat_nombre);
        recyclerView = findViewById(R.id.activity_chat_rv_mensajes);
        sendimg = findViewById(R.id.activity_chat_send_img);


        if (type.equals(""+Paciente.DOCTOR))nombre.setText(docName);
        else if (type.equals(""+Paciente.PACIENTE))nombre.setText(pacName);

        databaseParent = FirebaseDatabase.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("Chats");//we add the fb part
        //chat_id= databaseReference.push().getKey();//TODO we are going to get it in the previoous layout.
        storageReference = FirebaseStorage.getInstance().getReference("images");

        adapter = new AdapterMensajes(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(l);
        recyclerView.setAdapter(adapter);


        sendimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(i,"Selecciona una foto"),PHOTO_SEND);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //adapter.addMensaje(new Message(nombre.getText().toString(),input.getText().toString(),"12:00","1"));
                MessageSender message = new MessageSender(nombre.getText().toString(),input.getText().toString(),"1",ServerValue.TIMESTAMP);
                databaseReference.child(chat_id).push().setValue(message);
                databaseParent.child("Salas").child(chat_id).child("lastmessage").setValue(message.getMessage());
                databaseParent.child("Salas").child(chat_id).child("hour").setValue(ServerValue.TIMESTAMP);
                input.setText("");
            }
        });
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });

        databaseReference.child(chat_id).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MessageReceiver m = dataSnapshot.getValue(MessageReceiver.class);
                if(m.getName().equals(nombre.getText().toString())){
                    adapter.addForeingMensaje(m);
                    return;
                }
                adapter.addMensaje(m);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_SEND && resultCode == RESULT_OK){
            Uri u =data.getData();
            final StorageReference fotoReference = storageReference.child(u.getLastPathSegment());
            fotoReference.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri u = taskSnapshot.getDownloadUrl();
                    MessageSender m = new MessageSender(nombre.getText().toString(),"Te han enviado una foto","2",u.toString(),ServerValue.TIMESTAMP);
                    databaseReference.push().setValue(m);
                }
            });
        }
    }

    private  void setScrollbar(){
        recyclerView.scrollToPosition(adapter.getItemCount()-1);
    }
}
