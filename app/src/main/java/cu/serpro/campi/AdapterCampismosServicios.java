package cu.serpro.campi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import cu.serpro.campi.entidades.Campismos;
import cu.serpro.campi.entidades.Servicios;

class AdapterCampismosServicios extends RecyclerView.Adapter<AdapterCampismosServicios.ViewHolderDatos> implements View.OnClickListener{
    List<Servicios> listaServicios;
    Context context;
    private View.OnClickListener listener;
    ConexionSQLiteHelper conn;      //conexion a la bd

    public AdapterCampismosServicios(List<Servicios> listaServicios, Context context){
        this.listaServicios = listaServicios;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servisios,null,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        if(listaServicios.get(position).getImagen() != null){
            cargarImagenWebService(listaServicios.get(position).getImagen(),holder);
        }
        //Log.d("CAMPILOG", listaServicios.get(position).getImagen());
    }

    private void cargarImagenWebService(String nombreArchivo, final ViewHolderDatos viewHolderDatos) {
        Bitmap bitmap = null;
        try{
            FileInputStream fileInputStream = new FileInputStream(context.getFilesDir().getPath() + "/" + nombreArchivo);
            bitmap = BitmapFactory.decodeStream(fileInputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        viewHolderDatos.imgservice.setImageBitmap(bitmap);           ///SE LE PASA EL BITMAP A LA IMAGEN
    }

    @Override
    public int getItemCount() {
        return listaServicios.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        ImageView imgservice;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            imgservice = (ImageView) itemView.findViewById(R.id.imgservice);
        }
    }
}