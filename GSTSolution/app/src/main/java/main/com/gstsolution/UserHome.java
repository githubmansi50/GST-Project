package main.com.gstsolution;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import main.com.gstsolution.firebase.DeleteTokenService;

public class UserHome extends AppCompatActivity {

    Button news,discussion,knowledgesolution,guestarticles,favourites,GSTcalculator,implinks,gstacts,btndownload;
    TextView heading;
    boolean running = true;
    protected static final int RESULT_SPEECH = 1;
    String id,servletName = "", myText = "";
    ImageButton btnSpeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        heading = (TextView) findViewById(R.id.textUserHomeHeading);
        news = (Button) findViewById(R.id.btnUserHomeNews);
        discussion = (Button) findViewById(R.id.btnUserHomeDiscussionForum);
        knowledgesolution = (Button) findViewById(R.id.btnUserHomeKnowledgeSolution);
        guestarticles = (Button) findViewById(R.id.btnUserHomeGuestArticles);
        favourites = (Button) findViewById(R.id.btnUserHomeFavourites);
        GSTcalculator = (Button) findViewById(R.id.btnUserHomeGSTCalculator);
        implinks = (Button) findViewById(R.id.btnUserHomeImportantLinks);
        gstacts = (Button) findViewById(R.id.btnUserHomeGSTActs);
        btndownload=(Button) findViewById(R.id.btnUserHomeDownload);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);


        heading.setText("Welcome "+UserData.name);

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserData.type = "1";
                Intent i = new Intent(UserHome.this,GSTNews.class);
                startActivity(i);
            }
        });

        discussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(UserHome.this, DeleteTokenService.class);
                startService(in);
                Intent i = new Intent(UserHome.this,DiscussionForum.class);
                startActivity(i);
            }
        });

        knowledgesolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserData.type = "2";
                Intent i = new Intent(UserHome.this,KnowledgeSolution.class);
                startActivity(i);
            }
        });

        guestarticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserData.type = "3";
                Intent i = new Intent(UserHome.this,GSTArticles.class);
                startActivity(i);
            }
        });

        favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserData.searchflag = "0";
                Intent i = new Intent(UserHome.this,Favourites.class);
                startActivity(i);
            }
        });

        GSTcalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserHome.this,GSTcalculator.class);
                startActivity(i);
            }
        });

        implinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserHome.this,ImportantLinks.class);
                startActivity(i);
            }
        });



        gstacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserHome.this,GSTacts.class);
                startActivity(i);
            }
        });

        btndownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserHome.this,RecommendMaterial.class);
                startActivity(i);
            }
        });


        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UserData.searchflag = "1";
                running = false;
                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    //txtText.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Your device doesn't support Speech to Text!",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    myText = text.get(0);
                    //txtText.setText(text.get(0));
                    //myText=txtText.getText().toString();
                    UserData.searchword = myText;
                    Toast.makeText(getApplicationContext(), myText,Toast.LENGTH_LONG).show();
                    Intent i = new Intent(UserHome.this,ViewSearch.class);
                    startActivity(i);
                    //finish();
                }
                break;
            }

        }
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
            Intent i = new Intent(UserHome.this, Feedback.class);
            startActivity(i);
        }

        if (id == R.id.action_setting2) {
            Intent i = new Intent(UserHome.this, Aboutus.class);
            startActivity(i);
        }

        if (id == R.id.action_setting3) {
            Intent i = new Intent(UserHome.this, HelpServices.class);
            startActivity(i);
        }

        if (id == R.id.action_setting4) {
            Intent i = new Intent(UserHome.this, MainActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
}
