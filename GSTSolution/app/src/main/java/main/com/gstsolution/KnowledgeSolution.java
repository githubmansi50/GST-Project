package main.com.gstsolution;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class KnowledgeSolution extends AppCompatActivity {

    RecyclerView listview;
    List<ArticleData> list;
    String getknowledgeURL = IPaddress.ip+"knowledge.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_solution);

        listview = (RecyclerView) findViewById(R.id.listKnowledge);
        listview.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listview.setLayoutManager(llm);

        getKnowledge();
    }

    public void getKnowledge(){
        RequestParams params = new RequestParams();
        params.put("id",1);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getknowledgeURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = new String(responseBody);
                list = new ArrayList<ArticleData>();
                try{
                    JSONObject o = new JSONObject(res);
                    if(o.getString("success").equals("200")){
                        JSONArray a = o.getJSONArray("result");
                        for(int i=0;i<a.length();i++){
                            JSONObject obj = a.getJSONObject(i);
                            ArticleData ad = new ArticleData();
                            ad.image = obj.getString("image");
                            ad.heading = obj.getString("heading");
                            ad.body = obj.getString("body");
                            ad.caption = obj.getString("caption");
                            ad.id = obj.getString("id");
                            list.add(ad);
                        }
                        UserData.type = "2";
                        ArticleAdapter a1 = new ArticleAdapter(list);
                        listview.setAdapter(a1);
                    }
                }catch(Exception e){
                    Toast.makeText(KnowledgeSolution.this, res, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(KnowledgeSolution.this, "Connectivity failed!", Toast.LENGTH_SHORT).show();
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
            Intent i = new Intent(KnowledgeSolution.this, Feedback.class);
            startActivity(i);
        }

        if (id == R.id.action_setting2) {
            Intent i = new Intent(KnowledgeSolution.this, Aboutus.class);
            startActivity(i);
        }

        if (id == R.id.action_setting3) {
            Intent i = new Intent(KnowledgeSolution.this, HelpServices.class);
            startActivity(i);
        }

        if (id == R.id.action_setting4) {
            Intent i = new Intent(KnowledgeSolution.this, MainActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
}
