package effe.in.ac.iiita.effe2k15.landingpage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter
{

    public PagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0)
    {
        // TODO Auto-generated method
        switch (arg0)
        {
            case 0:
                return new FragmentOne();
            case 1:
                return new FragmentTwo();
            case 2:
                return new FragmentThree();
        }

        return null;
    }

    @Override
    public int getCount()
    {
        return 3;
    }

}
