package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.controlleur.NeighbourDetailsActivity;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.StartActivityEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter.BUNDLE_NEIGHBOUR_SELECTED;


public class NeighbourListFragment extends Fragment {

    @BindView(R.id.list_neighbours) RecyclerView mRecyclerView;

    private NeighbourApiService mApiService;
    private ArrayList<Neighbour> mNeighbours = new ArrayList<>();
    private ArrayList<Neighbour> mFavoriteNeighbours = new ArrayList<>();

    public static final int NEIGHBOUR_DETAILS_ACTIVITY_CODE = 1;
    private MyNeighbourRecyclerViewAdapter mAdapter;

    /**
     * Create and return a new instance
     * @return @{@link NeighbourListFragment}
     * @param neighbours
     */
    public static NeighbourListFragment newInstance(ArrayList<Neighbour> neighbours) {
        NeighbourListFragment fragment = new NeighbourListFragment();

        Bundle arg = new Bundle();
        arg.putSerializable( "list_of_neighbours", neighbours);
        fragment.setArguments(arg);

        return fragment;
    }

    public void setNeighbours(ArrayList<Neighbour> neighbours)
    {
        mNeighbours.clear();
        mNeighbours.addAll(neighbours);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        ButterKnife.bind(this, view);
        initFavoriteList();
        ArrayList<Neighbour> list = (ArrayList<Neighbour>) getArguments().getSerializable("list_of_neighbours");
        mApiService = DI.getNeighbourApiService();
        mRecyclerView.findViewById(R.id.list_neighbours);
        mAdapter = new MyNeighbourRecyclerViewAdapter(this, list);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initFavoriteList() {
        //mAdapter = new MyNeighbourRecyclerViewAdapter(mNeighbours);
        //mRecyclerView.setAdapter(mAdapter);
        for (Neighbour n : mNeighbours) {
            if (n.getIsFavorite())
                mFavoriteNeighbours.add(n);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param neighbour
     */
    public void onDeleteNeighbour(Neighbour neighbour) {
        mApiService.deleteNeighbour(neighbour);
        mNeighbours.remove(neighbour);
        mAdapter.notifyDataSetChanged();
    }


    public void startDetailsActivity(Neighbour neighbour)
    {
        Intent neighbourDetailsActivity = new Intent(getActivity(), NeighbourDetailsActivity.class);
        neighbourDetailsActivity.putExtra(BUNDLE_NEIGHBOUR_SELECTED, neighbour);
        getActivity().startActivityForResult(neighbourDetailsActivity, NEIGHBOUR_DETAILS_ACTIVITY_CODE);
    }
}
