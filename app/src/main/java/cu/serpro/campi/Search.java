package cu.serpro.campi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import cu.serpro.campi.entidades.Campismos;
import cu.serpro.campi.entidades.Provincias;
import cu.serpro.campi.utilidades.utilidades;

public class Search extends AppCompatActivity {
    ImageView imageView4,btnSearch;
    public String TAG = "CAMPILOG";
    public Integer ddonde;
    RecyclerView recyclerSearch;
    ConexionSQLiteHelper conn;
    ArrayList<Campismos> listaCampismos;
    EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();
        //hago la coneccion con la BD
        conn = new ConexionSQLiteHelper(getApplicationContext());

        imageView4 = findViewById(R.id.imageView4);
        btnSearch = findViewById(R.id.btnSearch);
        edtSearch = findViewById(R.id.edtSearch);
        recyclerSearch = findViewById(R.id.recyclerSearch);
        //PARA MOSTRAR LAS VENTAS RAPIDAS
        recyclerSearch.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), Principal.class);
                startActivity(intent);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtSearch.getText().toString().isEmpty()){
                    Toast.makeText(Search.this, "Escriba un parámetro para la busqueda", Toast.LENGTH_SHORT).show();
                }else{
                    loadCampismos(edtSearch.getText().toString());
                }
            }
        });
    }

    private void loadCampismos(String search) {
        SQLiteDatabase db = conn.getReadableDatabase();
        Campismos campismos = null;
        listaCampismos = new ArrayList<Campismos>();
        Log.d(TAG, "busqueda: "+search);
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + utilidades.TABLA_CAMPISMOS +" WHERE "+ utilidades.Camp_titulo + " LIKE '%"+ search +"%'", null);
            Log.d(TAG, "busqueda: "+search +" / cantidad de resultados: "+ cursor.getCount());
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

            AdapterCampismosPopularesProvincia adapterCampismosPopulares = new AdapterCampismosPopularesProvincia(listaCampismos, getApplicationContext());
            adapterCampismosPopulares.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Seleccion. " + listaCampismos.get(recyclerSearch.getChildAdapterPosition(view)).getTitulo(), Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(ShowProvincia.this, DetallesCampismo.class);
//                    i.putExtra("idCampismo", listaCampismos.get(recyclerSearch.getChildAdapterPosition(view)).getId());
//                    i.putExtra("ddonde", 2);
//                    i.putExtra("nompreProvincia", nompreProvincia);
//                    startActivity(i);
                    Log.d(TAG, listaCampismos.get(recyclerSearch.getChildAdapterPosition(view)).getId().toString());
                }
            });
            recyclerSearch.setAdapter(adapterCampismosPopulares);
        }catch (Exception e){}
    }
}
