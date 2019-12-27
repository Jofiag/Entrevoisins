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
     * Get all Favoris Neighbours
     */
    List<Neighbour> getFavorisNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour neighbour to delete
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * change favoris statut
     * @param neighbour neighbour to add or remove from the favorite liste
     */
    void changeFavoris (Neighbour neighbour);

    void updateFavoriteNeighbours(Neighbour neighbour);
}
