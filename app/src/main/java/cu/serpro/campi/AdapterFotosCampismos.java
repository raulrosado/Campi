package cu.serpro.campi;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.chrisbanes.photoview.PhotoView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import cu.serpro.campi.entidades.Campismos;
import cu.serpro.campi.entidades.Fotos;

class AdapterFotosCampismos extends RecyclerView.Adapter<AdapterFotosCampismos.ViewHolderDatos> implements View.OnClickListener{
    List<Fotos> listaFotosCampismos;
    Context context;
    private View.OnClickListener listener;
    ConexionSQLiteHelper conn;      //conexion a la bd
    final Dialog dialog;

    public AdapterFotosCampismos(List<Fotos> listaFotosCampismos, Context context, Activity actividad){
        this.listaFotosCampismos = listaFotosCampismos;
        this.context = context;
        dialog = new Dialog(actividad);
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photos_campis,null,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        if(listaFotosCampismos.get(position).getNombre() != null){
            cargarImagenWebService(listaFotosCampismos.get(position).getNombre(),holder);
        }

        holder.photosCampismos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mostrardialog(listaFotosCampismos.get(position).getNombre());
            }
        });
    }

    private void mostrardialog(String nombreArchivo) {
        dialog.setContentView(R.layout.dialog_imagen);
        PhotoView imageView = dialog.findViewById(R.id.imagenAmpliada);

        Bitmap bitmap = null;
        try{
            FileInputStream fileInputStream = new FileInputStream(context.getFilesDir().getPath() + "/" + nombreArchivo);
            bitmap = BitmapFactory.decodeStream(fileInputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        imageView.setImageBitmap(bitmap);           ///SE LE PASA EL BITMAP A LA IMAGEN
        dialog.show();

    }

    private void cargarImagenWebService(String nombreArchivo, final ViewHolderDatos viewHolderDatos) {
        Bitmap bitmap = null;
        try{
            FileInputStream fileInputStream = new FileInputStream(context.getFilesDir().getPath() + "/" + nombreArchivo);
            bitmap = BitmapFactory.decodeStream(fileInputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        viewHolderDatos.photosCampismos.setImageBitmap(bitmap);           ///SE LE PASA EL BITMAP A LA IMAGEN
    }


    @Override
    public int getItemCount() {
        return listaFotosCampismos.size();
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
        ImageView photosCampismos;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            photosCampismos = (ImageView) itemView.findViewById(R.id.photosCampismos);
        }
    }
}