package com.example.chordify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VariantsAdapter extends RecyclerView.Adapter<VariantsAdapter.VariantViewHolder> {
    private List<ChordVariant> variants;
    private Context context;
    private PreferencesHelper preferencesHelper;

    public VariantsAdapter(Context context, List<ChordVariant> variants) {
        this.context = context;
        this.variants = variants;
        this.preferencesHelper = new PreferencesHelper(context);
    }

    @NonNull
    @Override
    public VariantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chord_variant_item, parent, false);
        return new VariantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VariantViewHolder holder, int position) {
        ChordVariant variant = variants.get(position);

        holder.chordImage.setImageResource(variant.getImageResource());
        holder.chordName.setText(variant.getName()); // Устанавливаем название
        holder.favoriteButton.setImageResource(
                variant.isFavorite() ? R.drawable.ic_star_gold : R.drawable.ic_star
        );

        // Обработчик для кнопки "избранное"
        holder.favoriteButton.setOnClickListener(v -> {
            boolean isFavorite = !variant.isFavorite();
            variant.setFavorite(isFavorite);
            holder.favoriteButton.setImageResource(
                    isFavorite ? R.drawable.ic_star_gold : R.drawable.ic_star
            );
            preferencesHelper.saveFavorite(variant.getName(), isFavorite);
            notifyItemChanged(holder.getAdapterPosition()); // Обновляем позицию
        });
    }

    @Override
    public int getItemCount() {
        return variants.size();
    }

    static class VariantViewHolder extends RecyclerView.ViewHolder {
        ImageView chordImage;
        TextView chordName;
        ImageButton favoriteButton;

        VariantViewHolder(@NonNull View itemView) {
            super(itemView);
            chordImage = itemView.findViewById(R.id.chordImage);
            chordName = itemView.findViewById(R.id.chordName);
            favoriteButton = itemView.findViewById(R.id.favoriteButton);
        }
    }
}

