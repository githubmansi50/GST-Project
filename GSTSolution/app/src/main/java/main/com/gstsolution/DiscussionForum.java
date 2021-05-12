package main.com.gstsolution;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;

public class DiscussionForum extends AppCompatActivity {

    static TextView question;
    EditText editnew;
    Button send;
    String sending= "";
    public static String maxid="";
    String getdiscussionURL = IPaddress.ip+"getdiscussion.php";
    String sendmsgURL = IPaddress.ip+"postmsg.php";
    static String text="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_forum);

        send = (Button) findViewById(R.id.btnSendDiscussionForum);
        question = (TextView) findViewById(R.id.textDiscussionInfo);
        editnew= (EditText) findViewById(R.id.editDiscussionForum);


        getDiscussion();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sending = editnew.getText().toString().trim();
                if(!sending.isEmpty()){
                    sendMsg();
                }
            }
        });
    }

    public static void updateMessage(String msg){
        text = text+"\n"+msg;
        question.setText(text);
    }

    @Override
    public void onStop(){
        super.onStop();
        try {
            FirebaseInstanceId.getInstance().deleteInstanceId();
            //finish();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Unable to close activity! Please try agian.", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendMsg(){
        if(UserData.fid.isEmpty()){
            Toast.makeText(this, "Currently unable to send msg! please try again.", Toast.LENGTH_SHORT).show();
            FirebaseInstanceId.getInstance().getToken();
        }else {
            RequestParams params = new RequestParams();
            params.put("msg", sending);
            params.put("uid", UserData.id);
            params.put("uname", UserData.name);

            final ProgressDialog pDialog = new ProgressDialog(DiscussionForum.this);
            pDialog.setMessage("posting text on forum...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(false);
            pDialog.show();

            AsyncHttpClient client = new AsyncHttpClient();
            client.get(sendmsgURL, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    pDialog.dismiss();
                    //FirebaseInstanceId.getInstance().getId();
                    String res = new String(responseBody);
                    if (res.equals("200")) {
                        editnew.setText("");
                        editnew.setHint("Type here");
                        sending = "";
                    } else {
                        Toast.makeText(DiscussionForum.this, "Message posting failed! Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    pDialog.dismiss();
                    Toast.makeText(DiscussionForum.this, "Message posting failed! Trying to resend.", Toast.LENGTH_SHORT).show();
                    DiscussionForum.this.sendMsg();
                }
            });
        }
    }

    public void getDiscussion(){
        RequestParams params = new RequestParams();
        params.put("id",1);

        final ProgressDialog pDialog = new ProgressDialog(DiscussionForum.this);
        pDialog.setMessage("please wait...");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getdiscussionURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pDialog.dismiss();
                String res = new String(responseBody);
                question.setText(res);
                text = res;
                if(res.equals("Error in fetching posts!")){
                    getDiscussion();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
                question.setText("Error in fetching posts from server!");
                getDiscussion();
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
            Intent i = new Intent(DiscussionForum.this, Feedback.class);
            startActivity(i);
        }

        if (id == R.id.action_setting2) {
            Intent i = new Intent(DiscussionForum.this, Aboutus.class);
            startActivity(i);
        }

        if (id == R.id.action_setting3) {
            Intent i = new Intent(DiscussionForum.this, HelpServices.class);
            startActivity(i);
        }

        if (id == R.id.action_setting4) {
            Intent i = new Intent(DiscussionForum.this, MainActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
}
