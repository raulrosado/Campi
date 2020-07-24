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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cu.serpro.campi.entidades.Campismos;
import cu.serpro.campi.utilidades.utilidades;

public class ShowProvincia extends AppCompatActivity {
    ConexionSQLiteHelper conn;
    RecyclerView recyclerCampismosProvincias;
    ArrayList<Campismos> listaCampismos;
    public String TAG = "CAMPILOG";
    public String nompreProvincia;
    TextView textView;
    ImageView imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_provincia);
        getSupportActionBar().hide();

        //hago la coneccion con la BD
        conn = new ConexionSQLiteHelper(getApplicationContext());

        imageView4 = findViewById(R.id.imageView4);
        nompreProvincia = getIntent().getExtras().getString("nompreProvincia");

        textView = findViewById(R.id.textView);
        textView.setText(nompreProvincia);

        recyclerCampismosProvincias = findViewById(R.id.recyclerCampismosProvincias);
        recyclerCampismosProvincias.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //cargo los campismos
        loadCampismos();

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), Principal.class);
                startActivity(intent);
            }
        });
    }

    private void loadCampismos() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Campismos campismos = null;
        listaCampismos = new ArrayList<Campismos>();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + utilidades.TABLA_CAMPISMOS +" WHERE "+ utilidades.Camp_provincia + "= '"+nompreProvincia+"'", null);
            while (cursor.moveToNext()) {
                Log.d(TAG, cursor.getString(1));
                campismos = new Campismos();
                campismos.setId(cursor.getInt(0));
                campismos.setTitulo(cursor.getString(1));
                campismos.setDescripcion(cursor.getString(2));
                campismos.setImagen(cursor.getString(3));
                campismos.setTelefono(cursor.getString(4));
                campismos.setUbicacion(cursor.getString(5));
                campismos.setDireccion(cursor.getString(6));
                campismos.setServicios(cursor.getString(7));
                campismos.setCategoria(cursor.getString(8));
                campismos.setTipoturismo(cursor.getString(9));
                campismos.setMunicipio(cursor.getString(10));
                campismos.setProvincia(cursor.getString(11));
                campismos.setEstado(cursor.getInt(12));
                listaCampismos.add(campismos);
            }

            AdapterCampismosPopularesProvincia adapterCampismosPopulares = new AdapterCampismosPopularesProvincia(listaCampismos, getApplicationContext());
            adapterCampismosPopulares.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "Seleccion. " + listaCampismos.get(recyclerCampismosProvincias.getChildAdapterPosition(view)).getTitulo(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ShowProvincia.this, DetallesCampismo.class);
                    i.putExtra("idCampismo", listaCampismos.get(recyclerCampismosProvincias.getChildAdapterPosition(view)).getId());
                    i.putExtra("ddonde", 2);
                    i.putExtra("nompreProvincia", nompreProvincia);
                    startActivity(i);
                    Log.d(TAG, listaCampismos.get(recyclerCampismosProvincias.getChildAdapterPosition(view)).getId().toString());
                }
            });
            recyclerCampismosProvincias.setAdapter(adapterCampismosPopulares);
        }catch (Exception e){}
    }
}
