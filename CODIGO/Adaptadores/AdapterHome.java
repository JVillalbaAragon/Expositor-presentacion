package com.example.eztrain.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.eztrain.R;
import com.example.eztrain.models.EjercicioAvanzado;

import java.util.ArrayList;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.MainViewHolder> {

    //Interfaz para un clickListener
    public interface OnExercisesClickListener {
        void onExercisesClick(int position);
    }
    private OnExercisesClickListener onExercisesClickListener;
    private ArrayList<EjercicioAvanzado> exerciseList;

    public AdapterHome(ArrayList<EjercicioAvanzado> exerciseList, OnExercisesClickListener onExercisesClickListener){
        this.exerciseList=exerciseList;
        this.onExercisesClickListener = onExercisesClickListener;

    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homelist, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        EjercicioAvanzado ejercicioAvanzado = exerciseList.get(position);

        holder.txtNombre.setText(ejercicioAvanzado.getNombre());
        holder.foto.setImageResource(ejercicioAvanzado.getImagen());
        holder.progreso.setProgress(ejercicioAvanzado.getProgreso());

        // Agrega el OnClickListener al elemento de la lista
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onExercisesClickListener != null) {
                    onExercisesClickListener.onExercisesClick(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre;
        ImageView foto;
        ProgressBar progreso;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.fieldNombreProgresion);
            foto = itemView.findViewById(R.id.imgAvanzado);
            progreso =  itemView.findViewById(R.id.progressBar);
        }
    }
    //On click listener para realizar una pequeña animación.
    public void setOnExercisesClickListener(OnExercisesClickListener onExerciseClickListener) {
        this.onExercisesClickListener = onExerciseClickListener;
    }
}
