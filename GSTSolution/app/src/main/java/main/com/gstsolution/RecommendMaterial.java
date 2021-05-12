package main.com.gstsolution;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class RecommendMaterial extends AppCompatActivity {

    ListView listView;
    List<RecommendData> list = new ArrayList<>();
    String getmaterialURL = IPaddress.ip+"getrecommendation.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_material);
        listView = (ListView) findViewById(R.id.recommendedList);

        callFunction();
    }
    public void callFunction(){
        RequestParams params = new RequestParams();
        params.put("admin","admin");

        final ProgressDialog pDialog = ProgressDialog.show(RecommendMaterial.this,null, "processing...", true, false);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getmaterialURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pDialog.dismiss();
                String res = new String(responseBody);
                try{
                    JSONObject o = new JSONObject(res);
                    if(o.getString("success").equals("200")){
                        list.clear();
                        JSONArray a = o.getJSONArray("result");
                        for(int i=0;i<a.length();i++){
                            JSONObject obj = a.getJSONObject(i);
                            RecommendData rd = new RecommendData();
                            rd.id = obj.getString("id");
                            rd.title = obj.getString("title");
                            rd.attachment = obj.getString("attachment");
                            rd.typ = obj.getInt("type");
                            if(rd.typ==1){
                                rd.type = "Word document";
                            }else if(rd.typ==2){
                                rd.type = "PDF";
                            }else if(rd.typ==3){
                                rd.type = "video";
                            }
                            list.add(rd);
                        }
                        MaterialAdapter a1 = new MaterialAdapter(RecommendMaterial.this, R.layout.rowitem2, list);
                        listView.setAdapter(a1);
                    }
                }catch(Exception e){
                    Toast.makeText(RecommendMaterial.this, res, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
                Toast.makeText(RecommendMaterial.this, "Connectivity failed!", Toast.LENGTH_SHORT).show();
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
            Intent i = new Intent(RecommendMaterial.this, Feedback.class);
            startActivity(i);
        }

        if (id == R.id.action_setting2) {
            Intent i = new Intent(RecommendMaterial.this, Aboutus.class);
            startActivity(i);
        }

        if (id == R.id.action_setting3) {
            Intent i = new Intent(RecommendMaterial.this, HelpServices.class);
            startActivity(i);
        }

        if (id == R.id.action_setting4) {
            Intent i = new Intent(RecommendMaterial.this, MainActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
}
