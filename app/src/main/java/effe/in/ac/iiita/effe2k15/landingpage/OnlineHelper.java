package effe.in.ac.iiita.effe2k15.landingpage;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.facebook.Profile;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import effe.in.ac.iiita.effe2k15.utils.Connectivity;
import effe.in.ac.iiita.effe2k15.utils.Database.DatabaseHelper;
import effe.in.ac.iiita.effe2k15.utils.JSON.JSONParser;
import effe.in.ac.iiita.effe2k15.utils.Message;

/**
 * Online Helper for LoginCheck and to post user info
 */
public class OnlineHelper
{

    private ProgressDialog pDialog;
    /**
     * JSONParser is responsible for http request
     */
    JSONParser jParser;
    Context context;
    /**
     * Class to check network state
     */
    Connectivity connectivity;

    private static String url_create_user = "http://MYIPADDRESSforServer/effe/create_user.php";
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String PROFILE_PIC_URL = "profile_pic_url";
    String user_id;
    String user_name;
    String first_name;
    String last_name;
    String email;
    String profile_pic_url;





    OnlineHelper(Context context)
    {
        this.context = context;
        jParser = new JSONParser();
        connectivity = new Connectivity(context);
    }

    public void createUser(String id, String user_name, String first_name, String last_name, String email, String profile_pic_url)
    {
        this.user_id = id;
        // to store user id for further use
        context.getSharedPreferences("prefs", context.MODE_PRIVATE).edit().putString("user_id", id).commit();
        this.user_name = user_name;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.profile_pic_url = profile_pic_url;

        if (connectivity.isNetworkAvailable())
        {

            CreateUser createUser = new CreateUser();
            createUser.execute();
        } else
        {
            Message.message(context, "Network error");
        }
    }

    public void loginCheck()
    {
        if (connectivity.isNetworkAvailable())
        {
            LoginCheck loginCheck = new LoginCheck();
            loginCheck.execute();
        } else
        {
            Message.message(context, "Network error");
        }

    }


    class CreateUser extends AsyncTask<String, String, Boolean>
    {
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Gathering up required information....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... param)
        {
            // TODO Auto-generated method stub

            List<NameValuePair> params1 = new ArrayList<>();
            JSONObject json;

            params1.add(new BasicNameValuePair(USER_ID, user_id));
            params1.add(new BasicNameValuePair(USER_NAME, user_name));
            params1.add(new BasicNameValuePair(FIRST_NAME, first_name));
            params1.add(new BasicNameValuePair(LAST_NAME, last_name));
            params1.add(new BasicNameValuePair(EMAIL, email));
            params1.add(new BasicNameValuePair(PROFILE_PIC_URL, profile_pic_url));
            json = jParser.makeHttpRequest(url_create_user, "POST", params1);

            // TODO Auto-generated catch block
            if (json != null)
            {

            } else
            {
                return false;
            }
            return true;
        }

        /**
         * If user created start next Activity
         *
         * @param result
         */

        protected void onPostExecute(Boolean result)
        {

            if (result == false)
                Message.message(context, "Network Error");

            pDialog.dismiss();
            // to start new activity after user has been created
           // Intent intent = new Intent(context, effe.in.ac.iiita.effe2k15.MainActivity.class);
           // context.startActivity(intent);
        }
    }


    /**
     * If user is login then start next activity else continue using current activity
     */
    private class LoginCheck extends AsyncTask<String, Void, Boolean>
    {

        @Override
        protected Boolean doInBackground(String... params)
        {
            // if database is not created yet. May cause first time error if removed
            new DatabaseHelper(context);

            if (Profile.getCurrentProfile().getCurrentProfile() != null)
                return true;
            else
                return false;
        }

        @Override
        protected void onPostExecute(Boolean result)
        {
            if (result == true)
            {
                //for navigating to other activity from main activity (logincheck method )of landing page
               // Intent intent = new Intent(context, effe.in.ac.iiita.effe2k15.MainActivity.class);
                //((Activity) context).finish();
                //context.startActivity(intent);
            }
            pDialog.dismiss();
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Please Wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

    }


}


