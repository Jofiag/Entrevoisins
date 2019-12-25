package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


public class ProfilActivity extends AppCompatActivity {

    private NeighbourApiService mApiService;
    private ImageView imgProfil;
    private TextView txtProfil;
    private TextView txtTitre;
    private TextView txtWeb;
    private TextView txtAdresse;
    private TextView txtTel;
    private ImageButton retour_Button;
    private FloatingActionButton favoris_Button;
    private Neighbour profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        imgProfil = findViewById(R.id.imgProfil);
        txtProfil = findViewById(R.id.txtProfil);
        txtTitre = findViewById(R.id.txtTitre);
        txtWeb = findViewById(R.id.txtWeb);
        txtAdresse = findViewById(R.id.txtAdresse);
        txtTel = findViewById(R.id.txtTel);
        retour_Button = findViewById(R.id.retourButton);
        favoris_Button = findViewById(R.id.favorisButton);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /** recuperation de mon objet **/
        mApiService = DI.getNeighbourApiService();
        profil = getIntent().getParcelableExtra("profil");

        Glide.with(this).load(profil.getAvatarUrl()).into(imgProfil);

        txtProfil.setText(profil.getName());
        txtTitre.setText(profil.getName());
        txtWeb.setText(txtWeb.getText() + profil.getName());


        retour_Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (profil.isFavoris() == false){
            favoris_Button.setImageResource(R.drawable.ic_star_white_24dp);
        }
        else favoris_Button.setImageResource(R.drawable.ic_star_gold_24dp);

        favoris_Button.setOnClickListener(new View.OnClickListener() {

            /**
             * utilisation de ma methode de services changeFavoris
             * @param v
             */
            @Override
            public void onClick(View v) {
                if (profil.isFavoris() == false){
                    mApiService.changeFavoris(profil);
                    favoris_Button.setImageResource(R.drawable.ic_star_gold_24dp);
                }
                else if (profil.isFavoris() == true) {
                    mApiService.changeFavoris(profil);
                    favoris_Button.setImageResource(R.drawable.ic_star_white_24dp);
                }
            }
        });
    }

}
