package effe.in.ac.iiita.effe2k15.landingpage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import effe.in.ac.iiita.effe2k15.R;

public class FragmentTwo extends Fragment
{
    TextView text_view;
    Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub


        return inflater.inflate(
                R.layout.fragment_two, container, false);

    }


}
