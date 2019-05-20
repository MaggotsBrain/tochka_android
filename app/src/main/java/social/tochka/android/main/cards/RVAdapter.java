package social.tochka.android.main.cards;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import social.tochka.android.R;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder> {

    List<TochkaCard> cards;

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;

        CardViewHolder(CardView cv) {
            super(cv);
            cardView = cv;
        }
    }

    public RVAdapter(List<TochkaCard> cards) {
        this.cards = cards;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tochka_card_view, parent, false);
        return new CardViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int position) {
        CardView cardView = cardViewHolder.cardView;
        TextView longitude_degree_digit = cardView.findViewById(R.id.longitude_degree_digit);
        longitude_degree_digit.setText(cards.get(position).getLongitudeDegree());
        TextView longitude_minute_digit = cardView.findViewById(R.id.longitude_minute_digit);
        longitude_minute_digit.setText(cards.get(position).getLongitudeMinutes());
        TextView longitude_seconds_digit = cardView.findViewById(R.id.longitude_seconds_digit);
        longitude_seconds_digit.setText(cards.get(position).getLongitudeSeconds());
        TextView longitude_symbol = cardView.findViewById(R.id.longitude_symbol);
        longitude_symbol.setText(cards.get(position).getLongitudeSymbol());

        TextView latitude_degree_digit = cardView.findViewById(R.id.latitude_degree_digit);
        latitude_degree_digit.setText(cards.get(position).getLatitudeDegree());
        TextView latitude_minute_digit = cardView.findViewById(R.id.latitude_minute_digit);
        latitude_minute_digit.setText(cards.get(position).getLatitudeMinutes());
        TextView latitude_seconds_digit = cardView.findViewById(R.id.latitude_seconds_digit);
        latitude_seconds_digit.setText(cards.get(position).getLatitudeSeconds());
        TextView latitude_symbol = cardView.findViewById(R.id.latitude_symbol);
        latitude_symbol.setText(cards.get(position).getLatitudeSymbol());

        TextView text = cardView.findViewById(R.id.card_text);
        text.setText(cards.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}