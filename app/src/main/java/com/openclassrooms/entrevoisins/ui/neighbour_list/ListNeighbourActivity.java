package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.PendingIntent.getActivity;
import static com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourListFragment.NEIGHBOUR_DETAILS_ACTIVITY_CODE;

public class ListNeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tab)
    TabLayout tabLayout;

    private ArrayList<Neighbour> mNeighbours = new ArrayList<>();
    private ArrayList<Neighbour> mFavoriteNeighbours = new ArrayList<>();
    private NeighbourApiService mApiService;
    private Neighbour mNeighbourUpdated;
    private ArrayAdapter<Neighbour> adapter;
    private NeighbourListFragment mGeneralFragment;
    private NeighbourListFragment mFavoriteFragment;

    ListNeighbourPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);

        ButterKnife.bind(this);

        mApiService = DI.getNeighbourApiService();
        mNeighbours = mApiService.getNeighbours();
        mFavoriteNeighbours = mApiService.getFavoriteNeighbour();
        mPagerAdapter = new ListNeighbourPagerAdapter(new FragmentManager()
        {
            @NonNull
            @Override
            public FragmentTransaction beginTransaction()
            {
                return null;
            }

            @Override
            public boolean executePendingTransactions()
            {
                return false;
            }

            @Nullable
            @Override
            public Fragment findFragmentById(int i)
            {
                return null;
            }

            @Nullable
            @Override
            public Fragment findFragmentByTag(@Nullable String s)
            {
                return null;
            }

            @Override
            public void popBackStack()
            {

            }

            @Override
            public boolean popBackStackImmediate()
            {
                return false;
            }

            @Override
            public void popBackStack(@Nullable String s, int i)
            {

            }

            @Override
            public boolean popBackStackImmediate(@Nullable String s, int i)
            {
                return false;
            }

            @Override
            public void popBackStack(int i, int i1)
            {

            }

            @Override
            public boolean popBackStackImmediate(int i, int i1)
            {
                return false;
            }

            @Override
            public int getBackStackEntryCount()
            {
                return 0;
            }

            @NonNull
            @Override
            public BackStackEntry getBackStackEntryAt(int i)
            {
                return null;
            }

            @Override
            public void addOnBackStackChangedListener(@NonNull OnBackStackChangedListener onBackStackChangedListener)
            {

            }

            @Override
            public void removeOnBackStackChangedListener(@NonNull OnBackStackChangedListener onBackStackChangedListener)
            {

            }

            @Override
            public void putFragment(@NonNull Bundle bundle, @NonNull String s, @NonNull Fragment fragment)
            {

            }

            @Nullable
            @Override
            public Fragment getFragment(@NonNull Bundle bundle, @NonNull String s)
            {
                return null;
            }

            @NonNull
            @Override
            public List<Fragment> getFragments()
            {
                return null;
            }

            @Nullable
            @Override
            public Fragment.SavedState saveFragmentInstanceState(Fragment fragment)
            {
                return null;
            }

            @Override
            public boolean isDestroyed()
            {
                return false;
            }

            @Override
            public void registerFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean b)
            {

            }

            @Override
            public void unregisterFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks fragmentLifecycleCallbacks)
            {

            }

            @Nullable
            @Override
            public Fragment getPrimaryNavigationFragment()
            {
                return null;
            }

            @Override
            public void dump(String s, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strings)
            {

            }

            @Override
            public boolean isStateSaved()
            {
                return false;
            }
        });
        viewPager.setAdapter(mPagerAdapter);

        setupViewPager();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int i, float v, int i1)
            {

            }

            @Override
            public void onPageSelected(int i)
            {
                NeighbourListFragment fragmentN = (NeighbourListFragment) mPagerAdapter.getItem(0);
                NeighbourListFragment fragmentF = (NeighbourListFragment) mPagerAdapter.getItem(1);

                fragmentN.updateData();
                fragmentF.updateData();

            }

            @Override
            public void onPageScrollStateChanged(int i)
            {

            }
        });

    }
    private void updateData()
    {
        mPagerAdapter.notifyDataSetChanged();
    }


    private void setupViewPager()
    {
        //On instancie les deux fragments
        mGeneralFragment = NeighbourListFragment.newInstance(mNeighbours);
        mFavoriteFragment = NeighbourListFragment.newInstance(mFavoriteNeighbours);

        //On ajoutes ces deux fragments à la page
        ListNeighbourPagerAdapter mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(mGeneralFragment, "MY NEIGHBOURS");
        mPagerAdapter.addFragment(mFavoriteFragment, "FAVORITES");
        viewPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        //Récupération de neighbour à jour (favori ou non)
        if (NEIGHBOUR_DETAILS_ACTIVITY_CODE == requestCode && RESULT_OK == resultCode)
        {
            if (getIntent() != null)
                mNeighbourUpdated = (Neighbour) getIntent().getSerializableExtra("neighbourUpdated");

            //initialisation de la liste d'utilisateurs favoris
            updateFavoriteList(mNeighbourUpdated);
            mFavoriteFragment.setNeighbours(mFavoriteNeighbours);
        }
    }

    private void updateFavoriteList(Neighbour neighbour)
    {
        //initFavoriteList();
        if (neighbour.getIsFavorite() && !mFavoriteNeighbours.contains(neighbour))
            mFavoriteNeighbours.add(neighbour);
        else if(!mNeighbourUpdated.getIsFavorite() && mFavoriteNeighbours.contains(neighbour))
            mFavoriteNeighbours.remove(neighbour);
    }

    private void initFavoriteList()
    {
        for (Neighbour n : mNeighbours)
        {
            if (n.getIsFavorite())
                mFavoriteNeighbours.add(n);
            else if (mFavoriteNeighbours.contains(n) && !n.getIsFavorite())
                mFavoriteNeighbours.remove(n);
        }
    }

    public void addOnPageListener()
    {

    }
}
