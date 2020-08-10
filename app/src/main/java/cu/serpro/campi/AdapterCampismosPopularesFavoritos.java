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

import cu.serpro.campi.entidades.Campismos;

class AdapterCampismosPopularesFavoritos extends RecyclerView.Adapter<AdapterCampismosPopularesFavoritos.ViewHolderDatos> implements View.OnClickListener{
    List<Campismos> listaCampismos;
    Context context;
    private View.OnClickListener listener;
    ConexionSQLiteHelper conn;      //conexion a la bd

    public AdapterCampismosPopularesFavoritos(List<Campismos> listaCampismos, Context context){
        this.listaCampismos = listaCampismos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_campismo_popularfavorito,null,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        if(listaCampismos.get(position).getImagen() != null){
            cargarImagenWebService(listaCampismos.get(position).getImagen(),holder);
        }
        holder.nombreCampismo.setText( listaCampismos.get(position).getTitulo());
        holder.ubicacion.setText( listaCampismos.get(position).getMunicipio() +", "+listaCampismos.get(position).getProvincia() );
    }

    private void cargarImagenWebService(String nombreArchivo, final ViewHolderDatos viewHolderDatos) {
        Bitmap bitmap = null;
        try{
            FileInputStream fileInputStream = new FileInputStream(context.getFilesDir().getPath() + "/" + nombreArchivo);
            bitmap = BitmapFactory.decodeStream(fileInputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        viewHolderDatos.imagenCampismo.setImageBitmap(bitmap);           ///SE LE PASA EL BITMAP A LA IMAGEN
    }

    @Override
    public int getItemCount() {
        return listaCampismos.size();
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
        TextView nombreCampismo,ubicacion;
        ImageView imagenCampismo,btnaccion;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombreCampismo = (TextView) itemView.findViewById(R.id.nombreCampismo);
            ubicacion = (TextView) itemView.findViewById(R.id.ubicacion);
            imagenCampismo = (ImageView) itemView.findViewById(R.id.imagenCampismo);
            btnaccion = (ImageView) itemView.findViewById(R.id.btnaccion);
        }
    }
}