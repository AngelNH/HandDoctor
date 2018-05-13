package com.iteso.handdoctor;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iteso.handdoctor.beans.Doctor;
import com.iteso.handdoctor.beans.Expediente;
import com.iteso.handdoctor.beans.Medicamento;
import com.iteso.handdoctor.beans.Paciente;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class ActivityRecetar extends AppCompatActivity {
    EditText id_pac;
    TextView name;
    TextInputEditText medicament, cantidad, dosis, dias;
    RadioButton jarabe, pastillas, inyecc;
    Button recetar;
    String id_doc;

    DatabaseReference databaseReference;
    ArrayList<Paciente> pacientes ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetar);

        id_pac = findViewById(R.id.activity_recetar_edt_id);
        medicament = findViewById(R.id.activity_recetar_nombre_medicamento);
        cantidad = findViewById(R.id.activity_recetar_cantidad);
        dosis = findViewById(R.id.activity_recetar_dosis);
        dias = findViewById(R.id.activity_recetar_dias);
        name = findViewById(R.id.activity_recetar_txt_name);
        recetar = findViewById(R.id.activity_recetar_add);

        jarabe = findViewById(R.id.activity_recetar_jarabe);
        pastillas = findViewById(R.id.activity_recetar_pastilla);
        inyecc = findViewById(R.id.activity_recetar_iny);


        loadID();
        Log.e("ACTIVITY_RECETAR","id_doc"+id_doc);
        pacientes = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.child("Doctor").child(id_doc).child("Pacientes").getChildren()){
                    Paciente p = data.getValue(Paciente.class);

                    Log.e("ACTIViTY_RECETAR","Paciente"+p.toString());
                    Log.e("ACTIViTY_RECETAR","id_doc_inside"+id_doc);
                    pacientes.add(data.getValue(Paciente.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        id_pac.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s+"";
                for (int i = 0;i<pacientes.size();i++){
                    if (pacientes.get(i).getPhone().equals(text)){
                        name.setText(pacientes.get(i).getName());
                        return;
                    }
                    name.setText("(Patient Name)");
                }
                //name.setText("Not Founded");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        recetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveonFirebase();
                Toast.makeText(ActivityRecetar.this,"El medicamento fue añadido con éxito" ,Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
    public void saveonFirebase(){
        int days=0;
        Medicamento medicamento = new Medicamento();
        medicamento.setNombre(medicament.getText().toString());
        medicamento.setCantidad(cantidad.getText().toString());
        medicamento.setDosis(dosis.getText().toString());
        try{
            days = Integer.parseInt(dias.getText().toString());
        }catch (Exception e){}
        medicamento.setDiasRestantes(days);
        int tipo = Medicamento.MEDICAMENTO_PASTILLA;
        if (jarabe.isChecked())tipo = Medicamento.MEDICAMENTO_JARABE;
        else if (pastillas.isChecked())tipo = Medicamento.MEDICAMENTO_PASTILLA;
        else if (inyecc.isChecked())tipo = Medicamento.MEDICAMENTO_INYECCION;
        medicamento.setTipoMedicamento(tipo);

        //fecha expiración
        Date today = Calendar.getInstance().getTime();
        Date expiration = sumarDiasAFecha(today,medicamento.getDiasRestantes());
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String fecha = df.format(expiration);
        medicamento.setExpiration(fecha);
        String med_id = databaseReference.child("Paciente").child(id_pac.getText().toString()).child("Medicamento").push().getKey();
        databaseReference.child("Paciente").child(id_pac.getText().toString()).child("Medicamento").child(med_id).setValue(medicamento);
    }

    public static Date sumarDiasAFecha(Date fecha, int dias){
        if (dias==0) return fecha;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    public void loadID(){
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.HANDDOCTOR_PREFERENCES",MODE_PRIVATE);
        id_doc = sharedPreferences.getString("ID","555");
        sharedPreferences = null;
    }
}
