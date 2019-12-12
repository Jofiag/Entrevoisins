package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DeleteNeighbourEvent {

    /**
     * Neighbour to delete
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public DeleteNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
