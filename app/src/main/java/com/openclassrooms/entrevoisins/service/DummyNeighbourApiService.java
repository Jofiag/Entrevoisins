package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private ArrayList<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Neighbour> getNeighbours() {
        return neighbours;
    }

    public List<Neighbour> getFavorites() {return neighbours;}

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public void toggleFavorite(Neighbour neighbour) {
        if (neighbours.get(neighbours.indexOf(neighbour)).getIsFavorite()==false)
            neighbours.get(neighbours.indexOf(neighbour)).setIsFavorite(true);
        else neighbours.get(neighbours.indexOf(neighbour)).setIsFavorite(false);
    }
}
