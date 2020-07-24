package cu.serpro.campi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import cu.serpro.campi.entidades.Provincias;

class AdapterProvincias extends RecyclerView.Adapter<AdapterProvincias.ViewHolderDatos> implements View.OnClickListener{
    List<Provincias> listaProvincias;
    Context context;
    private View.OnClickListener listener;
    ConexionSQLiteHelper conn;      //conexion a la bd

    public AdapterProvincias(List<Provincias> listaProvincias, Context context){
        this.listaProvincias = listaProvincias;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_provincias,null,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        if(listaProvincias.get(position).getImagen() != null){
            cargarImagenWebService(listaProvincias.get(position).getImagen(),holder);
        }
        holder.nombreProvincia.setText( listaProvincias.get(position).getNombre());
        holder.cantCampismos.setText( listaProvincias.get(position).getCantCamp() + " Campismos");
    }

    private void cargarImagenWebService(String nombreArchivo, final ViewHolderDatos viewHolderDatos) {
        Bitmap bitmap = null;
        try{
            FileInputStream fileInputStream = new FileInputStream(context.getFilesDir().getPath() + "/" + nombreArchivo);
            bitmap = BitmapFactory.decodeStream(fileInputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        viewHolderDatos.imagenProvincias.setImageBitmap(bitmap);           ///SE LE PASA EL BITMAP A LA IMAGEN
    }


    @Override
    public int getItemCount() {
        return listaProvincias.size();
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
        TextView nombreProvincia,cantCampismos;
        ImageView imagenProvincias;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombreProvincia = (TextView) itemView.findViewById(R.id.nombreProvincia);
            cantCampismos = (TextView) itemView.findViewById(R.id.cantCampismos);
            imagenProvincias = (ImageView) itemView.findViewById(R.id.imagenProvincias);
        }
    }
}