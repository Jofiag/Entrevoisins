package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Objects;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourPagerAdapter.FAVORITE_PAGE;


public class NeighbourFragment extends Fragment
{

    private NeighbourApiService mApiService;
    private RecyclerView mRecyclerView;
    private String page;
    private static final String ONGLET = "page";


    /**
     * Create and return a new instance
     *
     * @param page name of the fragment
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(String page)
    {
        NeighbourFragment fragment = new NeighbourFragment();

        Bundle args = new Bundle();
        args.putString(ONGLET, page);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));

        if (getArguments() != null)
            page = getArguments().getString(ONGLET);

        initList();

        return view;
    }

    /**
     * Init the List of neighbours
     * utilisation de la methode de services getFavorisNeigbours
     * pour la generation de liste de favoris a jours
     */
    public void initList()
    {
        List<Neighbour> neighbours = mApiService.getNeighbours();

        List<Neighbour> favorisNeigbours = mApiService.getFavorisNeighbours();

        if (page.equals(FAVORITE_PAGE))
            mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(favorisNeigbours));
        else
            mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(neighbours));
    }

    @Override
    public void onStart()
    {
        super.onStart();
        EventBus.getDefault().register(this);
        initList();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    /**
     * Fired if the user clicks on a delete button
     *
     * @param event deleteEvent
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event)
    {
        mApiService.deleteNeighbour(event.neighbour);
        initList();
    }
}
