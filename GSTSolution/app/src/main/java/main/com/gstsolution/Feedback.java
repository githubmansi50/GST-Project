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
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class Feedback extends AppCompatActivity {
    EditText uname,umobile,uemail,umessage;
    String name = " ",mobile = " ",email = " ",message = " ";
    Button submit;
    private String feedbackURL = IPaddress.ip+"feedback.php";

    static ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        pDialog = new ProgressDialog(Feedback.this);

        uname = (EditText) findViewById(R.id.feedbackname);
        umobile = (EditText) findViewById(R.id.feedbackno);
        uemail = (EditText) findViewById(R.id.feedbackemail);
        umessage = (EditText) findViewById(R.id.feedbackmsg);
        submit = (Button) findViewById(R.id.feedbacksubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 name = uname.getText().toString().trim();
                 mobile = umobile.getText().toString().trim();
                 email = uemail.getText().toString().trim();
                 message = umessage.getText().toString().trim();
                if(name.isEmpty() ||  mobile.isEmpty() || email.isEmpty() || message.isEmpty()){
                    Toast.makeText(Feedback.this,"",Toast.LENGTH_SHORT).show();
                }else {
                    feedback();
                }
            }
        });

    }

    private void feedback() {
        RequestParams params = new RequestParams();
        params.put("name", name);
        params.put("mobile",mobile);
        params.put("email",email);
        params.put("message",message);


        pDialog.setMessage("processing..");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(feedbackURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pDialog.dismiss();
                String res = new String(responseBody);
                if(res.equals("200")){
                    Toast.makeText(Feedback.this," Submitted successfully",Toast.LENGTH_SHORT).show();
                    finish();
                }else if(res.equals("202")){
                    Toast.makeText(Feedback.this,"Failure",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
                Toast.makeText(Feedback.this,"Connectivity failed!",Toast.LENGTH_SHORT).show();
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
            Intent i = new Intent(Feedback.this, Feedback.class);
            startActivity(i);
        }

        if (id == R.id.action_setting2) {
            Intent i = new Intent(Feedback.this, Aboutus.class);
            startActivity(i);
        }

        if (id == R.id.action_setting3) {
            Intent i = new Intent(Feedback.this, HelpServices.class);
            startActivity(i);
        }

        if (id == R.id.action_setting4) {
            Intent i = new Intent(Feedback.this, MainActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
}
