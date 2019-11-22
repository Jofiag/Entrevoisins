package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class StartActivityEvent
{
    /**
     * Activity to start
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public StartActivityEvent (Neighbour neighbour)
    {
        this.neighbour = neighbour;
    }
}
