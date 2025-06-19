
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ChordsAdapter extends RecyclerView.Adapter<ChordsAdapter.ChordViewHolder> {
    private List<Chord> chords;
    private Context context;

    public ChordsAdapter(Context context, List<Chord> chords) {
        this.context = context;
        this.chords = chords;
    }

    @NonNull
    @Override
    public ChordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chord_item, parent, false);
        return new ChordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChordViewHolder holder, int position) {
        Chord chord = chords.get(position);
        holder.chordName.setText(chord.getName());
        holder.notation.setImageResource(chord.getNotationImage());
    }

    @Override
    public int getItemCount() {
        return chords.size();
    }

    static class ChordViewHolder extends RecyclerView.ViewHolder {
        TextView chordName;
        ImageView notation;

        ChordViewHolder(@NonNull View itemView) {
            super(itemView);
            chordName = itemView.findViewById(R.id.chordName);
            notation = itemView.findViewById(R.id.notation);
        }
    }
}