package com.openclassrooms.entrevoisins.controlleur;

import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter.BUNDLE_NEIGHBOUR_SELECTED;

public class NeighbourDetailsActivity extends AppCompatActivity
{
    private ImageView mImageView;
    private ImageButton mBackButton;
    private TextView mNeighbourNameOnAvatar;
    private TextView mNeighbourNameOnCardView;
    private FloatingActionButton mFavoriteButton;
    private TextView mNeighbourAddress;
    private TextView mNeighbourPhoneNumber;
    private TextView mNeighbourFacebook;
    private TextView mNeighbourMoreAbout;

    private Neighbour mNeighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);

        //Référenciation
        mImageView = findViewById(R.id.neighbour_avatar_img);
        mBackButton = findViewById(R.id.back_button_btn);
        mNeighbourNameOnAvatar = findViewById(R.id.neighbour_name_in_avatar_txt);
        mNeighbourNameOnCardView = findViewById(R.id.neighbour_name_in_cardv_txt);
        mFavoriteButton = findViewById(R.id.favorite_button);
        mNeighbourAddress = findViewById(R.id.neighbour_address_txt);
        mNeighbourPhoneNumber = findViewById(R.id.phone_number_txt);
        mNeighbourFacebook = findViewById(R.id.neighbour_web_txt);
        mNeighbourMoreAbout = findViewById(R.id.about_details_txt);

        //Récupération du neighbour sélectionné
        mNeighbour = getIntent().getParcelableExtra(BUNDLE_NEIGHBOUR_SELECTED);
    }
}
