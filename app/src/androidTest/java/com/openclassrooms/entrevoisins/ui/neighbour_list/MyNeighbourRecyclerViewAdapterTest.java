package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.test.rule.ActivityTestRule;

import com.openclassrooms.entrevoisins.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class MyNeighbourRecyclerViewAdapterTest {

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mListNeighbourActivityActivityTestRule =
            new ActivityTestRule<>(ListNeighbourActivity.class);


    /**
     * test vérifiant que lorsqu’on clique sur un élément de la liste, l’écran de
     * détails est bien lancé ;
     */
    @Test
    public void onBindViewHolder()
    {
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.txtProfil)).check(matches(isDisplayed()));
    }

    /**
     * test vérifiant qu’au démarrage de ce nouvel écran, le TextView indiquant
     * le nom de l’utilisateur en question est bien rempli
     */
    @Test
    public void profilIsPopulated()
    {
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.txtProfil)).check(matches(withText("Caroline")));
    }

    /**
     * test vérifiant que l’onglet Favoris n’affiche que les voisins marqués comme
     * favoris
     */
    @Test
    public void FavorisListIsPopulated()
    {
        //click sur un profil
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(actionOnItemAtPosition(0, click()));
        // click sur le bouton favoris dans la fiche profil
        onView(withId(R.id.favorisButton)).perform(click());
        // retour en arriere
        pressBack();
        // bascule sur l'onglet favoris
        swipeLeft();
        // click sur le profil depuis l'onglet favoris
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(actionOnItemAtPosition(0, click()));
        // on verifie que le nom du profil favoris est bien le meme que celui choisi dans la liste des voisins
        onView(withId(R.id.txtProfil)).check(matches(withText("Caroline")));
    }
}