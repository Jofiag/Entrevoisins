package com.openclassrooms.entrevoisins.controlleur;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


import java.io.Serializable;

import butterknife.BindDrawable;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter.BUNDLE_NEIGHBOUR_SELECTED;

public class NeighbourDetailsActivity extends AppCompatActivity
{
    private ImageView mNeighbourAvatar;
    private ImageButton mBackButton;
    private TextView mNeighbourNameOnAvatar;
    private TextView mNeighbourNameOnCardView;
    private FloatingActionButton mFavoriteButton;
    private TextView mNeighbourFacebook;

    @BindDrawable(R.drawable.ic_star_border_white_24dp)
    public Drawable mStarWithBorderWhite;

    @BindDrawable(R.drawable.ic_star_yellow_24dp)
    public Drawable mStarYellow;

    //Récupération du neighbour sélectionné
   private Neighbour mNeighbour;

   private NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);

        mApiService = DI.getNeighbourApiService();

        //Récupération de neighbour
        if (getIntent() != null)
            mNeighbour = (Neighbour) getIntent().getSerializableExtra(BUNDLE_NEIGHBOUR_SELECTED);
        else
            finish();

        //Référenciation
        mNeighbourAvatar = findViewById(R.id.neighbour_avatar_img);
        mBackButton = findViewById(R.id.back_button_btn);
        mNeighbourNameOnAvatar = findViewById(R.id.neighbour_name_in_avatar_txt);
        mNeighbourNameOnCardView = findViewById(R.id.neighbour_name_in_cardv_txt);
        mFavoriteButton = findViewById(R.id.favorite_button);
        mNeighbourFacebook = findViewById(R.id.neighbour_web_txt);

        //Mise en place de chaque détails correspondants
        Glide.with(NeighbourDetailsActivity.this)
                .load(mNeighbour.getAvatarUrl())
                .into(mNeighbourAvatar);
        mNeighbourNameOnAvatar.setText(mNeighbour.getName());
        mNeighbourNameOnCardView.setText(mNeighbour.getName());
        mNeighbourFacebook.setText(String.format("www.facebook.com/%s", mNeighbour.getName()));

        //Activation du boutton retour
        mBackButton.setOnClickListener(v -> finish());

        //Activation du boutton favori
        onFavoriteButton();
        /*mFavoriteButton.setOnClickListener(v -> {
            if (mNeighbour.getIsFavorite() == false)
            {
                mNeighbour.setIsFavorite(true);
                mFavoriteButton.setOnClickListener(view -> mFavoriteButton.setImageDrawable(mStarYellow));
            }
            else
            {
                mNeighbour.setIsFavorite(false);
                mFavoriteButton.setOnClickListener(view -> mFavoriteButton.setImageDrawable(mStarWithBorderWhite));
            }
        });*/

    }

    private void onFavoriteButton()
    {
        if (mNeighbour.getIsFavorite())
            mFavoriteButton.setImageDrawable(mStarWithBorderWhite);
        else
            mFavoriteButton.setImageDrawable(mStarYellow);

        mFavoriteButton.setOnClickListener(v ->{
            mApiService.toggleFavorite(mNeighbour);
           mNeighbour.setIsFavorite(!mNeighbour.getIsFavorite());
           onFavoriteButton();
        });
    }
}