package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter.EXTRA_KEY;


public class ProfilActivity extends AppCompatActivity {

    ImageView imgProfil;
    TextView txtProfil;
    TextView txtTitre;
    TextView txtWeb;
    TextView txtAdresse;
    TextView txtTel;
    ImageButton retour_Button;
    FloatingActionButton favoris_Button;
    Toolbar toolbar;

    private NeighbourApiService mApiService;

    private Neighbour profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        mApiService = DI.getNeighbourApiService();

        setUserInterface();
        setProfileDetails();
        settingFavoriteButton();
        retour_Button.setOnClickListener(v -> finish());
    }

    private void setUserInterface()
    {
        imgProfil = findViewById(R.id.imgProfil);
        txtProfil = findViewById(R.id.txtProfil);
        txtTitre = findViewById(R.id.txtTitre);
        txtWeb = findViewById(R.id.txtWeb);
        txtAdresse = findViewById(R.id.txtAdresse);
        txtTel = findViewById(R.id.txtTel);
        retour_Button = findViewById(R.id.retourButton);
        favoris_Button = findViewById(R.id.favorisButton);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setProfileDetails()
    {
        profil = getIntent().getParcelableExtra(EXTRA_KEY);
        Glide.with(this).load(profil.getAvatarUrl()).into(imgProfil);
        txtProfil.setText(profil.getName());
        txtTitre.setText(profil.getName());
        txtWeb.setText(String.format("%s%s", txtWeb.getText(), profil.getName()));
    }

    private void setFavoriteButtonInWhite()
    {
        favoris_Button.setImageResource(R.drawable.ic_star_white_24dp);
    }

    private void setFavoriteInYellow()
    {
        favoris_Button.setImageResource(R.drawable.ic_star_gold_24dp);
    }

    private void settingFavoriteButton()
    {
        if (!profil.isFavoris()){
            setFavoriteButtonInWhite();
        }
        else
            setFavoriteInYellow();
        favoris_Button.setOnClickListener(v -> {

            if (!profil.isFavoris()){
                mApiService.changeFavoris(profil);
                setFavoriteInYellow();
            }
            else
            {
                mApiService.changeFavoris(profil);
                setFavoriteButtonInWhite();
            }
        });
    }

}
