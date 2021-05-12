package main.com.gstsolution;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ImportantLinks extends AppCompatActivity {

    RecyclerView listview;
    List<LinkData> list;
    String getlinksURL = IPaddress.ip+"implinks.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_links);

        listview = (RecyclerView) findViewById(R.id.listImpLinks);
        listview.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(ImportantLinks.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listview.setLayoutManager(llm);

        getLinks();
    }

    public void getLinks(){
        RequestParams params = new RequestParams();
        params.put("id",1);

        final ProgressDialog pDialog = new ProgressDialog(ImportantLinks.this);
        pDialog.setMessage("getting links...");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getlinksURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pDialog.dismiss();
                list = new ArrayList<LinkData>();
                String res = new String(responseBody);
                try{
                    JSONObject o = new JSONObject(res);
                    if(o.getString("success").equals("200")){
                        JSONArray a = o.getJSONArray("result");
                        for(int i=0;i<a.length();i++) {
                            JSONObject obj = a.getJSONObject(i);
                            LinkData ld = new LinkData();
                            ld.id = obj.getString("id");
                            ld.name = obj.getString("name");
                            ld.link = obj.getString("link");
                            list.add(ld);
                        }
                        LinkAdapter a1 = new LinkAdapter(list);
                        listview.setAdapter(a1);
                    }
                }catch(Exception e){
                    Toast.makeText(ImportantLinks.this, res, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
                Toast.makeText(ImportantLinks.this, "Connectivity failed!", Toast.LENGTH_SHORT).show();
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
            Intent i = new Intent(ImportantLinks.this, Feedback.class);
            startActivity(i);
        }

        if (id == R.id.action_setting2) {
            Intent i = new Intent(ImportantLinks.this, Aboutus.class);
            startActivity(i);
        }

        if (id == R.id.action_setting3) {
            Intent i = new Intent(ImportantLinks.this, HelpServices.class);
            startActivity(i);
        }

        if (id == R.id.action_setting4) {
            Intent i = new Intent(ImportantLinks.this, MainActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
}
