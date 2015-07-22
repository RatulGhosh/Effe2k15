package effe.in.ac.iiita.effe2k15.utils;

import android.content.Context;
import android.widget.Toast;

public class Message
{

    public static void message(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
