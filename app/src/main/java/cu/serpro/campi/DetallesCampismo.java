package cu.serpro.campi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import cu.serpro.campi.entidades.Campismos;
import cu.serpro.campi.entidades.Fotos;
import cu.serpro.campi.utilidades.utilidades;

public class DetallesCampismo extends AppCompatActivity {
    ImageView imageView4,imageView3;
    ConexionSQLiteHelper conn;
    public String TAG = "CAMPILOG";
    public Integer idCampismo, ddonde;
    public String nompreProvincia,paramSearch;
    TextView titulo,ubicacion,descripcion,nov2,v2,nov3,v3,nov4,v4,nov5,v5,nov6,v6,nov7,v7,nov8,v8,textgalery;
    RecyclerView recyclerPhotos;
    ArrayList<Fotos> listaFotosCampismos;
    LinearLayout listadosPrecios;
    TextView txtphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_campismo);
        getSupportActionBar().hide();
        //hago la coneccion con la BD
        conn = new ConexionSQLiteHelper(getApplicationContext());

        idCampismo = getIntent().getExtras().getInt("idCampismo");
        ddonde = getIntent().getExtras().getInt("ddonde");
        nompreProvincia = getIntent().getExtras().getString("nompreProvincia");
        paramSearch = getIntent().getExtras().getString("paramSearch");

        imageView4 = findViewById(R.id.imageView4);
        imageView3 = findViewById(R.id.imageView3);
        titulo = findViewById(R.id.titulo);
        ubicacion = findViewById(R.id.ubicacion);
        descripcion = findViewById(R.id.descripcion);
        textgalery = findViewById(R.id.textgalery);
        listadosPrecios = findViewById(R.id.listadosPrecios);
        txtphone = findViewById(R.id.txtphone);

        nov2 = findViewById(R.id.nov2);
        v2 = findViewById(R.id.v2);
        nov3 = findViewById(R.id.nov3);
        v3 = findViewById(R.id.v3);
        nov4 = findViewById(R.id.nov4);
        v4 = findViewById(R.id.v4);
        nov5 = findViewById(R.id.nov5);
        v5 = findViewById(R.id.v5);
        nov6 = findViewById(R.id.nov6);
        v6 = findViewById(R.id.v6);
        nov7 = findViewById(R.id.nov7);
        v7 = findViewById(R.id.v7);
        nov8 = findViewById(R.id.nov8);
        v8 = findViewById(R.id.v8);

        recyclerPhotos = findViewById(R.id.recyclerPhotos);
        //PARA MOSTRAR LAS VENTAS RAPIDAS
        recyclerPhotos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(ddonde == 2){
                    intent = new Intent(getApplicationContext(), ShowProvincia.class);
                    intent.putExtra("nompreProvincia", nompreProvincia);
                }else if(ddonde == 3){
                    intent = new Intent(getApplicationContext(), Search.class);
                    intent.putExtra("paramSearch", paramSearch);
                }else{
                    intent = new Intent(getApplicationContext(), Principal.class);
                }
                startActivity(intent);
            }
        });


        SQLiteDatabase db = conn.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + utilidades.TABLA_CAMPISMOS + " WHERE "+ utilidades.Id_campismos + " = '"+ idCampismo +"'", null);
            while (cursor.moveToNext()) {
//                Log.d(TAG, cursor.getString(1));
                titulo.setText(cursor.getString(1));
                ubicacion.setText(cursor.getString(6) + " - "+ cursor.getString(7));
                descripcion.setText(cursor.getString(2));

                if(cursor.getString(5) == null){
                    txtphone.setText("Sin telefono");
                }else {
                    txtphone.setText(cursor.getString(5));
                }
                Bitmap imagCampismo = null;
                try{
                    FileInputStream fileInputStream = new FileInputStream(getApplicationContext().getFilesDir().getPath() + "/" + cursor.getString(3));
                    imagCampismo = BitmapFactory.decodeStream(fileInputStream);
                }catch (IOException e){
                    e.printStackTrace();
                }
                imageView3.setImageBitmap(imagCampismo);

            }

        }catch (Exception e){}

        //cargo los precios de la etapa no vacacional
        loadPreciosNV(idCampismo);
        //cargo los precios de la etapa vacacional
        loadPreciosV(idCampismo);
        //cargo las fotos
        loadPhotosCampismos();

    }

    public void loadPreciosNV(Integer idCampismo){
        SQLiteDatabase db2 = conn.getReadableDatabase();
        try {
            Cursor cursor2 = db2.rawQuery("SELECT * FROM " + utilidades.TABLA_PRECIOS + " WHERE "+ utilidades.Id_prec_idcampismo + " = '"+ idCampismo +"' AND "+ utilidades.Prec_tipoetapa +" = 'NV'", null);
            if(cursor2.getCount() == 0){
                listadosPrecios.setVisibility(View.GONE);
            }
            while (cursor2.moveToNext()) {
                nov2.setText(cursor2.getString(3));
                nov3.setText(cursor2.getString(4));
                nov4.setText(cursor2.getString(5));
                nov5.setText(cursor2.getString(6));
                nov6.setText(cursor2.getString(7));
                nov7.setText(cursor2.getString(8));
                nov8.setText(cursor2.getString(9));
            }
        }catch (Exception e){}
    }
    public void loadPreciosV(Integer idCampismo){
        SQLiteDatabase db2 = conn.getReadableDatabase();
        try {
            Cursor cursor2 = db2.rawQuery("SELECT * FROM " + utilidades.TABLA_PRECIOS + " WHERE "+ utilidades.Id_prec_idcampismo + " = '"+ idCampismo +"' AND "+ utilidades.Prec_tipoetapa +" = 'V'", null);
            if(cursor2.getCount() == 0){
                listadosPrecios.setVisibility(View.GONE);
            }
            while (cursor2.moveToNext()) {
                v2.setText(cursor2.getString(3));
                v3.setText(cursor2.getString(4));
                v4.setText(cursor2.getString(5));
                v5.setText(cursor2.getString(6));
                v6.setText(cursor2.getString(7));
                v7.setText(cursor2.getString(8));
                v8.setText(cursor2.getString(9));
            }
        }catch (Exception e){}
    }

    private void loadPhotosCampismos() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Fotos fotos = null;
        listaFotosCampismos = new ArrayList<Fotos>();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + utilidades.TABLA_FOTOS +" WHERE "+ utilidades.Id_foto_idcampismo + "= '"+idCampismo+"'", null);
            Log.d(TAG, "cantidad de fotos:"+ cursor.getCount());

            if(cursor.getCount() == 0){
                textgalery.setVisibility(View.GONE);
                recyclerPhotos.setVisibility(View.GONE);
            }

            while (cursor.moveToNext()) {
                Log.d(TAG, cursor.getString(1));
                fotos = new Fotos();
                fotos.setId(cursor.getInt(0));
                fotos.setIdcampismo(cursor.getInt(1));
                fotos.setNombre(cursor.getString(2));
                listaFotosCampismos.add(fotos);
            }

            AdapterFotosCampismos adapterFotosCampismos = new AdapterFotosCampismos(listaFotosCampismos, getApplicationContext());
            adapterFotosCampismos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Seleccion. " + listaFotosCampismos.get(recyclerPhotos.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(DetallesCampismo.this, DetallesCampismo.class);
//                    i.putExtra("idCampismo", listaFotosCampismos.get(recyclerPhotos.getChildAdapterPosition(view)).getId());
//                    startActivity(i);
                    Log.d(TAG, listaFotosCampismos.get(recyclerPhotos.getChildAdapterPosition(view)).getId().toString());
                }
            });
            recyclerPhotos.setAdapter(adapterFotosCampismos);
        }catch (Exception e){}
    }
}
