package cu.serpro.campi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
        changeDir("pajarito.jpeg");
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
