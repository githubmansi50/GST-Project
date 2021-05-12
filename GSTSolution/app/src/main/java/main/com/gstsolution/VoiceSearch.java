package main.com.gstsolution;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class VoiceSearch extends AppCompatActivity {

    String id,servletName = "", myText = "";
    Object ret = null, inp = null;
    boolean cancel = false, responseRecd = false;
    protected static final int RESULT_SPEECH = 1;
    private ImageButton btnSpeak;
    private TextView txtText;
    String servletPath = "";
    public int status = 0;
    boolean running = true;
    Button submit;
    int cid;
    Handler h;
    String UID, DOCNAME, DOCPATH;
    private static final String searchURL = IPaddress.ip+"user_speech.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_search);

        txtText = (TextView) findViewById(R.id.txtText);
        submit = (Button) findViewById(R.id.butnSubmit);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                running = false;
                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                    txtText.setText("");
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
                    txtText.setText(text.get(0));
                    myText=txtText.getText().toString();
                    UserData.searchword = myText;
                    Toast.makeText(getApplicationContext(), myText,Toast.LENGTH_LONG).show();
                    Intent i = new Intent(VoiceSearch.this,ViewSearch.class);
                    startActivity(i);
                    finish();
                }
                break;
            }

        }
    }

}
