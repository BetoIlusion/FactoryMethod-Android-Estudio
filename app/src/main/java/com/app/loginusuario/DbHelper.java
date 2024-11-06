package com.app.loginusuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    // Nombre de la base de datos
    private static final String DATABASE_NAME = "app.db";
    private static final int DATABASE_VERSION = 1;

    // Nombres de las tablas
    private static final String TABLE_ROL = "rol";
    private static final String TABLE_USUARIO = "usuario";

    // Columnas de la tabla rol
    private static final String COLUMN_ROL_ID = "id";
    private static final String COLUMN_ROL_NOMBRE = "nombre";

    // Columnas de la tabla usuario
    private static final String COLUMN_USUARIO_NOMBRE = "nombre";
    private static final String COLUMN_USUARIO_EMAIL = "email";
    private static final String COLUMN_USUARIO_CONTRA = "contra";
    private static final String COLUMN_USUARIO_ROL = "rol_id"; // Relación con la tabla rol

    // Crear las tablas
    private static final String CREATE_TABLE_ROL = "CREATE TABLE " + TABLE_ROL + " ("
            + COLUMN_ROL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ROL_NOMBRE + " TEXT NOT NULL)";

    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE " + TABLE_USUARIO + " ("
            + COLUMN_USUARIO_NOMBRE + " TEXT NOT NULL, "
            + COLUMN_USUARIO_EMAIL + " TEXT NOT NULL UNIQUE, "
            + COLUMN_USUARIO_CONTRA + " TEXT NOT NULL, "
            + COLUMN_USUARIO_ROL + " INTEGER, "
            + "FOREIGN KEY(" + COLUMN_USUARIO_ROL + ") REFERENCES " + TABLE_ROL + "(" + COLUMN_ROL_ID + "))";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ROL);
        db.execSQL(CREATE_TABLE_USUARIO);
        insertDefaultRoles(db); // Inserta roles predeterminados
    }

    private void insertDefaultRoles(SQLiteDatabase db) {
        addRol(db, "Administrador");
        addRol(db, "Cajero");
    }

    private void addRol(SQLiteDatabase db, String nombre) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ROL_NOMBRE, nombre);
        db.insert(TABLE_ROL, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROL);
        onCreate(db);
    }

    // Método para agregar un usuario
    public long addUsuario(String nombre, String email, String contra, int rolId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USUARIO_NOMBRE, nombre);
        values.put(COLUMN_USUARIO_EMAIL, email);
        values.put(COLUMN_USUARIO_CONTRA, contra);
        values.put(COLUMN_USUARIO_ROL, rolId);
        return db.insert(TABLE_USUARIO, null, values);
    }

    // Método para obtener todos los usuarios
    public Cursor getAllUsuarios() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USUARIO, null);
    }

    // Método para obtener todos los roles
    public Cursor getAllRoles() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_ROL, null);
    }

}
