package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourViewHolder extends RecyclerView.ViewHolder
{
    @BindView(R.id.item_list_name) TextView mTextView;

    public NeighbourViewHolder(@NonNull View itemView)
    {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithNeithbour(Neighbour neighbour)
    {
        mTextView.setText(neighbour.getName());
    }
}
