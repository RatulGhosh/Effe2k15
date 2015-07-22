package effe.in.ac.iiita.effe2k15.landingpage;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.facebook.FacebookSdk;

import effe.in.ac.iiita.effe2k15.R;
import effe.in.ac.iiita.effe2k15.utils.CirclePageIndicator;

/**
 * Launcher Activity
 */

public class MainActivity extends FragmentActivity
{
    OnlineHelper onlineHelper;
    ViewPager pager;
    CirclePageIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        /**
         * OnlineHelper to check if user has already login.
         * If yes then finish this activity and start another
         * Else Continue to current activity
         */
        onlineHelper = new OnlineHelper(this);
        onlineHelper.loginCheck(); // don't return value as Asynch task dont return

        /**
         * ViewPager having three fragments with circle page indicator
         * FragmentOne, FragmentTwo and FragmentThree are our three fragment.
         * FragmentThree have Facebook Login Button
         */
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }
}
