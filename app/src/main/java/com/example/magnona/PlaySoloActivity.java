package com.example.magnona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class PlaySoloActivity extends AppCompatActivity {

    BasraAdapter adapter;

    ArrayList<String> CardsList = new ArrayList<String>(Arrays.asList(
            "SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK",
            "HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK",
            "DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK",
            "CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK"));

    List<String> BlueBasra = new ArrayList<String>();

    List<String> RedBasra = new ArrayList<String>();

    String[] comp1Hand;
    String[] comp2Hand;
    String[] comp3Hand;

    int deck_cards_num = 0;

    int red_cards_num = 0;
    int red_cards_basra = 0;

    int blue_cards_number = 0;
    int blue_cards_basra = 0;

    int ca = 0;
    int c2 = 0;
    int c3 = 0;
    int c4 = 0;
    int c5 = 0;
    int c6 = 0;
    int c7 = 0;
    int c8 = 0;
    int c9 = 0;
    int c1 = 0;
    int cj = 0;
    int cq = 0;
    int ck = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_solo);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            final WindowInsetsController insetsController = getWindow().getInsetsController();
            if (insetsController != null) {
                insetsController.hide(WindowInsets.Type.statusBars());
                insetsController.hide(WindowInsets.Type.navigationBars());
            }
        } else {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
        }

        /*      -- Initializing Xml --      */
        final RecyclerView basraRyc = findViewById(R.id.basary_rec);
        basraRyc.setLayoutManager(new GridLayoutManager(this, 6));

        final RelativeLayout my_card_1 = findViewById(R.id.my_card_1);
        final ImageView my_card_1_suit = findViewById(R.id.my_card_1_suit);
        final TextView my_card_1_num = findViewById(R.id.my_card_1_num);
        my_card_1.setVisibility(View.INVISIBLE);

        final RelativeLayout my_card_2 = findViewById(R.id.my_card_2);
        final ImageView my_card_2_suit = findViewById(R.id.my_card_2_suit);
        final TextView my_card_2_num = findViewById(R.id.my_card_2_num);
        my_card_2.setVisibility(View.INVISIBLE);

        final RelativeLayout my_card_3 = findViewById(R.id.my_card_3);
        final ImageView my_card_3_suit = findViewById(R.id.my_card_3_suit);
        final TextView my_card_3_num = findViewById(R.id.my_card_3_num);
        my_card_3.setVisibility(View.INVISIBLE);

        final RelativeLayout my_card_4 = findViewById(R.id.my_card_4);
        final ImageView my_card_4_suit = findViewById(R.id.my_card_4_suit);
        final TextView my_card_4_num = findViewById(R.id.my_card_4_num);
        my_card_4.setVisibility(View.INVISIBLE);

        final ImageButton my_pp = findViewById(R.id.my_pp);
        final ImageButton pp1 = findViewById(R.id.pp1);
        final ImageButton pp2 = findViewById(R.id.pp2);
        final ImageButton pp3 = findViewById(R.id.pp3);

        final ToggleButton blue_cards = findViewById(R.id.blue_cards);
        final ToggleButton red_cards = findViewById(R.id.red_cards);

        final RelativeLayout basra_window = findViewById(R.id.basra_window);

        final RelativeLayout top_card = findViewById(R.id.top_card);
        final ImageView top_card_suit = findViewById(R.id.top_card_suit);
        final TextView top_card_num1 = findViewById(R.id.top_card_num1);
        final TextView top_card_num2 = findViewById(R.id.top_card_num2);
        top_card.setVisibility(View.INVISIBLE);

        final Button show_score = findViewById(R.id.show_score);
        final ImageButton hide_score = findViewById(R.id.hide_score);
        final RelativeLayout score_board = findViewById(R.id.score_board);

        final TextView cards_num = findViewById(R.id.cards_num);
        final TextView blue_cards_num = findViewById(R.id.blue_cards_num);

        final TextView dealer = findViewById(R.id.dealer);
        RelativeLayout.LayoutParams dealerLayoutParams = (RelativeLayout.LayoutParams) dealer.getLayoutParams();

        setDealer(dealerLayoutParams,my_pp,dealer);

        final ImageView next_player = findViewById(R.id.next_player);
        RelativeLayout.LayoutParams PlayerLayoutParams = (RelativeLayout.LayoutParams) next_player.getLayoutParams();

        setPlayer(PlayerLayoutParams,my_pp,next_player);

        final MediaPlayer playing_card = MediaPlayer.create(this,R.raw.played_card);

        final RelativeLayout WarningMsg = findViewById(R.id.WarningMsg);
        final TextView GroundCards1_num = findViewById(R.id.GroundCards1_num);
        final ImageView GroundCards1_suit = findViewById(R.id.GroundCards1_suit);
        final TextView GroundCards2_num = findViewById(R.id.GroundCards2_num);
        final ImageView GroundCards2_suit = findViewById(R.id.GroundCards2_suit);
        final TextView GroundCards3_num = findViewById(R.id.GroundCards3_num);
        final ImageView GroundCards3_suit = findViewById(R.id.GroundCards3_suit);
        final TextView GroundCards4_num = findViewById(R.id.GroundCards4_num);
        final ImageView GroundCards4_suit = findViewById(R.id.GroundCards4_suit);
        final TextView DealerCard_num = findViewById(R.id.DealerCard_num);
        final ImageView DealerCard_suit = findViewById(R.id.DealerCard_suit);
        final Button MsgDismiss = findViewById(R.id.MsgDismiss);

        final TextView test = findViewById(R.id.test);

        MsgDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WarningMsg.setVisibility(View.INVISIBLE);
            }
        });

        my_pp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                Collections.shuffle(CardsList,new Random(System.nanoTime()));

                my_card_1.setVisibility(View.VISIBLE);
                my_card_2.setVisibility(View.VISIBLE);
                my_card_3.setVisibility(View.VISIBLE);
                my_card_4.setVisibility(View.VISIBLE);

                WarningMsg.setVisibility(View.VISIBLE);
                SetCard(GroundCards1_num,GroundCards1_suit,CardsList.get(0));
                IncCount(String.valueOf(GroundCards1_suit.getTag()));
                SetCard(GroundCards2_num,GroundCards2_suit,CardsList.get(1));
                IncCount(String.valueOf(GroundCards2_suit.getTag()));
                SetCard(GroundCards3_num,GroundCards3_suit,CardsList.get(2));
                IncCount(String.valueOf(GroundCards3_suit.getTag()));
                SetCard(GroundCards4_num,GroundCards4_suit,CardsList.get(3));
                IncCount(String.valueOf(GroundCards4_suit.getTag()));

                top_card.setVisibility(View.VISIBLE);
                SetTopCard(top_card_num1,top_card_num2,top_card_suit,CardsList.get(3));

                SetCard(DealerCard_num,DealerCard_suit,CardsList.get(51));

                SetCard(my_card_1_num,my_card_1_suit,CardsList.get(4));
                SetCard(my_card_2_num,my_card_2_suit,CardsList.get(5));
                SetCard(my_card_3_num,my_card_3_suit,CardsList.get(6));
                SetCard(my_card_4_num,my_card_4_suit,CardsList.get(7));

                comp1Hand = new String[]{CardsList.get(8),CardsList.get(9),CardsList.get(10),CardsList.get(11)};

                SetTopCard(top_card_num1,top_card_num2,top_card_suit,comp1Hand[computerPlay(comp1Hand)]);

                System.out.println(CardsList);
            }
        });

        /*      -- Playing Cards --      */

        my_card_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playing_card.start();
                IncCount(String.valueOf(my_card_1_suit.getTag()));

                if (checkBasra(String.valueOf(top_card_suit.getTag()) , String.valueOf(my_card_1_suit.getTag()))) {
                    blue_cards_basra = blue_cards_basra + 1 ;
                    blue_cards_number = blue_cards_number + deck_cards_num;
                    deck_cards_num = 0;
                    blue_cards_num.setText(String.valueOf(blue_cards_number + blue_cards_basra));
                    BlueBasra.add((String) top_card_suit.getTag());
                    top_card.setVisibility(View.INVISIBLE);
                    blue_cards.setText(String.valueOf(blue_cards_basra));
                }
                else{
                    top_card.setVisibility(View.VISIBLE);
                    ChangeCard(my_card_1_num,my_card_1_suit,top_card_num1,top_card_num2,top_card_suit);
                    deck_cards_num = deck_cards_num + 1 ;
                }
                my_card_1.setVisibility(View.INVISIBLE);
                cards_num.setText(String.valueOf(deck_cards_num));
                test.setText(String.valueOf(ca) + String.valueOf(c2) + String.valueOf(c3) + String.valueOf(c4) + String.valueOf(c5) + String.valueOf(c6) + String.valueOf(c7)
                        + String.valueOf(c8) + String.valueOf(c9) + String.valueOf(c1) + String.valueOf(cj) + String.valueOf(cq) + String.valueOf(ck));
            }
        });

        my_card_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playing_card.start();
                IncCount(String.valueOf(my_card_2_suit.getTag()));

                if (checkBasra(String.valueOf(top_card_suit.getTag()) , String.valueOf(my_card_2_suit.getTag()))) {
                    blue_cards_basra = blue_cards_basra + 1 ;
                    blue_cards_number = blue_cards_number + deck_cards_num;
                    deck_cards_num = 0;
                    blue_cards_num.setText(String.valueOf(blue_cards_number + blue_cards_basra));
                    BlueBasra.add((String) top_card_suit.getTag());
                    top_card.setVisibility(View.INVISIBLE);
                    blue_cards.setText(String.valueOf(blue_cards_basra));
                }
                else{
                    top_card.setVisibility(View.VISIBLE);
                    ChangeCard(my_card_2_num,my_card_2_suit,top_card_num1,top_card_num2,top_card_suit);
                    deck_cards_num = deck_cards_num + 1 ;
                }
                my_card_2.setVisibility(View.INVISIBLE);
                cards_num.setText(String.valueOf(deck_cards_num));
                test.setText(String.valueOf(ca) + String.valueOf(c2) + String.valueOf(c3) + String.valueOf(c4) + String.valueOf(c5) + String.valueOf(c6) + String.valueOf(c7)
                        + String.valueOf(c8) + String.valueOf(c9) + String.valueOf(c1) + String.valueOf(cj) + String.valueOf(cq) + String.valueOf(ck));
            }
        });

        my_card_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playing_card.start();
                IncCount(String.valueOf(my_card_3_suit.getTag()));

                if (checkBasra(String.valueOf(top_card_suit.getTag()) , String.valueOf(my_card_3_suit.getTag()))) {
                    blue_cards_basra = blue_cards_basra + 1 ;
                    blue_cards_number = blue_cards_number + deck_cards_num;
                    deck_cards_num = 0;
                    blue_cards_num.setText(String.valueOf(blue_cards_number + blue_cards_basra));
                    BlueBasra.add((String) top_card_suit.getTag());
                    top_card.setVisibility(View.INVISIBLE);
                    blue_cards.setText(String.valueOf(blue_cards_basra));
                }
                else{
                    top_card.setVisibility(View.VISIBLE);
                    ChangeCard(my_card_3_num,my_card_3_suit,top_card_num1,top_card_num2,top_card_suit);
                    deck_cards_num = deck_cards_num + 1 ;
                }
                my_card_3.setVisibility(View.INVISIBLE);
                cards_num.setText(String.valueOf(deck_cards_num));
                test.setText(String.valueOf(ca) + String.valueOf(c2) + String.valueOf(c3) + String.valueOf(c4) + String.valueOf(c5) + String.valueOf(c6) + String.valueOf(c7)
                        + String.valueOf(c8) + String.valueOf(c9) + String.valueOf(c1) + String.valueOf(cj) + String.valueOf(cq) + String.valueOf(ck));
            }
        });

        my_card_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playing_card.start();
                IncCount(String.valueOf(my_card_4_suit.getTag()));

                if (checkBasra(String.valueOf(top_card_suit.getTag()) , String.valueOf(my_card_4_suit.getTag()))) {
                    blue_cards_basra = blue_cards_basra + 1 ;
                    blue_cards_number = blue_cards_number + deck_cards_num;
                    deck_cards_num = 0;
                    blue_cards_num.setText(String.valueOf(blue_cards_number + blue_cards_basra));
                    BlueBasra.add((String) top_card_suit.getTag());
                    top_card.setVisibility(View.INVISIBLE);
                    blue_cards.setText(String.valueOf(blue_cards_basra));
                }
                else{
                    top_card.setVisibility(View.VISIBLE);
                    ChangeCard(my_card_4_num,my_card_4_suit,top_card_num1,top_card_num2,top_card_suit);
                    deck_cards_num = deck_cards_num + 1 ;
                }
                my_card_4.setVisibility(View.INVISIBLE);
                cards_num.setText(String.valueOf(deck_cards_num));
                test.setText(String.valueOf(ca) + String.valueOf(c2) + String.valueOf(c3) + String.valueOf(c4) + String.valueOf(c5) + String.valueOf(c6) + String.valueOf(c7)
                            + String.valueOf(c8) + String.valueOf(c9) + String.valueOf(c1) + String.valueOf(cj) + String.valueOf(cq) + String.valueOf(ck));
            }
        });

        /*      -- Basary Shapes --      */

        blue_cards.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    basra_window.setVisibility(View.VISIBLE);
                    System.out.println(BlueBasra);
                    adapter = new BasraAdapter(getApplicationContext(),BlueBasra);
                    basraRyc.setAdapter(adapter);
                } else {
                    basra_window.setVisibility(View.INVISIBLE);
                }
            }
        });

        red_cards.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    basra_window.setVisibility(View.VISIBLE);
                    System.out.println(BlueBasra);
                    adapter = new BasraAdapter(getApplicationContext(),RedBasra);
                    basraRyc.setAdapter(adapter);
                } else {
                    basra_window.setVisibility(View.INVISIBLE);
                }
            }
        });

        /*      -- Score Sheet --      */

        show_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score_board.setVisibility(View.VISIBLE);
                show_score.setVisibility(View.INVISIBLE);
            }
        });

        hide_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score_board.setVisibility(View.INVISIBLE);
                show_score.setVisibility(View.VISIBLE);
            }
        });

    }

    /*      -- My Functions --      */

    void IncCount(String tag){
        char number = tag.charAt(1);

        if(number == 'A'){ ca++; }
        else if(number == '2'){ c2++; }
        else if(number == '3'){ c3++; }
        else if(number == '4'){ c4++; }
        else if(number == '5'){ c5++; }
        else if(number == '6'){ c6++; }
        else if(number == '7'){ c7++; }
        else if(number == '8'){ c8++; }
        else if(number == '9'){ c9++; }
        else if(number == '1'){ c1++; }
        else if(number == 'J'){ cj++; }
        else if(number == 'Q'){ cq++; }
        else if(number == 'K'){ ck++; }

    }

    void SetCard(TextView numText , ImageView suit , String tag){

        char card_s = tag.charAt(0);
        char card_n = tag.charAt(1);

        if (card_n == '1'){
            numText.setText(String.valueOf(10)); }
        else {
        numText.setText(String.valueOf(card_n)); }

        if (card_s == 'S') {
            suit.setImageResource(R.drawable.suit_spades);
            numText.setTextColor(0xFF000000); }
        else if (card_s == 'H') {
            suit.setImageResource(R.drawable.suit_hearts);
            numText.setTextColor(0xFFC20C0C); }
        else if (card_s == 'D') {
            suit.setImageResource(R.drawable.suit_diamond);
            numText.setTextColor(0xFFC20C0C); }
        else {
            suit.setImageResource(R.drawable.suit_clubs);
            numText.setTextColor(0xFF000000); }

        suit.setTag(tag);
    }

    void SetTopCard(TextView numText1 , TextView numText2 , ImageView suit , String tag){

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

    void ChangeCard(TextView numTextFrom , ImageView suitFrom ,TextView numTextTo ,TextView numTextTo2 , ImageView suitTo){
        numTextTo.setText(numTextFrom.getText());
        numTextTo2.setText(numTextFrom.getText());
        numTextTo.setTextColor(numTextFrom.getTextColors());
        numTextTo2.setTextColor(numTextFrom.getTextColors());
        suitTo.setImageDrawable(suitFrom.getDrawable());
        suitTo.setTag(suitFrom.getTag());
    }

    boolean checkBasra(String card1 , String card2) {
        boolean result = FALSE ;

        char card1_num = card1.charAt(1);
        char card2_num = card2.charAt(1);

        if (card1_num == card2_num){
            result = TRUE;
        }

        return result;
    }

    void setDealer(RelativeLayout.LayoutParams LayoutParams , ImageView pp , TextView d){
        LayoutParams.addRule(RelativeLayout.ALIGN_END,pp.getId());
        LayoutParams.addRule(RelativeLayout.ALIGN_BOTTOM,pp.getId());
        d.setLayoutParams(LayoutParams);
    }

    void setPlayer(RelativeLayout.LayoutParams LayoutParams , ImageView pp , ImageView player){
        LayoutParams.addRule(RelativeLayout.ALIGN_END,pp.getId());
        LayoutParams.addRule(RelativeLayout.ALIGN_TOP,pp.getId());
        player.setLayoutParams(LayoutParams);
    }

    int computerPlay(String[] computerHand){

        int[] sortingArray = {0,0,0,0};

        char cardNum;

        int my_return = 5;

        for (int i = 0 ; i < computerHand.length ; i++) {

            cardNum = computerHand[i].charAt(1);

            if (cardNum == 'A') {
                sortingArray[i] = ca;
            } else if (cardNum == '2') {
                sortingArray[i] = c2;
            } else if (cardNum == '3') {
                sortingArray[i] = c3;
            } else if (cardNum == '4') {
                sortingArray[i] = c4;
            } else if (cardNum == '5') {
                sortingArray[i] = c5;
            } else if (cardNum == '6') {
                sortingArray[i] = c6;
            } else if (cardNum == '7') {
                sortingArray[i] = c7;
            } else if (cardNum == '8') {
                sortingArray[i] = c8;
            } else if (cardNum == '9') {
                sortingArray[i] = c9;
            } else if (cardNum == '1') {
                sortingArray[i] = c1;
            } else if (cardNum == 'J') {
                sortingArray[i] = cj;
            } else if (cardNum == 'Q') {
                sortingArray[i] = cq;
            } else if (cardNum == 'K') {
                sortingArray[i] = ck;
            }
        }

        System.out.println(Arrays.toString(sortingArray));

        if (sortingArray[0] > sortingArray [1] && sortingArray[0] > sortingArray[2] && sortingArray[0] > sortingArray[3]){
            my_return = 0;
        }
        else if (sortingArray[1] > sortingArray [0] && sortingArray[1] > sortingArray[2] && sortingArray[1] > sortingArray[3]){
            my_return = 1;
        }
        else if (sortingArray[2] > sortingArray [1] && sortingArray[2] > sortingArray[0] && sortingArray[2] > sortingArray[3]){
            my_return = 2;
        }
        else if (sortingArray[3] > sortingArray [1] && sortingArray[3] > sortingArray[2] && sortingArray[3] > sortingArray[0]){
            my_return = 3;
        }

        return my_return;
    }

}