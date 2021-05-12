package main.com.gstsolution.firebase;

import android.location.Geocoder;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.util.Locale;

import main.com.gstsolution.DiscussionForum;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";
    private static TextToSpeech tts;
    Geocoder geocoder;
    String message,lat,lon,address,accflag,title;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        System.out.print(remoteMessage);

        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });

        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                JSONObject data = json.getJSONObject("data");

                /*accflag = data.getString("accflag");
                //parsing json data
                JSONArray a = data.getJSONArray("tnotice");
                if(!accflag.equals("1")) {
                    //UserData.tnotice.clear();
                    for (int i = 0; i < a.length(); i++) {
                        //UserData.tnotice.add(a.get(i));
                    }
                }
                lat = data.getString("lat");
                lon = data.getString("lon");*/

                title = data.getString("title");

                message = data.getString("message");
                DiscussionForum.updateMessage(message);

               /* geocoder = new Geocoder(MyFirebaseMessagingService.this, Locale.getDefault());

                List<Address> addresses = new ArrayList<>();
                try {
                    addresses = geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lon), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                } catch (IOException e) {
                    e.printStackTrace();
                }

                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();

                    if (accflag.equals("0")) {
                        message = "Traffic located at " + address;
                    } else if (accflag.equals("1")) {
                        message = "Accident happened near area " + address;
                    }

                    *//*IncidentData.accflag = accflag;
                    IncidentData.address = address;
                    IncidentData.lat = lat;
                    IncidentData.lon = lon;
                    IncidentData.message = message;
                    UserData.t.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);*//*
                    sendPushNotification(json);*/

            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
    }

    private void sendPushNotification(JSONObject json) {
        //optionally we can display the json into log
        Log.e(TAG, "Notification JSON " + json.toString());
        try {
            //getting the json data

            String imageUrl = "null";




            //creating MyNotificationManager object
            MyNotificationManager mNotificationManager = new MyNotificationManager(getApplicationContext());

            //creating an intent for the notification
            //Intent intent = new Intent(getApplicationContext(), ShowNotifications.class);

            //if there is no image
            if(imageUrl.equals("null")){
                //displaying small notification
                //mNotificationManager.showSmallNotification(title, message, intent);
            }else{
                //if there is an image
                //displaying a big notification
                //mNotificationManager.showBigNotification(title, message, imageUrl, intent);
            }
        } catch (Exception e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        }
    }
}