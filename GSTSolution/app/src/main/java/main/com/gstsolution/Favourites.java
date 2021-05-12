package main.com.gstsolution;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Favourites extends AppCompatActivity {

    ListView list;
    ArrayList arrayList;
    String getbookmarksURL = IPaddress.ip+"bookmarks.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        list= (ListView) findViewById(R.id.listBookMarks);
        arrayList=new ArrayList();

        getBookmarks();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tm = parent.getItemAtPosition(position).toString();
                String [] rs = tm.split("-");
                Intent i = new Intent(Favourites.this,ViewBookmark.class);
                i.putExtra("id",rs[1]);
                i.putExtra("name",rs[0]);
                startActivity(i);
                finish();
            }
        });
    }

    public void getBookmarks(){
        RequestParams params=new RequestParams();
        params.put("uid",UserData.id);
        //  params.put("dept",dept);
        AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.get(getbookmarksURL,params ,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response=new String(responseBody);
                System.out.println(response);
                try {
                    JSONObject object=new JSONObject(response);
                    System.out.println(object);
                    JSONArray array=object.getJSONArray("result");
                    for(int i=0;i<array.length();i++){
                        JSONObject object1=array.getJSONObject(i);
                        String name=object1.getString("name");
                        String id=object1.getString("id");

                        arrayList.add(name+"-"+id);
                    }
                    ArrayAdapter arrayAdapter=new ArrayAdapter(Favourites.this,android.R.layout.simple_expandable_list_item_1,arrayList);
                    list.setAdapter(arrayAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(Favourites.this, "Connectivity failed! Please try again.", Toast.LENGTH_SHORT).show();
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
            Intent i = new Intent(Favourites.this, Feedback.class);
            startActivity(i);
        }

        if (id == R.id.action_setting2) {
            Intent i = new Intent(Favourites.this, Aboutus.class);
            startActivity(i);
        }

        if (id == R.id.action_setting3) {
            Intent i = new Intent(Favourites.this, HelpServices.class);
            startActivity(i);
        }

        if (id == R.id.action_setting4) {
            Intent i = new Intent(Favourites.this, MainActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
}
