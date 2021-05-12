package main.com.gstsolution;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import cz.msebera.android.httpclient.Header;

public class ViewBookmark extends AppCompatActivity {

    TextView heading,body;
    ImageView delete,image;
    String name="",id="",img="";
    String getdetailsURL = IPaddress.ip+"getbookmark.php";
    String deleteURL = IPaddress.ip+"delete.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookmark);

        name = getIntent().getExtras().getString("name");
        id = getIntent().getExtras().getString("id");

        heading = (TextView) findViewById(R.id.viewBookmarkHeading);
        body = (TextView) findViewById(R.id.viewBookmarkBody);
        delete = (ImageView) findViewById(R.id.viewBookmarkDelete);
        image = (ImageView) findViewById(R.id.viewBookmarkImage);

        if(UserData.searchflag.equals("1")){
            delete.setVisibility(View.INVISIBLE);
        }

        getDetails();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(v.getContext());
                ab.setTitle("Delete bookmark");
                ab.setMessage("Do you want to delete this page from your favourites?");

                ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteBookmark();
                    }
                });

                ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog a = ab.create();
                a.show();
            }
        });
    }

    public void getDetails(){
        RequestParams params = new RequestParams();
        params.put("name",name);
        params.put("id",id);
        params.put("uid",UserData.id);
        params.put("type",UserData.type);
        params.put("flag",UserData.searchflag);

        final ProgressDialog pDialog = new ProgressDialog(ViewBookmark.this);
        pDialog.setMessage("processing...");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getdetailsURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pDialog.dismiss();
                String res = new String(responseBody);
                try{
                    JSONObject o = new JSONObject(res);
                    if(o.getString("success").equals("200")){
                        heading.setText(o.getString("heading"));
                        body.setText(o.getString("body"));
                        img = o.getString("image");
                        if(!img.equals("no image")){
                            new GetImage().execute(IPaddress.ip+img);
                        }
                    }
                }catch(Exception e){
                    Toast.makeText(ViewBookmark.this, res, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
                Toast.makeText(ViewBookmark.this, "Connectivity failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class GetImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap b) {
            super.onPostExecute(b);
            image.setImageBitmap(b);

        }

        @Override
        protected Bitmap doInBackground(String... params) {

                String id = params[0];
                //p = Integer.parseInt(temp[1]);
//                String add = "http://10.0.3.2/Smart_Institute/images/image1.jpg";
                URL url = null;
                Bitmap image1 = null;
                try {
                    url = new URL(id);
                    image1 = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    Log.d("Result", String.valueOf(image1));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            return image1;
        }

    }

    public void deleteBookmark(){
        RequestParams params = new RequestParams();
        params.put("name",name);
        params.put("id",id);
        params.put("uid",UserData.id);

        final ProgressDialog pDialog = new ProgressDialog(ViewBookmark.this);
        pDialog.setMessage("processing...");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(deleteURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pDialog.dismiss();
                String res = new String(responseBody);
                if(res.equals("200")){
                    Intent i = new Intent(ViewBookmark.this,Favourites.class);
                    //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }else{
                    Toast.makeText(ViewBookmark.this, "Error in deleting bookmark! Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
                Toast.makeText(ViewBookmark.this, "Connectivity failed!", Toast.LENGTH_SHORT).show();
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
            Intent i = new Intent(ViewBookmark.this, Feedback.class);
            startActivity(i);
        }

        if (id == R.id.action_setting2) {
            Intent i = new Intent(ViewBookmark.this, Aboutus.class);
            startActivity(i);
        }

        if (id == R.id.action_setting3) {
            Intent i = new Intent(ViewBookmark.this, HelpServices.class);
            startActivity(i);
        }

        if (id == R.id.action_setting4) {
            Intent i = new Intent(ViewBookmark.this, MainActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
}
