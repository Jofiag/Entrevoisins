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

    public ArrayList<Neighbour> getFavoriteNeighbour()
    {
        ArrayList<Neighbour> favoriteNeighbours = new ArrayList<>();

        for (Neighbour n : neighbours)
        {
            if (n.getIsFavorite())
                favoriteNeighbours.add(n);
        }

        return favoriteNeighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour, ArrayList<Neighbour> list) {
        list.remove(neighbour);
    }

    @Override
    public void toggleFavorite(Neighbour neighbour) {
        neighbours.get(neighbours.indexOf(neighbour)).setIsFavorite(!neighbour.getIsFavorite());

        /*if (neighbours.get(neighbours.indexOf(neighbour)).getIsFavorite()==false)
            neighbours.get(neighbours.indexOf(neighbour)).setIsFavorite(true);
        else
            neighbours.get(neighbours.indexOf(neighbour)).setIsFavorite(false);
         */
    }
}
