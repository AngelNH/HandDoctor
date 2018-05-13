package com.iteso.handdoctor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.EditText;

import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.handdoctor.beans.Paciente;
import com.iteso.handdoctor.beans.User;

import java.util.ArrayList;


public class ActivityLogin extends AppCompatActivity {
    TextInputEditText user,password;
    SharedPreferences sharedPreferences;
    User userLog;
    ArrayList<User> users;
    int state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.activity_login_user);
        password = findViewById(R.id.activity_login_pass);


        users = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.child("User").getChildren()){
                    userLog = snapshot.getValue(User.class);
                    users.add(userLog);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void logInBtn(View view){

        if (compareToFirebase(user.getText().toString(),password.getText().toString())){
            if(state == Paciente.DOCTOR || state == Paciente.SECRETARIA) {
                Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                startActivity(intent);
                finish();
            }else if(state == Paciente.PACIENTE){
                //TODO se va a la pantalla principal del Paciente.
            }
        }else{
            Toast.makeText(this,"incorrect password or user, try again",Toast.LENGTH_SHORT).show();
        }

    }
    public void signInLbl(View view){
        Intent intent = new Intent(ActivityLogin.this,ActivityRegister.class);
        startActivity(intent);
        finish();

    }
    public boolean compareToFirebase(String user, String password){
        sharedPreferences = getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
        for(int i = 0;i<users.size();i++){
            Log.e("FIREBASE_USER_OUTSIDE",users.get(i).toString());
            if (users.get(i).getUser().equals(user) && users.get(i).getPassword().equals(password)){
                sharedPreferences = getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("NAME",users.get(i).getUser());
                editor.putString("PWD",users.get(i).getPassword());
                editor.putString("ID",users.get(i).getId());
                editor.putString("TYPE",""+state);
                editor.putBoolean("LOGGED",true);
                editor.apply();
                state = users.get(i).getState();
                return true;
            }
        }
        return false;
    }
}
