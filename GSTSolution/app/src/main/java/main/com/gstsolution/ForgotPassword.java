package main.com.gstsolution;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ForgotPassword extends AppCompatActivity {

    EditText etmob;
    Button bnPasswordRequest;
    String mob;
    ProgressDialog pDialog;
    String forgotURL=IPaddress.ip+"forgetpass.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etmob=(EditText)findViewById(R.id.edtforgotpassword);
        bnPasswordRequest=(Button)findViewById(R.id.btnForgotPassword);
        bnPasswordRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendpassword();
            }
        });
    }
    public void sendpassword(){
        mob=etmob.getText().toString().trim();
        RequestParams params =  new RequestParams();
        params.put("contact",mob);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Processing...");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(forgotURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pDialog.dismiss();
                String response = new String(responseBody);
                System.out.print(response);

                try {
                    JSONObject obj = new JSONObject(response);
                    String message = obj.getString("message");

                        Toast.makeText(ForgotPassword.this,message,Toast.LENGTH_LONG).show();
                        Intent i = new Intent(ForgotPassword.this,MainActivity.class);
                        startActivity(i);
                        finish();

                }catch(JSONException e){
                    Toast.makeText(ForgotPassword.this,"Error in JSON",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
                Toast.makeText(ForgotPassword.this,"Connectivity failed with server! Try again.",Toast.LENGTH_LONG).show();
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
            Intent i = new Intent(ForgotPassword.this, Feedback.class);
            startActivity(i);
        }

        if (id == R.id.action_setting2) {
            Intent i = new Intent(ForgotPassword.this, Aboutus.class);
            startActivity(i);
        }

        if (id == R.id.action_setting3) {
            Intent i = new Intent(ForgotPassword.this, HelpServices.class);
            startActivity(i);
        }

        if (id == R.id.action_setting4) {
            Intent i = new Intent(ForgotPassword.this, MainActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
}
