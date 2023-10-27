package com.example.magnona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BasraAdapter extends RecyclerView.Adapter<BasraAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    BasraAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.basra_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SetCard(holder.basra_num1,holder.basra_num2,holder.basra_suit,mData.get(position));
        //holder.basra_card.setImageResource(R.drawable.mData);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView basra_suit;
        TextView basra_num1;
        TextView basra_num2;

        ViewHolder(View itemView) {
            super(itemView);
            basra_suit = itemView.findViewById(R.id.basra_suit);
            basra_num1 = itemView.findViewById(R.id.basra_num1);
            basra_num2 = itemView.findViewById(R.id.basra_num2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }

    void SetCard(TextView numText1 ,TextView numText2 , ImageView suit , String tag){

        char card_s = tag.charAt(0);
        char card_n = tag.charAt(1);

        if (card_n == '1'){
            numText1.setText(String.valueOf(10));
            numText2.setText(String.valueOf(10)); }
        else {
            numText1.setText(String.valueOf(card_n));
            numText2.setText(String.valueOf(card_n)); }

        if (card_s == 'S') {
            suit.setImageResource(R.drawable.suit_spades);
            numText1.setTextColor(0xFF000000);
            numText2.setTextColor(0xFF000000); }
        else if (card_s == 'H') {
            suit.setImageResource(R.drawable.suit_hearts);
            numText1.setTextColor(0xFFC20C0C);
            numText2.setTextColor(0xFFC20C0C); }
        else if (card_s == 'D') {
            suit.setImageResource(R.drawable.suit_diamond);
            numText1.setTextColor(0xFFC20C0C);
            numText2.setTextColor(0xFFC20C0C); }
        else {
            suit.setImageResource(R.drawable.suit_clubs);
            numText1.setTextColor(0xFF000000);
            numText2.setTextColor(0xFF000000); }

        suit.setTag(tag);
    }

}
