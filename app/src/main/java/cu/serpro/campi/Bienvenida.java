package cu.serpro.campi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cu.serpro.campi.utilidades.utilidades;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class Bienvenida extends AppCompatActivity {
    private static final String RUTA_A_LA_BASE_DE_DATOS_SQLITE = "/data/data/cu.serpro.campi/databases/";
    ConexionSQLiteHelper conn;      //conexion a la bd
    public String TAG = "CAMPILOG";
    Intent i;
    Button button;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        InputStream origen = null;
        String file;
        //hago la coneccion con la BD
        conn = new ConexionSQLiteHelper(getApplicationContext());

        button = findViewById(R.id.button);
        imageView2 = findViewById(R.id.imageView2);

        if(validarPermisos()){
            Log.d(TAG, "se dio el permiso");
        }else{
            Log.d(TAG, "no se dio el permiso");
            Toast.makeText(this, "Permiso no concedido", Toast.LENGTH_SHORT).show();
        }

        if (fileExist(getApplicationContext(),RUTA_A_LA_BASE_DE_DATOS_SQLITE +  "campi")){
            Log.d("sisrpLog", "esta en db");

            SQLiteDatabase db = conn.getReadableDatabase();
            try {
                Cursor cursor = db.rawQuery("SELECT * FROM " + utilidades.TABLA_CAMPISMOS , null);
            }catch (Exception e){

                try {
                    origen = getAssets().open("campi");
                    OutputStream destino = new FileOutputStream(RUTA_A_LA_BASE_DE_DATOS_SQLITE +  "campi" );
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = origen.read(buffer)) > 0) {
                        destino.write(buffer, 0, length);
                    }
                    origen.close();
                    destino.flush();
                    destino.close();
                    Log.i(TAG, "New database created...");
                } catch (IOException ez) {
                    Log.e(TAG, "Could not create new database...");
                    ez.printStackTrace();
                }
            }

        }else{
            Log.d("sisrpLog", "No esta en db");
            try {
                origen = getAssets().open("campi");
                OutputStream destino = new FileOutputStream(RUTA_A_LA_BASE_DE_DATOS_SQLITE +  "campi" );
                byte[] buffer = new byte[1024];
                int length;
                while ((length = origen.read(buffer)) > 0) {
                    destino.write(buffer, 0, length);
                }
                origen.close();
                destino.flush();
                destino.close();
                Log.i(TAG, "New database created...");
            } catch (IOException e) {
                Log.e(TAG, "Could not create new database...");
                e.printStackTrace();
            }

        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream origen = null;

                SQLiteDatabase db = conn.getReadableDatabase();
                try {
                    Cursor cursor = db.rawQuery("SELECT * FROM " + utilidades.TABLA_CAMPISMOS , null);
                }catch (Exception e){

                    try {
                        origen = getAssets().open("campi");
                        OutputStream destino = new FileOutputStream(RUTA_A_LA_BASE_DE_DATOS_SQLITE +  "campi" );
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = origen.read(buffer)) > 0) {
                            destino.write(buffer, 0, length);
                        }
                        origen.close();
                        destino.flush();
                        destino.close();
                        Log.i(TAG, "New database created...");
                    } catch (IOException ez) {
                        Log.e(TAG, "Could not create new database...");
                        ez.printStackTrace();
                    }
                }

                i = new Intent(getApplicationContext(),Principal.class);
                startActivity(i);
                finish();
            }
        });

        changeDir("aguasclaras.jpeg");
        changeDir("aguasclaras1.jpeg");
        changeDir("aguasclaras2.jpeg");
        changeDir("aguasclaras3.jpeg");
        changeDir("aguasclaras4.jpeg");
        changeDir("aguasclaras5.jpeg");
        changeDir("aguasclaras6.jpeg");
        changeDir("aguasclaras7.jpeg");

        changeDir("boca.jpeg");
        changeDir("boca1.jpeg");
        changeDir("boca3.jpeg");
        changeDir("boca4.jpeg");
        changeDir("boca5.jpeg");

        changeDir("doshermanas.jpeg");
        changeDir("doshermanas1.jpeg");
        changeDir("doshermanas2.jpeg");
        changeDir("doshermanas3.jpeg");
        changeDir("doshermanas4.jpeg");

        changeDir("lagunagrande.jpeg");
        changeDir("lagunagrande1.jpeg");
        changeDir("lagunagrande2.jpeg");
        changeDir("lagunagrande3.jpeg");
        changeDir("lagunagrande4.jpeg");
        changeDir("lagunagrande5.jpeg");

        changeDir("cuevaportales.jpeg");
        changeDir("cuevaportales1.jpeg");
        changeDir("cuevaportales2.jpeg");
        changeDir("cuevaportales3.jpeg");
        changeDir("cuevaportales4.jpeg");
        changeDir("cuevaportales5.jpeg");

        changeDir("copey.jpeg");
        changeDir("copey1.jpeg");
        changeDir("copey2.jpeg");
        changeDir("copey3.jpeg");
        changeDir("copey4.jpeg");
        changeDir("copey5.jpeg");
        changeDir("copey6.jpeg");

        changeDir("playa.jpg");

        changeDir("pajarito.jpeg");
        changeDir("pajarito1.jpeg");
        changeDir("pajarito2.jpeg");
        changeDir("pajarito3.jpeg");
        changeDir("pajarito4.jpeg");
        changeDir("pajarito5.jpeg");
        changeDir("pajarito6.jpeg");
        changeDir("pajarito7.jpeg");
        changeDir("pajarito8.jpeg");
        changeDir("pajarito10.jpeg");
        changeDir("pajarito11.jpeg");

        changeDir("saltoportales.jpeg");
        changeDir("saltoportales1.jpeg");
        changeDir("saltoportales2.jpeg");
        changeDir("saltoportales3.jpeg");
        changeDir("saltoportales4.jpeg");
        changeDir("saltoportales5.jpeg");
        changeDir("saltoportales6.jpeg");

        changeDir("cocomar.jpeg");
        changeDir("cocomar1.jpg");
        changeDir("cocomar2.jpg");
        changeDir("cocomar3.jpg");
        changeDir("cocomar4.jpg");

        changeDir("eltaburete.jpeg");
        changeDir("eltaburete1.jpg");
        changeDir("eltaburete2.jpg");
        changeDir("eltaburete3.jpg");

        changeDir("jardinaspiro.jpeg");
        changeDir("jardinaspiro1.jpg");
        changeDir("jardinaspiro2.jpg");

        changeDir("laaltura.jpeg");

        changeDir("lacaridad.jpeg");
        changeDir("lacaridad1.jpg");
        changeDir("lacaridad2.jpg");
        changeDir("lacaridad3.jpg");
        changeDir("lacaridad4.jpg");
        changeDir("lacaridad5.jpg");
        changeDir("lacaridad6.jpg");

        changeDir("lachorrera.jpeg");
        changeDir("lachorrera1.jpg");
        changeDir("lachorrera2.jpg");
        changeDir("lachorrera3.jpg");

        changeDir("lacoronela.jpeg");
        changeDir("lacoronela1.jpg");
        changeDir("lacoronela2.jpg");
        changeDir("lacoronela3.jpg");
        changeDir("lacoronela4.jpg");
        changeDir("lacoronela5.jpeg");

        changeDir("laherradura.jpeg");
        changeDir("laherradura1.jpg");
        changeDir("laherradura2.jpg");

        changeDir("sanpedro.jpeg");
        changeDir("sanpedro1.jpg");
        changeDir("sanpedro2.jpg");

        changeDir("bocajaruco.jpg");
        changeDir("bocajaruco1.jpg");
        changeDir("bocajaruco2.jpg");
        changeDir("bocajaruco3.jpg");

        changeDir("laterraza.jpg");
        changeDir("laterraza1.jpg");

        changeDir("lascaletas.jpeg");
        changeDir("lascaletas1.jpg");
        changeDir("lascaletas2.jpg");
        changeDir("lascaletas3.jpg");
        changeDir("lascaletas4.jpg");

        changeDir("puertoescondido.jpg");
        changeDir("puertoescondido1.jpg");
        changeDir("puertoescondido2.jpg");
        changeDir("puertoescondido3.jpg");
        changeDir("puertoescondido4.jpg");

        changeDir("playamarilla.jpg");
        changeDir("playamarilla1.jpg");
        changeDir("playamarilla2.jpg");

        changeDir("penablanca.jpeg");

        changeDir("loscocos.jpg");
        changeDir("loscocos1.jpg");
        changeDir("loscocos2.jpg");
        changeDir("loscocos3.jpg");
        changeDir("loscocos4.jpg");

        changeDir("escalerasjaruco.jpg");
        changeDir("escalerasjaruco1.jpg");
        changeDir("escalerasjaruco2.jpg");
        changeDir("escalerasjaruco3.jpg");
        changeDir("escalerasjaruco4.jpg");

        changeDir("lascuevas.jpg");
        changeDir("lascuevas1.jpg");
        changeDir("lascuevas2.jpg");
        changeDir("lascuevas3.jpg");

        changeDir("lalaguna.jpg");
        changeDir("lalaguna1.jpg");
        changeDir("lalaguna2.jpg");
        changeDir("lalaguna3.jpg");
        changeDir("lalaguna4.jpg");

        changeDir("elabra.jpg");
        changeDir("elabra1.jpg");
        changeDir("elabra2.jpg");
        changeDir("elabra3.jpg");

        changeDir("lasalmendras.jpg");
        changeDir("lasalmendras1.jpg");
        changeDir("lasalmendras2.jpg");
        changeDir("lasalmendras3.jpg");
        changeDir("lasalmendras4.jpg");

        changeDir("riosanjuan.jpg");
        changeDir("riosanjuan1.jpg");
        changeDir("riosanjuan2.jpg");
        changeDir("riosanjuan3.jpg");
        changeDir("riosanjuan4.jpg");
        changeDir("riosanjuan5.jpg");
        changeDir("riosanjuan6.jpg");
        changeDir("riosanjuan7.jpg");
        changeDir("riosanjuan8.jpg");

        changeDir("rioconimar.jpeg");
        changeDir("rioconimar1.jpeg");
        changeDir("rioconimar2.jpeg");
        changeDir("rioconimar3.jpeg");
        changeDir("rioconimar4.jpeg");
        changeDir("rioconimar5.jpeg");

        changeDir("cominarabajo.jpeg");
        changeDir("cominarabajo1.jpeg");
        changeDir("cominarabajo2.jpeg");
        changeDir("cominarabajo3.jpeg");
        changeDir("cominarabajo4.jpeg");
        changeDir("cominarabajo5.jpeg");

        changeDir("bacunayagua.jpeg");
        changeDir("bacunayagua1.jpeg");
        changeDir("bacunayagua2.jpeg");
        changeDir("bacunayagua3.jpeg");
        changeDir("bacunayagua4.jpeg");
        changeDir("bacunayagua5.jpeg");

        changeDir("sierramorena.jpeg");
        changeDir("sierramorena1.jpeg");
        changeDir("sierramorena2.jpeg");
        changeDir("sierramorena3.jpeg");
        changeDir("sierramorena4.jpeg");
        changeDir("sierramorena5.jpeg");
        changeDir("sierramorena6.jpeg");
        changeDir("sierramorena7.jpeg");

        changeDir("cayocanuco.jpeg");
        changeDir("cayocanuco1.jpg");
        changeDir("cayocanuco2.jpg");

        changeDir("ganuza.jpeg");
        changeDir("ganuza1.jpeg");
        changeDir("ganuza2.jpeg");
        changeDir("ganuza3.jpeg");
        changeDir("ganuza4.jpeg");
        changeDir("ganuza5.jpeg");

        changeDir("arcoiris.jpeg");
        changeDir("arcoiris1.jpeg");
        changeDir("arcoiris2.jpeg");
        changeDir("arcoiris3.jpeg");
        changeDir("arcoiris4.jpeg");
        changeDir("arcoiris5.jpeg");
        changeDir("arcoiris6.jpeg");

        changeDir("rioseibabo.jpeg");
        changeDir("rioseibabo1.jpeg");
        changeDir("rioseibabo2.jpeg");
        changeDir("rioseibabo3.jpeg");

        changeDir("elsalto.jpeg");
        changeDir("elsalto1.jpeg");
        changeDir("elsalto2.jpeg");
        changeDir("elsalto3.jpeg");
        changeDir("elsalto4.jpeg");
        changeDir("elsalto5.jpeg");

        changeDir("presaminerva.jpeg");
        changeDir("presaminerva1.jpeg");
        changeDir("presaminerva2.jpeg");
        changeDir("presaminerva3.jpeg");
        changeDir("presaminerva4.jpeg");
        changeDir("presaminerva5.jpeg");
        changeDir("presaminerva6.jpeg");

        changeDir("guagimoco.jpeg");
        changeDir("guagimoco1.jpeg");
        changeDir("guagimoco2.jpg");
        changeDir("guagimoco3.jpg");
        changeDir("guagimoco4.jpg");
        changeDir("guagimoco5.jpg");
        changeDir("guagimoco6.jpg");
        changeDir("guagimoco7.jpg");

        changeDir("jagua.jpeg");
        changeDir("jagua1.jpg");
        changeDir("jagua2.jpg");
        changeDir("jagua3.jpg");

        changeDir("elingles.jpg");
        changeDir("elingles1.jpg");
        changeDir("elingles2.jpg");
        changeDir("elingles3.jpg");
        changeDir("elingles4.jpg");
        changeDir("elingles5.jpg");

        changeDir("plantacantu.jpeg");
        changeDir("plantacantu1.jpeg");
        changeDir("plantacantu2.jpeg");
        changeDir("plantacantu3.jpeg");
        changeDir("plantacantu4.jpeg");
        changeDir("plantacantu5.jpeg");

        changeDir("lahormiga.jpeg");
        changeDir("lahormiga1.jpeg");
        changeDir("lahormiga2.jpeg");
        changeDir("lahormiga3.jpeg");
        changeDir("lahormiga4.jpeg");
        changeDir("lahormiga5.jpeg");

        changeDir("bamburanao.jpeg");
        changeDir("bamburanao1.jpeg");
        changeDir("bamburanao2.jpeg");
        changeDir("bamburanao3.jpeg");
        changeDir("bamburanao4.jpeg");
        changeDir("bamburanao5.jpeg");

        changeDir("arroyolajas.jpeg");
        changeDir("arroyolajas1.jpeg");
        changeDir("arroyolajas2.jpeg");
        changeDir("arroyolajas3.jpeg");
        changeDir("arroyolajas4.jpeg");
        changeDir("arroyolajas5.jpeg");
        changeDir("arroyolajas6.jpeg");

        changeDir("pozaazul.jpeg");
        changeDir("pozaazul1.jpeg");
        changeDir("pozaazul2.jpeg");
        changeDir("pozaazul3.jpeg");
        changeDir("pozaazul4.jpeg");
        changeDir("pozaazul5.jpeg");
        changeDir("pozaazul6.jpeg");

        changeDir("manacal.jpeg");
        changeDir("manacal1.jpeg");
        changeDir("manacal2.jpeg");
        changeDir("manacal3.jpeg");
        changeDir("manacal4.jpeg");
        changeDir("manacal5.jpeg");

        changeDir("cayococo.jpeg");
        changeDir("cayococo1.jpeg");
        changeDir("cayococo2.jpeg");
        changeDir("cayococo3.jpeg");
        changeDir("cayococo4.jpeg");
        changeDir("cayococo5.jpeg");
        changeDir("cayococo6.jpeg");

        changeDir("boqueron.jpeg");
        changeDir("boqueron1.jpeg");
        changeDir("boqueron2.jpeg");
        changeDir("boqueron3.jpeg");
        changeDir("boqueron4.jpeg");
        changeDir("boqueron5.jpeg");

        changeDir("elcharcazo.jpeg");
        changeDir("elcharcazo1.jpeg");
        changeDir("elcharcazo2.jpeg");
        changeDir("elcharcazo3.jpeg");
        changeDir("elcharcazo4.jpeg");
        changeDir("elcharcazo5.jpeg");

        changeDir("losnaranjos.jpeg");
        changeDir("losnaranjos1.jpeg");
        changeDir("losnaranjos2.jpeg");
        changeDir("losnaranjos3.jpeg");
        changeDir("losnaranjos4.jpeg");
        changeDir("losnaranjos5.jpeg");

        changeDir("puntalegre.jpg");
        changeDir("puntalegre1.jpg");
        changeDir("puntalegre2.jpg");

        changeDir("monteoscuro.jpeg");
        changeDir("monteoscuro1.jpeg");
        changeDir("monteoscuro2.jpeg");

        changeDir("laspalmas.jpeg");
        changeDir("laspalmas1.jpeg");
        changeDir("laspalmas2.jpeg");
        changeDir("laspalmas3.jpeg");
        changeDir("laspalmas4.jpeg");
        changeDir("laspalmas5.jpeg");
        changeDir("laspalmas6.jpeg");

        changeDir("labarbacoa.jpeg");
        changeDir("labarbacoa1.jpeg");
        changeDir("labarbacoa2.jpeg");
        changeDir("labarbacoa3.jpeg");

        changeDir("loscangilones.jpeg");
        changeDir("loscangilones1.jpeg");
        changeDir("loscangilones2.jpeg");
        changeDir("loscangilones3.jpeg");
        changeDir("loscangilones4.jpeg");
        changeDir("loscangilones5.jpeg");

        changeDir("lasclavellinas.jpeg");
        changeDir("lasclavellinas1.jpeg");

        changeDir("batalla.jpeg");
        changeDir("batalla1.jpeg");
        changeDir("batalla2.jpeg");
        changeDir("batalla3.jpeg");
        changeDir("batalla4.jpeg");
        changeDir("batalla5.jpeg");

        changeDir("puntaganado.jpg");
        changeDir("puntaganado1.jpg");
        changeDir("puntaganado2.jpg");
        changeDir("puntaganado3.jpg");

        changeDir("playablanca.jpeg");
        changeDir("playablanca1.jpeg");
        changeDir("playablanca2.jpeg");
        changeDir("playablanca3.jpeg");
        changeDir("playablanca4.jpeg");
        changeDir("playablanca5.jpeg");

        changeDir("riocabonico.jpg");
        changeDir("riocabonico1.jpg");
        changeDir("riocabonico2.jpg");
        changeDir("riocabonico3.jpg");

        changeDir("sillagibara.jpg");
        changeDir("sillagibara1.jpg");
        changeDir("sillagibara2.jpg");
        changeDir("sillagibara3.jpg");
        changeDir("sillagibara4.jpg");

        changeDir("cromita.jpeg");
        changeDir("cromita1.jpeg");
        changeDir("cromita2.jpeg");
        changeDir("cromita3.jpeg");

        changeDir("lascoloradas.jpeg");
        changeDir("lascoloradas1.jpeg");
        changeDir("lascoloradas2.jpeg");
        changeDir("lascoloradas3.jpeg");
        changeDir("lascoloradas4.jpeg");
        changeDir("lascoloradas5.jpeg");
        changeDir("lascoloradas6.jpeg");

        changeDir("lasierrita.jpeg");
        changeDir("lasierrita1.jpeg");
        changeDir("lasierrita2.jpeg");
        changeDir("lasierrita3.jpeg");
        changeDir("lasierrita4.jpeg");
        changeDir("lasierrita5.jpeg");

        changeDir("loscantiles.jpeg");
        changeDir("loscantiles1.jpeg");
        changeDir("loscantiles2.jpeg");
        changeDir("loscantiles3.jpeg");
        changeDir("loscantiles4.jpeg");
        changeDir("loscantiles5.jpeg");
        changeDir("loscantiles6.jpeg");

        changeDir("saltojibacoa.jpeg");
        changeDir("saltojibacoa1.jpeg");
        changeDir("saltojibacoa2.jpeg");
        changeDir("saltojibacoa3.jpeg");
        changeDir("saltojibacoa4.jpeg");
        changeDir("saltojibacoa5.jpeg");
        changeDir("saltojibacoa6.jpeg");

        changeDir("caleton.jpeg");
        changeDir("caleton1.jpeg");
        changeDir("caleton2.jpeg");
        changeDir("caleton3.jpeg");
        changeDir("caleton4.jpeg");

        changeDir("lamula.jpeg");
        changeDir("lamula1.jpeg");
        changeDir("lamula2.jpeg");
        changeDir("lamula3.jpeg");
        changeDir("lamula4.jpeg");

        changeDir("lasgolondrinas.jpeg");
        changeDir("lasgolondrinas1.jpeg");
        changeDir("lasgolondrinas2.jpeg");
        changeDir("lasgolondrinas3.jpeg");
        changeDir("lasgolondrinas4.jpeg");
        changeDir("lasgolondrinas5.jpeg");
        changeDir("lasgolondrinas6.jpeg");

        changeDir("lomablanca.jpeg");
        changeDir("lomablanca1.jpeg");
        changeDir("lomablanca2.jpeg");
        changeDir("lomablanca3.jpeg");
        changeDir("lomablanca4.jpeg");
        changeDir("lomablanca5.jpeg");

        changeDir("playalarga.jpeg");
        changeDir("playalarga1.jpeg");
        changeDir("playalarga2.jpeg");
        changeDir("playalarga3.jpeg");

        changeDir("salton.jpeg");
        changeDir("salton1.jpeg");
        changeDir("salton2.jpeg");
        changeDir("salton3.jpeg");
        changeDir("salton4.jpeg");
        changeDir("salton5.jpeg");

        changeDir("duaba.jpeg");
        changeDir("duaba1.jpeg");
        changeDir("duaba2.jpeg");
        changeDir("duaba3.jpeg");
        changeDir("duaba4.jpeg");
        changeDir("duaba5.jpeg");

        changeDir("eltunque.jpeg");
        changeDir("eltunque1.jpeg");

        changeDir("playita.jpeg");
        changeDir("playita1.jpeg");
        changeDir("playita2.jpeg");
        changeDir("playita3.jpeg");
        changeDir("playita4.jpeg");

        changeDir("yacabo.jpeg");
        changeDir("yacabo1.jpeg");
        changeDir("yacabo2.jpeg");
        changeDir("yacabo3.jpeg");
        changeDir("yacabo4.jpeg");
        changeDir("yacabo5.jpeg");

    }

    private boolean validarPermisos() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }

        if((checkSelfPermission(ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) && (checkSelfPermission(ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)&& (checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)&& (checkSelfPermission(READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)){
            return true;
        }

        if((shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) || (shouldShowRequestPermissionRationale(ACCESS_COARSE_LOCATION))|| (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))|| (shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE))){
            cargaDialogConfirmacion();
        }else{
            requestPermissions(new String[] {WRITE_EXTERNAL_STORAGE,ACCESS_FINE_LOCATION,ACCESS_COARSE_LOCATION,READ_EXTERNAL_STORAGE},100);
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100){
            if(grantResults.length == 4 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED && grantResults[3] == PackageManager.PERMISSION_GRANTED ){
//                Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
            }else{
                solicitarPermisosManual();
            }
        }
    }

    private void solicitarPermisosManual() {
        final CharSequence[] opciones = {"Si","No"};
        final AlertDialog.Builder alertOption = new AlertDialog.Builder(Bienvenida.this);
        alertOption.setTitle("¿Desea configurar los permisos de forma manual?");
        alertOption.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if(opciones[i].equals("Si")){
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Bienvenida.this, "Los permisos no fueron aceptados", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                }
        );
    }

    private void cargaDialogConfirmacion() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(Bienvenida.this);
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestPermissions(new String[] {WRITE_EXTERNAL_STORAGE,ACCESS_FINE_LOCATION,READ_EXTERNAL_STORAGE,ACCESS_COARSE_LOCATION},100);
            }
        });
        dialogo.show();
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
            //Log.i(TAG, "Se copio el archivo...");
        } catch (IOException e) {
            Log.e(TAG, "No se pudo copiar..." + namefile);
            e.printStackTrace();
        }
    }

    public boolean fileExist(Context context, String filedir){
        File file = new File(filedir);
        if((file == null)||(!file.exists())) {
            Log.d(TAG,"no esta");
            return false;
        }
        Log.d(TAG,"ESTA");
        return true;
    }
}
