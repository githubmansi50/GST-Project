package main.com.gstsolution;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class sgst extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgst);

        Button b1 = (Button)findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Andhra%20Pradesh%20SGST_0.pdf"));
                startActivity(i1);

            }
        });

        Button b2 = (Button)findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Arunachal%20Pradesh%20SGST.pdf"));
                startActivity(i2);

            }
        });

        Button b3 = (Button)findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Assam%20SGST.pdf"));
                startActivity(i3);

            }
        });

        Button b4 = (Button)findViewById(R.id.b4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Bihar%20SGST_0.pdf"));
                startActivity(i4);

            }
        });
        Button b5 = (Button)findViewById(R.id.b5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i5 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Chhattisgarh%20SGST%20-%20English.pdf"));
                startActivity(i5);

            }
        });
        Button b6 = (Button)findViewById(R.id.b6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Delhi%20SGST_0.pdf"));
                startActivity(i6);

            }
        });


        Button b7 = (Button)findViewById(R.id.b7);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i7 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Goa%20SGST%20-%20English.pdf"));
                startActivity(i7);

            }
        });


        Button b8 = (Button)findViewById(R.id.b8);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i8 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Gujarat%20SGST%20-%20English.pdf"));
                startActivity(i8);

            }
        });


        Button b9= (Button)findViewById(R.id.b9);
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i9 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Haryana%20SGST%20-%20English.pdf"));
                startActivity(i9);

            }
        });

        Button b10= (Button)findViewById(R.id.b10);
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i10 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Himachal%20Pradesh%20SGST.pdf"));
                startActivity(i10);

            }
        });

        Button b11= (Button)findViewById(R.id.b11);
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i11 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Jammu%20and%20Kashmir%20SGST.pdf"));
                startActivity(i11);

            }
        });

        Button b12= (Button)findViewById(R.id.b12);
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i12 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Jharkhand%20SGST.pdf"));
                startActivity(i12);

            }
        });

        Button b13= (Button)findViewById(R.id.b13);
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i13 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Karnataka%20SGST.pdf"));
                startActivity(i13);

            }
        });

        Button b14= (Button)findViewById(R.id.b14);
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i14 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Kerala%20SGST.pdf"));
                startActivity(i14);

            }
        });

        Button b15= (Button)findViewById(R.id.b15);
        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i15 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Madhya%20Pradesh%20SGST.pdf"));
                startActivity(i15);

            }
        });

        Button b16= (Button)findViewById(R.id.b16);
        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i16 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://gstcouncil.gov.in/sites/default/files/IGST.pdf"));
                startActivity(i16);

            }
        });

        Button b17= (Button)findViewById(R.id.b17);
        b17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i17 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Maharashtra%20SGST.pdf"));
                startActivity(i17);

            }
        });


        Button b18= (Button)findViewById(R.id.b18);
        b18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i18 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Manipur%20SGST.pdf"));
                startActivity(i18);

            }
        });

        Button b19= (Button)findViewById(R.id.b19);
        b19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i19 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Meghalaya%20SGST.pdf"));
                startActivity(i19);

            }
        });

        Button b20= (Button)findViewById(R.id.b20);
        b20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i20 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Mizoram%20SGST%20-%20English.pdf"));
                startActivity(i20);

            }
        });

        Button b21= (Button)findViewById(R.id.b21);
        b21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i21=  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Nagaland_GST_Act.pdf"));
                startActivity(i21);

            }
        });

        Button b22= (Button)findViewById(R.id.b22);
        b22.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i22 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Odisha%20SGST.pdf"));
                        startActivity(i22);

                    }
                });

        Button b23= (Button)findViewById(R.id.b23);
        b23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i23 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Puducherry%20SGST.pdf"));
                startActivity(i23);

            }
        });

        Button b24= (Button)findViewById(R.id.b24);
        b24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i24 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Punjab%20SGST.pdf"));
                startActivity(i24);

            }
        });

        Button b25= (Button)findViewById(R.id.b25);
        b25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i25 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Rajasthan%20SGST%20-%20English%20%26%20Hindi.pdf"));
                startActivity(i25);

            }
        });

        Button b26= (Button)findViewById(R.id.b26);
        b26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i26 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Sikkim%20-%20English.pdf"));
                startActivity(i26);

            }
        });

        Button b27= (Button)findViewById(R.id.b27);
        b27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i27 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Sikkim%20-%20English.pdf"));
                startActivity(i27);

            }
        });

        Button b28= (Button)findViewById(R.id.b28);
        b28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i28 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Tamil%20Nadu%20SGST.pdf"));
                startActivity(i28);

            }
        });

        Button b29= (Button)findViewById(R.id.b29);
        b29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i29 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/TelanganaGST_ACT.pdf"));
                startActivity(i29);

            }
        });

        Button b30= (Button)findViewById(R.id.b30);
        b30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i30 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Tripura%20SGST.pdf"));
                startActivity(i30);

            }
        });

        Button b31= (Button)findViewById(R.id.b31);
        b31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i31 =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.gstcouncil.gov.in/sites/default/files/STATE%20GST%20ACT%20PDF/Uttarakhand%20SGST%20-%20English.pdf"));
                startActivity(i31);

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
            Intent i = new Intent(sgst.this, Feedback.class);
            startActivity(i);
        }

        if (id == R.id.action_setting2) {
            Intent i = new Intent(sgst.this, Aboutus.class);
            startActivity(i);
        }

        if (id == R.id.action_setting3) {
            Intent i = new Intent(sgst.this, HelpServices.class);
            startActivity(i);
        }

        if (id == R.id.action_setting4) {
            Intent i = new Intent(sgst.this, MainActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
}
