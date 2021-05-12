package main.com.gstsolution;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class HelpServices extends AppCompatActivity {

    LinearLayout ll;
    String gethelpURL = IPaddress.ip+"gethelp.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_services);

        ll = (LinearLayout) findViewById(R.id.layoutHelp);

        getHelp();
    }

    public void getHelp(){
        RequestParams params = new RequestParams();
        params.put("id",1);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(gethelpURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = new String(responseBody);
                try{
                    JSONObject o = new JSONObject(res);
                    if(o.getString("success").equals("200")){
                        JSONArray a = o.getJSONArray("result");
                        for(int i =0;i<a.length();i++){
                            //JSONObject obj = a.getJSONObject(i);
                            TextView txt = new TextView(HelpServices.this);
                            txt.setText(a.getString(i));
                            txt.setGravity(Gravity.CENTER);
                            ll.addView(txt);
                        }
                    }else{
                        Toast.makeText(HelpServices.this, res, Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){
                    Toast.makeText(HelpServices.this, res, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(HelpServices.this, "Connectivity failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_setting1) {
            Intent i = new Intent(HelpServices.this, Feedback.class);
            startActivity(i);
        }

        if (id == R.id.action_setting2) {
            Intent i = new Intent(HelpServices.this, Aboutus.class);
            startActivity(i);
        }

        if (id == R.id.action_setting3) {
            Intent i = new Intent(HelpServices.this, HelpServices.class);
            startActivity(i);
        }

        if (id == R.id.action_setting4) {
            Intent i = new Intent(HelpServices.this, MainActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
}
