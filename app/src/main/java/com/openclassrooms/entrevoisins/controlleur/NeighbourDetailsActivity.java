package com.openclassrooms.entrevoisins.controlleur;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

public class NeighbourDetailsActivity extends AppCompatActivity
{
    private ImageView mImageView;
    private Neighbour mNeighbour;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_details);
    }
}
