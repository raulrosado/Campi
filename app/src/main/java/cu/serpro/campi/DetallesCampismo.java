package cu.serpro.campi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import cu.serpro.campi.entidades.Campismos;
import cu.serpro.campi.entidades.Fotos;
import cu.serpro.campi.entidades.Servicios;
import cu.serpro.campi.utilidades.utilidades;

public class DetallesCampismo extends AppCompatActivity {
    ImageView imageView4,imageView3,favorite;
    ConexionSQLiteHelper conn;
    public String TAG = "CAMPILOG";
    public Integer idCampismo, ddonde;
    public String nompreProvincia,paramSearch;
    TextView titulo,ubicacion,descripcion,nov2,v2,nov3,v3,nov4,v4,nov5,v5,nov6,v6,nov7,v7,nov8,v8,textgalery;
    RecyclerView recyclerPhotos,recyclerServisios;
    ArrayList<Fotos> listaFotosCampismos;
    ArrayList<Servicios> listaServicios;
    LinearLayout listadosPrecios;
    TextView txtphone,categoria,tipoturismo;

    Integer sfavorite;

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
        favorite = findViewById(R.id.favorite);

        categoria = findViewById(R.id.categoria);
        tipoturismo = findViewById(R.id.tipoturismo);


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
        recyclerServisios = findViewById(R.id.recyclerServisios);
        //PARA MOSTRAR LAS VENTAS RAPIDAS
        recyclerPhotos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerServisios.setLayoutManager(new GridLayoutManager(this, 4));

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
                }else  if(ddonde == 6){
                    intent = new Intent(getApplicationContext(), MapsActivity.class);
                }else{
                    intent = new Intent(getApplicationContext(), Principal.class);
                }
                startActivity(intent);
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sfavorite == 1) {
                    conn.addfavorite(idCampismo);
                    favorite.setImageResource(R.drawable.ic_favorite_black_24dp);
                    Log.d(TAG, "add");
                    sfavorite = 2;
                }else{
                    conn.remfavorite(idCampismo);
                    favorite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    Log.d(TAG, "rem");
                    sfavorite = 1;
                }
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
                categoria.setText("Categoria: " +cursor.getString(9));
                tipoturismo.setText("Categoria: " +cursor.getString(10));

                sfavorite = cursor.getInt(13);

                if(cursor.getInt(13) == 2){
                    favorite.setImageResource(R.drawable.ic_favorite_black_24dp);
                }else{
                    favorite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }

                String local = cursor.getString(4);

                ubicacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent verenmapa = new Intent(getApplicationContext(), MapsActivity.class);
                        verenmapa.putExtra("localizacionDefault", local);
                        startActivity(verenmapa);
                    }
                });

//****************************************************
                String[] servicios = cursor.getString(8).split("/");
               // Log.d(TAG,"servicios: "+ cursor.getString(8) + "/ cantidad: " + servicios.length + "/ primero: "+ servicios[1].toString());

                Servicios servicios1 = null;
                listaServicios = new ArrayList<Servicios>();
                String nombreS="";

                for(Integer i=0; i < servicios.length; i++){
                    Log.d(TAG, servicios[i]);
                    servicios1 = new Servicios();

                    SQLiteDatabase db2 = conn.getReadableDatabase();
                    Cursor cursor2 = db.rawQuery("SELECT * FROM " + utilidades.TABLA_SERVICIOS + " WHERE "+ utilidades.Serv_imagen + " = '"+ servicios[i] +"'" , null);
                    //Log.d(TAG, "cantidad servicio: " + cursor2.getCount());
                        while (cursor2.moveToNext()) {
                            //Log.d(TAG, "nombre servisio" + cursor2.getString(1));
                            nombreS = cursor2.getString(1);
                        }

                    servicios1.setNombreservicio(nombreS);
                    servicios1.setImagen(servicios[i]);
                    listaServicios.add(servicios1);
                    changeDir(servicios[i].toString());

                }

                AdapterCampismosServicios adapterCampismosServicios = new AdapterCampismosServicios(listaServicios, getApplicationContext());
                adapterCampismosServicios.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(getApplicationContext(), listaServicios.get(recyclerServisios.getChildAdapterPosition(view)).getNombreservicio(), Toast.LENGTH_SHORT).show();
                    }
                });
                recyclerServisios.setAdapter(adapterCampismosServicios);

                //****************************************************

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

    public void changeDir(String namefile){
        InputStream origen = null;
        try {
            origen = getAssets().open(namefile);
            OutputStream destino = new FileOutputStream(getApplicationContext().getFilesDir().getPath() +  "/" + namefile );
            byte[] buffer = new byte[1024];
            int length;
            while ((length = origen.read(buffer)) > 0) {
                destino.write(buffer, 0, length);
            }
            origen.close();
            destino.flush();
            destino.close();
            Log.i(TAG, "Se copio el archivo...");
        } catch (IOException e) {
            Log.e(TAG, "No se pudo copiar...");
            e.printStackTrace();
        }
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
            if(cursor.getCount() == 0){
                textgalery.setVisibility(View.GONE);
                recyclerPhotos.setVisibility(View.GONE);
            }

            while (cursor.moveToNext()) {
                fotos = new Fotos();
                fotos.setId(cursor.getInt(0));
                fotos.setIdcampismo(cursor.getInt(1));
                fotos.setNombre(cursor.getString(2));
                listaFotosCampismos.add(fotos);
            }

            AdapterFotosCampismos adapterFotosCampismos = new AdapterFotosCampismos(listaFotosCampismos, getApplicationContext(),DetallesCampismo.this);
            adapterFotosCampismos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(getApplicationContext(), "Seleccion. " + listaFotosCampismos.get(recyclerPhotos.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, listaFotosCampismos.get(recyclerPhotos.getChildAdapterPosition(view)).getId().toString());
                }
            });
            recyclerPhotos.setAdapter(adapterFotosCampismos);
        }catch (Exception e){}
    }
}
