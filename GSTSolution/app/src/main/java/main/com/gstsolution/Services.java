package main.com.gstsolution;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Services extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);


        Button cgst = (Button) findViewById(R.id.btncgst);
        cgst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://gstcouncil.gov.in/sites/default/files/CGST.pdf"));
                startActivity(i2);

            }
        });


        Button igst = (Button) findViewById(R.id.btnigst);
        igst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://gstcouncil.gov.in/sites/default/files/IGST.pdf"));
                startActivity(i2);

            }
        });


        Button sgst = (Button) findViewById(R.id.btnsgst);
        sgst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(Services.this, sgst.class);
                startActivity(i3);
            }
        });

        Button utgst = (Button) findViewById(R.id.btnutgst);
        utgst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://gstcouncil.gov.in/sites/default/files/UTGST.pdf"));
                startActivity(i4);

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
            Intent i = new Intent(Services.this, Feedback.class);
            startActivity(i);
        }

        if (id == R.id.action_setting2) {
            Intent i = new Intent(Services.this, Aboutus.class);
            startActivity(i);
        }

        if (id == R.id.action_setting3) {
            Intent i = new Intent(Services.this, HelpServices.class);
            startActivity(i);
        }

        if (id == R.id.action_setting4) {
            Intent i = new Intent(Services.this, MainActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
}
