package cu.serpro.campi;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import cu.serpro.campi.utilidades.utilidades;

import java.util.Calendar;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
  private static final String TAG = "CAMPI";
  private static final String DATABASE_NAME = "campi";

  public ConexionSQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, 1);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
//      db.execSQL(utilidades.CREAR_CAMPISMOS);
//      db.execSQL(utilidades.CREAR_FOTOS);
//      db.execSQL(utilidades.CREAR_LUGARES);
//      db.execSQL(utilidades.CREAR_OFICINASRESERVAS);
//      db.execSQL(utilidades.CREAR_PRECIO);
//      db.execSQL(utilidades.CREAR_SERVICIOS);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//      db.execSQL("DROP TABLE IF EXISTS "+utilidades.TABLA_OFICINAS);
//      db.execSQL("DROP TABLE IF EXISTS "+utilidades.TABLA_CAMPISMOS);
//      db.execSQL("DROP TABLE IF EXISTS "+utilidades.TABLA_LUGARES);
//      db.execSQL("DROP TABLE IF EXISTS "+utilidades.TABLA_SERVICIOS);
//      db.execSQL("DROP TABLE IF EXISTS "+utilidades.TABLA_FOTOS);
//      db.execSQL("DROP TABLE IF EXISTS "+utilidades.TABLA_PRECIOS);
  }



}
