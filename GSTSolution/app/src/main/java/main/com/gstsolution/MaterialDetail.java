package main.com.gstsolution;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MaterialDetail extends AppCompatActivity {

    LinearLayout ll1,ll;
    TextView title;
    TextView link;
    static TextView text;
    VideoView v;
    TextView attach;
    ProgressDialog pDialog;
    String attachmentURL="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_detail);

        ll = (LinearLayout) findViewById(R.id.layoutMaterialDetail);
        ll1 = (LinearLayout) findViewById(R.id.layoutMaterialDetail2);
        title = (TextView) findViewById(R.id.textMaterialDetailTitle);
        link = (TextView) findViewById(R.id.textMaterialDetailDownloadLink);

        link.setPaintFlags(link.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        attachmentURL = IPaddress.ip+"/material/"+UserData.rd.attachment;

        text = new TextView(MaterialDetail.this);
        text.setTextColor(Color.WHITE);
        ll.addView(text);

        title.setText(UserData.rd.title);

        if(UserData.rd.typ==3){
            v = new VideoView(this);
            ll1.addView(v);
            playVideo();
        }else{
            attach = new TextView(this);
            attach.setTextColor(Color.WHITE);
            attach.setText(UserData.rd.attachment);
            if(UserData.rd.typ==1) {
                attach.setCompoundDrawablesWithIntrinsicBounds(R.drawable.smallwordicon,0,0,0);
            }else if(UserData.rd.typ==2){
                attach.setCompoundDrawablesWithIntrinsicBounds(R.drawable.smallpdficon, 0, 0, 0);
            }
            ll1.addView(attach);
        }

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MaterialDetail.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) MaterialDetail.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                }

                else {
                    Toast.makeText(getApplicationContext(),"Downloading",Toast.LENGTH_LONG).show();
                    downloadFile(UserData.rd.attachment);
                    Toast.makeText(getApplicationContext(),"Downloaded!!Plz check downloads folder",Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 1: if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
                downloadFile(UserData.rd.attachment);
            }break;
            default:
        }
    }

    public void playVideo(){
        pDialog = new ProgressDialog(MaterialDetail.this);
        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar
        pDialog.show();

        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(MaterialDetail.this);
            mediacontroller.setAnchorView(v);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(attachmentURL);
            v.setMediaController(mediacontroller);
            v.setVideoURI(video);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        v.requestFocus();
        v.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                v.start();
            }
        });
    }

    public void downloadFile(final String filename){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    //ProgressDialog pDialog = ProgressDialog.show(mContext, "Download in progress", "processing...",true,false);
                    URL url = new URL(IPaddress.ip+"/material/"+filename);
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    InputStream inputStream = connection.getInputStream();

                    File file = new File(Environment.getExternalStorageDirectory().getPath()+"/Download/"+filename);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);

                    int bytesRead;
                    byte[] buffer = new byte[4096];
                    while((bytesRead = inputStream.read(buffer)) != -1){
                        fileOutputStream.write( buffer, 0, bytesRead);
                        //text.setText("Downloading file...");
                        //UpdateDisplay("Downloading file...");
                    }
                    fileOutputStream.close();
                    inputStream.close();
                    //pDialog.dismiss();
                    //text.setText("Download complete");
                    //UpdateDisplay("Download complete");
                    //link.setTextColor(Color.YELLOW);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void UpdateDisplay(String msg){
        text.setText(msg);
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
            Intent i = new Intent(MaterialDetail.this, Feedback.class);
            startActivity(i);
        }

        if (id == R.id.action_setting2) {
            Intent i = new Intent(MaterialDetail.this, Aboutus.class);
            startActivity(i);
        }

        if (id == R.id.action_setting3) {
            Intent i = new Intent(MaterialDetail.this, HelpServices.class);
            startActivity(i);
        }

        if (id == R.id.action_setting4) {
            Intent i = new Intent(MaterialDetail.this, MainActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
}
