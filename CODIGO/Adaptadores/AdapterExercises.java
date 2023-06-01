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
import com.example.eztrain.models.EjercicioConvencional;

import java.util.ArrayList;

public class AdapterExercises extends RecyclerView.Adapter<AdapterExercises.ExercisesViewHolder> {

    // Interfaz para clickListener
    public interface OnExercisesClickListener {
        void onExercisesClick(int position);
    }

    private OnExercisesClickListener onExercisesClickListener;
    private ArrayList<EjercicioConvencional> exercisesList;

    public AdapterExercises(ArrayList<EjercicioConvencional> exercisesList, OnExercisesClickListener onExercisesClickListener) {
        this.exercisesList = exercisesList;
        this.onExercisesClickListener = onExercisesClickListener;
    }


    @NonNull
    @Override
    public ExercisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exerciselist, parent, false);
        return new ExercisesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesViewHolder holder, int position) {
        EjercicioConvencional ejercicioConvencional = exercisesList.get(position);

            holder.txtNombre.setText(ejercicioConvencional.getNombre());
            holder.foto.setImageResource(ejercicioConvencional.getImagen());

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
        return exercisesList.size();
    }

    public static class ExercisesViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre;
        ImageView foto;

        public ExercisesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.exName);
            foto = itemView.findViewById(R.id.exImg);
        }
    }

    // On click listener para realizar una pequeña animación.
    public void setOnExercisesClickListener(OnExercisesClickListener onExerciseClickListener) {
        this.onExercisesClickListener = onExerciseClickListener;
    }
}