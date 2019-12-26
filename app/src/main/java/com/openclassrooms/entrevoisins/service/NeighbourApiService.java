package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {


    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Get all Favorite Neighbours
     */
    List<Neighbour> getFavoriteNeighbours();

    /**
     * Deletes a neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * change favoris statut
     */
    void changeFavorite(Neighbour neighbour);
}
