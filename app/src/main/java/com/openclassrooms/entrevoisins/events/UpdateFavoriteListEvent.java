package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class UpdateFavoriteListEvent
{
    private  Neighbour mNeighbour;

    public UpdateFavoriteListEvent(Neighbour neighbour)
    {
        mNeighbour = neighbour;
    }
}
