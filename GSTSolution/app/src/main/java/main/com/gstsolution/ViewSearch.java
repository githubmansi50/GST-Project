package main.com.gstsolution;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ViewSearch extends AppCompatActivity {

    ListView list;
    ArrayList arrayList;
    TextView heading;
    int result=0;
    String getsearchURL = IPaddress.ip+"search.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_search);

        list= (ListView) findViewById(R.id.listViewSearch);
        heading = (TextView) findViewById(R.id.textViewSearchHeading);
        heading.setText("Search result for '"+UserData.searchword+"':");
        arrayList=new ArrayList();

        getSearch();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tm = parent.getItemAtPosition(position).toString();
                String [] rs = tm.split("-");
                Intent i = new Intent(ViewSearch.this,ViewBookmark.class);
                i.putExtra("id",rs[1]);
                i.putExtra("name",rs[0]);
                UserData.type = rs[2];
                startActivity(i);
            }
        });
    }

    public void getSearch(){
        RequestParams params = new RequestParams();
        params.put("searchword",UserData.searchword);

        final ProgressDialog pDialog = new ProgressDialog(ViewSearch.this);
        pDialog.setMessage("Searching result for "+UserData.searchword);
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getsearchURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pDialog.dismiss();
                String res = new String(responseBody);
                try{
                    JSONObject o = new JSONObject(res);
                    if(o.getString("success").equals("200")) {
                        result=1;
                        JSONArray a = o.getJSONArray("result");
                        for(int i =0;i<a.length();i++) {
                            JSONObject obj = a.getJSONObject(i);
                            String name = obj.getString("name");
                            UserData.searchid = obj.getString("id");
                            UserData.type = obj.getString("type");

                            arrayList.add(name+"-"+UserData.searchid+"-"+UserData.type);
                            ArrayAdapter a1 = new ArrayAdapter(ViewSearch.this, android.R.layout.simple_spinner_item, arrayList);
                            list.setAdapter(a1);
                        }
                    }else{
                        result=0;
                        Toast.makeText(ViewSearch.this, res, Toast.LENGTH_SHORT).show();
                        arrayList.add("No result found!");
                        ArrayAdapter a1 = new ArrayAdapter(ViewSearch.this, android.R.layout.simple_spinner_item, arrayList);
                        list.setAdapter(a1);
                    }
                }catch(Exception e){
                    Toast.makeText(ViewSearch.this, res, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
                Toast.makeText(ViewSearch.this, "Connectivity failed!", Toast.LENGTH_SHORT).show();
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
            Intent i = new Intent(ViewSearch.this, Feedback.class);
            startActivity(i);
        }

        if (id == R.id.action_setting2) {
            Intent i = new Intent(ViewSearch.this, Aboutus.class);
            startActivity(i);
        }

        if (id == R.id.action_setting3) {
            Intent i = new Intent(ViewSearch.this, HelpServices.class);
            startActivity(i);
        }

        if (id == R.id.action_setting4) {
            Intent i = new Intent(ViewSearch.this, MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
