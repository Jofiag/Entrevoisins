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

import butterknife.BindView;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter.EXTRA_KEY;


public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.imgProfil)
    public ImageView imgProfile;
    @BindView(R.id.txtProfil)
    public TextView txtProfile;
    @BindView(R.id.txtTitre)
    public TextView txtTitre;
    @BindView(R.id.txtWeb)
    public TextView txtWeb;
    @BindView(R.id.retourButton)
    public ImageButton back_Button;
    @BindView(R.id.favorisButton)
    public FloatingActionButton favoriteButton;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    private NeighbourApiService mApiService;
    private Neighbour profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        setSupportActionBar(toolbar);

        mApiService = DI.getNeighbourApiService();

        setProfileDetails();

        back_Button.setOnClickListener(v -> finish());

        setFavoriteButtonImage();

        favoriteButtonListener();
    }

    private void setProfileDetails()
    {
        profile = getIntent().getParcelableExtra(EXTRA_KEY);

        Glide.with(this).load(profile.getAvatarUrl()).into(imgProfile);
        txtProfile.setText(profile.getName());
        txtTitre.setText(profile.getName());
        txtWeb.setText(String.format("%s %s", txtWeb.getText(), profile.getName()));
    }

    private void putFavoriteButtonInWhite()
    {
        favoriteButton.setImageResource(R.drawable.ic_star_white_24dp);
    }

    private void putFavoriteButtonInYellow()
    {
        favoriteButton.setImageResource(R.drawable.ic_star_gold_24dp);
    }


    private void setFavoriteButtonImage()
    {
        if (!profile.isFavoris())
            putFavoriteButtonInWhite();
        else
            putFavoriteButtonInYellow();
    }

    private void favoriteButtonListener()
    {
        favoriteButton.setOnClickListener(v -> {
            if (!profile.isFavoris()){
                mApiService.changeFavorite(profile);
                putFavoriteButtonInYellow();
            }
            else {
                mApiService.changeFavorite(profile);
                putFavoriteButtonInWhite();
            }
        });
    }
}
