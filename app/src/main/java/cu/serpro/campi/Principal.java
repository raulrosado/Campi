package cu.serpro.campi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cu.serpro.campi.ConexionSQLiteHelper;
import cu.serpro.campi.entidades.Campismos;
import cu.serpro.campi.entidades.Provincias;
import cu.serpro.campi.utilidades.utilidades;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Principal extends AppCompatActivity {
    private static final int PICK_IMAGE = 0;
    ImageView imageView;
    ConexionSQLiteHelper conn;
    public String TAG = "CAMPILOG";
    RecyclerView RecyclercampismoPopulares,recyclerProvincias;
    ArrayList<Campismos> listaCampismos;
    ArrayList<Provincias> listaProvincias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        getSupportActionBar().hide();

        imageView = findViewById(R.id.imageView);
        RecyclercampismoPopulares = findViewById(R.id.campismoPopulares);
        recyclerProvincias = findViewById(R.id.recyclerProvincias);
        //PARA MOSTRAR LAS VENTAS RAPIDAS
        RecyclercampismoPopulares.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerProvincias.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        //hago la coneccion con la BD
        conn = new ConexionSQLiteHelper(getApplicationContext());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Search.class);
                startActivity(intent);
            }
        });

        //cargo los campismos
        loadCampismos();

        //cargo las provincias
        loadProvincias();
    }

    private void loadCampismos() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Campismos campismos = null;
        listaCampismos = new ArrayList<Campismos>();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + utilidades.TABLA_CAMPISMOS +" WHERE "+ utilidades.Camp_categoria + "= '1'", null);
            while (cursor.moveToNext()) {
                Log.d(TAG, cursor.getString(1));
                campismos = new Campismos();
                campismos.setId(cursor.getInt(0));
                campismos.setTitulo(cursor.getString(1));
                campismos.setDescripcion(cursor.getString(2));
                campismos.setImagen(cursor.getString(3));
                campismos.setLocalizacion(cursor.getString(4));
                campismos.setTelefono(cursor.getString(5));
                campismos.setUbicacion(cursor.getString(6));
                campismos.setDireccion(cursor.getString(7));
                campismos.setServicios(cursor.getString(8));
                campismos.setCategoria(cursor.getString(9));
                campismos.setTipoturismo(cursor.getString(10));
                campismos.setMunicipio(cursor.getString(11));
                campismos.setProvincia(cursor.getString(12));
                campismos.setEstado(cursor.getInt(13));
                listaCampismos.add(campismos);
            }

            AdapterCampismosPopulares adapterCampismosPopulares = new AdapterCampismosPopulares(listaCampismos, getApplicationContext());
            adapterCampismosPopulares.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Seleccion. " + listaCampismos.get(RecyclercampismoPopulares.getChildAdapterPosition(view)).getTitulo(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Principal.this, DetallesCampismo.class);
                    i.putExtra("idCampismo", listaCampismos.get(RecyclercampismoPopulares.getChildAdapterPosition(view)).getId());
                    startActivity(i);
                    Log.d(TAG, listaCampismos.get(RecyclercampismoPopulares.getChildAdapterPosition(view)).getId().toString());
                }
            });
            RecyclercampismoPopulares.setAdapter(adapterCampismosPopulares);
        }catch (Exception e){}
    }


    private void loadProvincias(){
        SQLiteDatabase db = conn.getReadableDatabase();
        Provincias provincias = null;
        listaProvincias = new ArrayList<Provincias>();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + utilidades.TABLA_PROVINCIAS , null);
            while (cursor.moveToNext()) {
                Log.d(TAG, cursor.getString(1));
                provincias = new Provincias();
                provincias.setId(cursor.getInt(0));
                provincias.setNombre(cursor.getString(1));
                provincias.setCantCamp(cursor.getString(2));
                provincias.setImagen(cursor.getString(3));
                listaProvincias.add(provincias);
            }

            AdapterProvincias adapterProvincias = new AdapterProvincias(listaProvincias, getApplicationContext());
            adapterProvincias.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Seleccion. " + listaProvincias.get(recyclerProvincias.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Principal.this, ShowProvincia.class);
                    i.putExtra("nompreProvincia", listaProvincias.get(recyclerProvincias.getChildAdapterPosition(view)).getNombre());
                    startActivity(i);
                    Log.d(TAG, listaProvincias.get(recyclerProvincias.getChildAdapterPosition(view)).getNombre());
                }
            });
            recyclerProvincias.setAdapter(adapterProvincias);
        }catch (Exception e){}
    }

}
