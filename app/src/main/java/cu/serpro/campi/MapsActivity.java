package cu.serpro.campi;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import cu.serpro.campi.entidades.Campismos;
import cu.serpro.campi.utilidades.utilidades;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public String TAG = "CAMPILOG";
    ConexionSQLiteHelper conn;

    private Button button2;
    private LinearLayout bottom_sheet;
    private BottomSheetBehavior sheetBehavior;
    String localizacionDefault = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//hago la coneccion con la BD
        conn = new ConexionSQLiteHelper(getApplicationContext());

        try {
            localizacionDefault = getIntent().getExtras().getString("localizacionDefault");
            Log.d(TAG, localizacionDefault);
        }catch (Exception e){}

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
            return;
        }

        SQLiteDatabase db = conn.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + utilidades.TABLA_CAMPISMOS , null);
            String[] coord = null;
            LatLng position = null;
            while (cursor.moveToNext()) {
//                Log.d(TAG, cursor.getPosition() +"/"+ cursor.getString(1) + "/" + cursor.getString(4).toString()); //titulo
                coord = cursor.getString(4).toString().split(","); //localizacion
                position = new LatLng(Double.parseDouble(coord[0]), Double.parseDouble(coord[1]));
                 mMap.addMarker(new MarkerOptions().position(position).title(cursor.getString(1)));
            }

            //mMap.getUiSettings().setMapToolbarEnabled(true);
        }catch (Exception e){}

        LatLng cuba = new LatLng(21.4086958,-79.5822557);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cuba,6));

        Log.d(TAG, "localizacion: " + localizacionDefault);

        if(localizacionDefault != null){
            String[] coord2 = localizacionDefault.toString().split(","); //localizacion

            LatLng position2;
            position2 = new LatLng(Double.parseDouble(coord2[0]), Double.parseDouble(coord2[1]));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position2,13));
        }else{

        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {

                final BottomSheetDialog dialog = new BottomSheetDialog(MapsActivity.this);
                dialog.setContentView(R.layout.descrip_buttonsheet);
                dialog.setCanceledOnTouchOutside(false);

                ImageView imagenDet =  dialog.findViewById(R.id.imagenDet);
                TextView bdialTitle =  dialog.findViewById(R.id.bdialTitle);
                TextView bcategoria =  dialog.findViewById(R.id.bcategoria);
                TextView bubicacion =  dialog.findViewById(R.id.bubicacion);
                TextView btxtphone =  dialog.findViewById(R.id.btxtphone);
                Button bdetalle =  dialog.findViewById(R.id.bdetalle);
                LinearLayout layubi =  dialog.findViewById(R.id.layubi);
                LinearLayout layphone =  dialog.findViewById(R.id.layphone);

                SQLiteDatabase db2 = conn.getReadableDatabase();
                try {
                    Cursor cursor2 = db2.rawQuery("SELECT * FROM " + utilidades.TABLA_CAMPISMOS + " WHERE " + utilidades.Camp_titulo + " = '"+ marker.getTitle()+"' ", null);
                    while (cursor2.moveToNext()) {
                        Log.d(TAG, cursor2.getString(1));
                        //cursor.getString(1); //titulo
                        bdialTitle.setText(cursor2.getString(1));

                        if(cursor2.getString(9) == null){
                            layubi.setVisibility(View.GONE);
                        }
                        if(cursor2.getString(5) == null){
                            layphone.setVisibility(View.GONE);
                        }

                        bcategoria.setText("Categoria:" + cursor2.getString(9));
                        bubicacion.setText(cursor2.getString(6));
                        btxtphone.setText(cursor2.getString(5));

                        Integer idCamp = cursor2.getInt(0);
                        Log.d(TAG, "id campismo:" + idCamp);

                        Bitmap bitmap = null;
                        try{
                            FileInputStream fileInputStream = new FileInputStream(getApplicationContext().getFilesDir().getPath() + "/" + cursor2.getString(3));
                            bitmap = BitmapFactory.decodeStream(fileInputStream);
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                        imagenDet.setImageBitmap(bitmap);           ///SE LE PASA EL BITMAP A LA IMAGEN

                        bdetalle.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(MapsActivity.this, DetallesCampismo.class);
                                i.putExtra("idCampismo",idCamp);
                                i.putExtra("ddonde",6);
                                startActivity(i);
                            }
                        });

                    }
                }catch (Exception e){}
                dialog.show();
                return true;
            }
        });
        mMap.setMyLocationEnabled(true);

//        mMap.setMapStyle(
//                MapStyleOptions.loadRawResourceStyle(
//                        this, R.raw.estiloalberginemapa));

    }

    private void cargarImagenWebService(String nombreArchivo, final AdapterCampismosServicios.ViewHolderDatos viewHolderDatos) {
        Bitmap bitmap = null;
        try{
            FileInputStream fileInputStream = new FileInputStream(getApplicationContext().getFilesDir().getPath() + "/" + nombreArchivo);
            bitmap = BitmapFactory.decodeStream(fileInputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        viewHolderDatos.imgservice.setImageBitmap(bitmap);           ///SE LE PASA EL BITMAP A LA IMAGEN
    }
}
