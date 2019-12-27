package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class UpdateFavoriteListEvent
{
    public Neighbour neighbour;

    public UpdateFavoriteListEvent(Neighbour neighbour)
    {
        this.neighbour = neighbour;
    }
}
