package com.dansalomon.C24.ViewPager;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dansalomon.C24.Mobile_tabs.Mois;
import com.dansalomon.C24.Mobile_tabs.Semaine;

/**
 * Created by Edwin on 15/02/2015.
 */
public class ViewPagerOfficeHome extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerTransfer is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerTransfer is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerOfficeHome(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            Semaine semaine = new Semaine();
            return semaine;
        }
        else {

            Mois mois = new Mois();
            return mois;
        }

    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}