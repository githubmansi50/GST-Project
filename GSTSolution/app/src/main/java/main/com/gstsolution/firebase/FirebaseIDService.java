package main.com.gstsolution.firebase;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import main.com.gstsolution.UserData;


public class FirebaseIDService extends FirebaseInstanceIdService {
    private static final String TAG = "FirebaseIDService";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // TODO: Implement this method to send any registration to your app's servers.
        sendRegistrationToServer(refreshedToken);
        System.out.print("Registration Id : "+refreshedToken);
    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
        SendID s = new SendID();
        UserData.fid = token;
        //s.sendtoken(token);
        s.newMethod(token);
        UserData.utoken = token;
        SharedPreferences pref = getSharedPreferences("mytoken",0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("token",token);
        editor.commit();
    }



}
