/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package effe.in.ac.iiita.effe2k15.utils.GCM;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

//import effe.in.ac.iiita.effe2k15.utils.DisplayNotification;

public class MyGcmListenerService extends GcmListenerService
{

    public static final String TAG = "MyGcmListenerService";
    public static final String SERVICE_TAG = "com.google.android.c2dm.intent.RECEIVE";


    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String event_id = data.getString("event_id");
        String event_name = data.getString("event_name");
        String event_start_time = data.getString("event_start_time");
        String duration = data.getString("duration");

        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + event_name);

        /**
         * Production applications would usually process the event_name here.
         * Eg: - Syncing with server.
         *     - Store event_name in local database.
         *     - Update UI.
         */

        /**
         * In some cases it may be useful to show a notification indicating to the user
         * that a event_name was received.
         */
        sendNotification(event_id,event_name,event_start_time,duration);
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received GCM message.
     * @param event_id
     * @param event_name
     * @param event_start_time
     * @param duration
     */
    private void sendNotification(String event_id,String event_name,String event_start_time, String duration) {
       // new DisplayNotification(this).makeGCMNotification(event_id,event_name,event_start_time,duration, Integer.parseInt(event_id));
    }

}
