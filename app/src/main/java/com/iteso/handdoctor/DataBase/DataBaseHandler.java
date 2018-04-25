package com.iteso.handdoctor.DataBase;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.iteso.handdoctor.beans.CitasPaciente;
import com.iteso.handdoctor.beans.Medicamento;


public class DataBaseHandler extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Handdoctor.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    public static final String TABLE_MEDICAMENTO = "medicamentos";
    public static final String TABLE_CITASPACIENTE = "citasPaciente";
    // Medicamentos
    public static final String KEY_MED_ID = "idMed";
    public static final String KEY_MED_NAME = "nombre";
    public static final String KEY_MED_CANTIDAD = "cantidad";
    public static final String KEY_MED_ID_PACIENTE = "idPaciente";
    public static final String KEY_MED_DIAS_RESTANTES = "diasRestantes";
    public static final String KEY_MED_DOSIS = "Dosis";
    public static final String KEY_MED_TIPO= "Tipo";
     // CitasPaciente
     public static final String KEY_CP_IDCITA = "idCita";
    public static final String KEY_CP_ID_PACIENTE = "idPaciente";
    public static final String KEY_CP_NOMBRE_DOCTOR = "NombreDoctor";
    public static final String KEY_CP_FECHA = "Fecha";
    public static final String KEY_CP_LAT = "Latitud";
    public static final String KEY_CP_LONG = "Longitud";
    public static final String KEY_CP_ESPECIALIDAD = "Especialidad";
    public static final String KEY_HORA= "HORA";

    private static DataBaseHandler dataBaseHandler;

    private DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHandler getInstance(Context context) {
        if (dataBaseHandler == null)
            dataBaseHandler = new DataBaseHandler(context);
        return dataBaseHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEDICAMENTO_TABLE = "CREATE TABLE " + TABLE_MEDICAMENTO + "("
                + KEY_MED_ID + " TEXT,"
                + KEY_MED_NAME + " TEXT," + KEY_MED_CANTIDAD + " TEXT," + KEY_MED_ID_PACIENTE + " INTEGER,"
                + KEY_MED_DIAS_RESTANTES + " INTEGER," + KEY_MED_DOSIS + " TEXT," + KEY_MED_TIPO + " INTEGER);";
        db.execSQL(CREATE_MEDICAMENTO_TABLE);

        String CREATE_CITASPACIENTE_TABLE = "CREATE TABLE " + TABLE_CITASPACIENTE + "("
                + KEY_CP_IDCITA + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_CP_ID_PACIENTE + " INTEGER," + KEY_CP_NOMBRE_DOCTOR + " TEXT,"
                + KEY_CP_FECHA + " TEXT," + KEY_CP_LAT + " LONG," + KEY_CP_LONG + " LONG,"
                + KEY_CP_ESPECIALIDAD + " TEXT," + KEY_HORA + "TEXT);";
        db.execSQL(CREATE_CITASPACIENTE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                upgradeVersion2(db);
                break;
        }
    }

    public void upgradeVersion2(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE " + "TABLE_SUBJECT" + " ADD COLUMN " +
                "KEY_NEWCOLUMN" + " text not null");
    }
    public boolean insertInto(SQLiteDatabase db, String nombreTabla, Medicamento med, CitasPaciente cp) {
        try {
            if (!nombreTabla.equals(TABLE_MEDICAMENTO) && !nombreTabla.equals(TABLE_CITASPACIENTE)) {
                return false;
            }
            if (nombreTabla.equals(TABLE_MEDICAMENTO) && med != null) {
                db.execSQL("INSERT INTO " + nombreTabla + "(" + KEY_MED_ID + "," + KEY_MED_NAME + "," + KEY_MED_CANTIDAD + "," + KEY_MED_ID_PACIENTE + "," + KEY_MED_DIAS_RESTANTES
                        + "," + KEY_MED_DOSIS + "," + KEY_MED_TIPO + ")"
                        + "VALUES ('" + med.getIdMed() + "','" + med.getNombre() + "','" + med.getCantidad() + "',"
                        + med.getIdPaciente() + "," + med.getDiasRestantes() + ",'" + med.getDosis() + "'," + med.getTipo() + ")");
                return true;
            }
            if (nombreTabla.equals(TABLE_CITASPACIENTE) && cp != null) {
                db.execSQL("INSERT INTO " + nombreTabla + "(" + KEY_CP_ID_PACIENTE + "," + KEY_CP_NOMBRE_DOCTOR + "," + KEY_CP_FECHA + ","
                        + KEY_CP_LAT + "," + KEY_CP_LONG + "," + KEY_CP_ESPECIALIDAD + "," + KEY_HORA + ")"
                        + "VALUES (" + cp.getIdPaciente() + ",'" + cp.getNombreDoctor() + "','" + cp.getFecha() + "',"
                        + cp.getLat() + "," + cp.getLon() + ",'" + cp.getEspecialidad() + "','" + cp.getHora() + "')");
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }





}

