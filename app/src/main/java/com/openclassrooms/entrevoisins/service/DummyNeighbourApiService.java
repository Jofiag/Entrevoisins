package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }


    /**
     * delocalisation de ma methode de creation de liste de favoris
     * @return
     */
    @Override
    public List<Neighbour> getFavoriteNeighbours() {

        List<Neighbour> favoriteNeighbours = new ArrayList<>();

        for (Neighbour list : neighbours) {
            if (list.isFavoris()) {
                favoriteNeighbours.add(list);
            }
        }

        return favoriteNeighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * methode de services pour changer le statut favoris
     * @param neighbour
     */
    @Override
    public void changeFavorite(Neighbour neighbour) {
        neighbours.get(neighbours.indexOf(neighbour)).setFavoris(!neighbour.isFavoris());
    }
}
