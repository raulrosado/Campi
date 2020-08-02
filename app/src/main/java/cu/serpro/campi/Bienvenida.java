package cu.serpro.campi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

        button = findViewById(R.id.button);
        imageView2 = findViewById(R.id.imageView2);

        InputStream origen = null;
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

        //hago la coneccion con la BD
        conn = new ConexionSQLiteHelper(getApplicationContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


        if(validarPermisos()){
            Log.d(TAG, "se dio el permiso");
//            Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
        }else{
            Log.d(TAG, "no se dio el permiso");
            Toast.makeText(this, "Permiso no concedido", Toast.LENGTH_SHORT).show();
        }

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
            Log.i(TAG, "Se copio el archivo...");
        } catch (IOException e) {
            Log.e(TAG, "No se pudo copiar...");
            e.printStackTrace();
        }
    }
}
