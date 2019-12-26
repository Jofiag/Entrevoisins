package com.openclassrooms.entrevoisins.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * l'objet implemente parcelable pour me permettre de le transmettre
 * via un intente entre mes activity
 */
public class Neighbour implements Parcelable {

    /** Identifier */
    private Integer id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    /** favoris **/

    private boolean favoris;

    /**
     * Constructor
     * @param id of neighbour
     * @param name of neighbour
     * @param avatarUrl of neighbour
     * @param favoris of neighbour
     */
    public Neighbour(Integer id, String name, String avatarUrl, boolean favoris) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.favoris = favoris;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public String getAvatarUrl() {
        return avatarUrl;
    }


    public boolean isFavoris() {
        return favoris;
    }

    public void setFavoris(boolean favoris) {
        this.favoris = favoris;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    public static final Parcelable.Creator<Neighbour> CREATOR = new Parcelable.Creator<Neighbour>() {
        public Neighbour createFromParcel(Parcel in) {
            return new Neighbour(in);
        }

        public Neighbour[] newArray(int size) {
            return new Neighbour[size];
        }
    };

    private Neighbour(Parcel in) {
        id = in.readInt();
        name = in.readString();
        avatarUrl = in.readString();
        favoris = in.readByte() != 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(avatarUrl);
        dest.writeByte((byte) (favoris ? 1 : 0));
    }
}
