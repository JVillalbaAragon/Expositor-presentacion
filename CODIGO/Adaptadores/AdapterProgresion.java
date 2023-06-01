package com.example.eztrain.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eztrain.R;
import com.example.eztrain.models.Progresion;

import java.util.ArrayList;

public class AdapterProgresion extends RecyclerView.Adapter<AdapterProgresion.MainViewHolder> {

    //Interfaz para un clickListener
    public interface OnProgresionClickListener {
        void onProgresionClick(int position);
    }
    private OnProgresionClickListener onProgresionClickListener;
    private ArrayList<Progresion> progresionList;

    public AdapterProgresion(ArrayList<Progresion> progresionList, OnProgresionClickListener onProgresionClickListener){
        this.progresionList=progresionList;
        this.onProgresionClickListener = onProgresionClickListener;

    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progresionlist, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        Progresion progresion = progresionList.get(position);
        holder.txtNombre.setText(progresion.getNombre());
        //Comprobar si está completado o no
        if (progresion.isCompletado()){
            holder.foto.setImageResource(R.drawable.ic_check_circle_outline);
        }else holder.foto.setImageResource(progresion.getImgProgresion());

        // Agrega el OnClickListener al elemento de la lista
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onProgresionClickListener != null) {
                    onProgresionClickListener.onProgresionClick(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return progresionList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre;
        ImageView foto;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.fieldNombreProgresion);
            foto = itemView.findViewById(R.id.imgProgre);
        }
    }
    //On click listener para realizar una pequeña animación.
    public void setOnProgresionClickListener(OnProgresionClickListener onProgresionClickListener) {
        this.onProgresionClickListener = onProgresionClickListener;
    }
}
