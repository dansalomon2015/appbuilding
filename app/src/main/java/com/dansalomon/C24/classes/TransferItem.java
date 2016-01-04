package com.dansalomon.C24.classes;

import android.graphics.drawable.Drawable;

/**
 * Created by Dan Salomon on 20/12/2015.
 */
public class TransferItem {

    private String agence;
    private double cout;
    private Drawable logo;

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public Drawable getLogo() {
        return logo;
    }

    public void setLogo(Drawable logo) {
        this.logo = logo;
    }
}
