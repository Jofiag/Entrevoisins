package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    ArrayList<Neighbour> getNeighbours();

    /**
     * Get favorite neighbour
     * @return
     */
    ArrayList<Neighbour> getFavoriteNeighbour();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour, ArrayList<Neighbour> list);

    /**
     * Toggle the favorite value of a neighbour
     *
     * @param neighbour
     */
    void toggleFavorite(Neighbour neighbour);
}