package main.com.gstsolution.firebase;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import main.com.gstsolution.IPaddress;
import main.com.gstsolution.UserData;

/**
 * Created by Ganesh on 7/5/2017.
 */

public class SendID {

    SyncHttpClient mSyncClient;
    String tokenrefreshURL = IPaddress.ip + "getid.php";
    Context mContext;

    public void sendtoken(String token) {
        RequestParams params = new RequestParams();
        params.put("id", UserData.id);
        params.put("fid", token);

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(IPaddress.ip + "getid.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = new String(responseBody);
                if (res.equals("200")) {
                    System.out.print("PHP called");
                } else {
                    System.out.print(res);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.print("PHP error");
            }
        });
    }


    public void newMethod(String token) {

        mSyncClient = new SyncHttpClient();
        RequestParams params = new RequestParams();
        try {
            params.put("mobile", UserData.mobile);
            params.put("fid", token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mSyncClient.post(tokenrefreshURL, params, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                // you can do something here before request starts
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.getString("success").equals("200")) {
                        System.out.print("PHP called");
                    }
                }catch(Exception e){

                }
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                // handle failure here
            }

        });

    }
}
