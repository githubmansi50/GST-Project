package main.com.gstsolution;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class GSTcalculator extends AppCompatActivity {

    Spinner sservice,field,category;
    Button calculate;
    EditText price,qauntity;
    TextView resultfinal;
    String [] fields,categories;
    String pric="",qaunt="",service="",fild="",cate="";
    String getdetailsURL = IPaddress.ip+"getcalc.php";
    String getpercentURL = IPaddress.ip+"getpercent.php";
    String getcategoryURL = IPaddress.ip+"getcategory.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gstcalculator);

        sservice = (Spinner) findViewById(R.id.spinnerGSTCalculatorService);
        field = (Spinner) findViewById(R.id.spinnerGSTCalcField);
        category = (Spinner) findViewById(R.id.spinnerGSTCalcProductCategory);
        price = (EditText) findViewById(R.id.editGSTCalcInitialPrice);
        qauntity = (EditText) findViewById(R.id.editGSTCalcQuantity);
        calculate = (Button) findViewById(R.id.btnGSTCalcCalculate);
        resultfinal = (TextView) findViewById(R.id.textGSTCalcResult);

        getDetails();

        String[] s= {"Select service type","CGST","SGST","CGST + SGST"};
        ArrayAdapter a1 = new ArrayAdapter(GSTcalculator.this,android.R.layout.simple_spinner_dropdown_item,s);
        sservice.setAdapter(a1);

        sservice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                resultfinal.setText("");
                switch(position){
                    case 0 : service = "";
                        break;
                    default: service = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        field.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                resultfinal.setText("");
                switch(position){
                    case 0 : fild = "";
                        break;
                    default: fild = parent.getItemAtPosition(position).toString();
                        getCategory();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                resultfinal.setText("");
                switch(position){
                    case 0: cate = "";
                        break;
                    default: cate = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pric = price.getText().toString().trim();
                qaunt = qauntity.getText().toString().trim();
                if(pric.isEmpty() || qaunt.isEmpty() || service.isEmpty() || fild.isEmpty() || cate.isEmpty()){
                    Toast.makeText(GSTcalculator.this, "Provide all required information first!", Toast.LENGTH_SHORT).show();
                }else{
                    callCalculate();
                }
            }
        });
    }

    public void getCategory(){
        RequestParams params = new RequestParams();
        params.put("field",fild);

        final ProgressDialog pDialog = new ProgressDialog(GSTcalculator.this);
        pDialog.setMessage("Processing! Please wait...");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getcategoryURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pDialog.dismiss();
                String res = new String(responseBody);
                try{
                    JSONObject o = new JSONObject(res);
                    if(o.getString("success").equals("200")){
                        JSONArray b = o.getJSONArray("category");
                        categories = new String[b.length()+1];
                        categories[0] = "Select category";
                        for(int i=1;i<=b.length();i++){
                            categories[i] = b.getString(i-1);
                        }
                        ArrayAdapter b1 = new ArrayAdapter(GSTcalculator.this, android.R.layout.simple_spinner_item, categories);
                        category.setAdapter(b1);
                    }else{
                        Toast.makeText(GSTcalculator.this, res, Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){
                    Toast.makeText(GSTcalculator.this, res, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
                Toast.makeText(GSTcalculator.this, "Connectivity failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDetails(){
        RequestParams params = new RequestParams();
        params.put("id",1);

        final ProgressDialog pDialog = new ProgressDialog(GSTcalculator.this);
        pDialog.setMessage("Processing! Please wait...");
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
                        JSONArray a = o.getJSONArray("field");
                        fields = new String[a.length()+1];
                        fields[0] = "Select field";
                        for(int i=1;i<=a.length();i++){
                            fields[i] = a.getString(i-1);
                        }
                        ArrayAdapter a1 = new ArrayAdapter(GSTcalculator.this, android.R.layout.simple_spinner_item, fields);
                        field.setAdapter(a1);
                        categories = new String[1];
                        categories[0] = "Select category";
                        ArrayAdapter b1 = new ArrayAdapter(GSTcalculator.this, android.R.layout.simple_spinner_item, categories);
                        category.setAdapter(b1);
                    }else{
                        Toast.makeText(GSTcalculator.this, res, Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){
                    Toast.makeText(GSTcalculator.this, res, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
                Toast.makeText(GSTcalculator.this, "Connectivity failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void callCalculate(){
        RequestParams params = new RequestParams();
        params.put("field",fild);
        params.put("category",cate);
        params.put("service",service);
        params.put("price",pric);
        params.put("qauntity",qaunt);

        final ProgressDialog pDialog = new ProgressDialog(GSTcalculator.this);
        pDialog.setMessage("Processing! Please wait...");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getpercentURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pDialog.dismiss();
                String res = new String(responseBody);
                try{
                    JSONObject o = new JSONObject(res);
                    if(o.getString("success").equals("200")){
                        double percent = o.getDouble("percent");
                        double tax = (Integer.parseInt(pric) * (percent/100)) * Integer.parseInt(qaunt);
                        resultfinal.setText("Calculated tax :\n"+service +" : "+tax);
                    }
                }catch(Exception e){
                    Toast.makeText(GSTcalculator.this, res, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
                Toast.makeText(GSTcalculator.this, "Connectivity failed!", Toast.LENGTH_SHORT).show();
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
            Intent i = new Intent(GSTcalculator.this, Feedback.class);
            startActivity(i);
        }

        if (id == R.id.action_setting2) {
            Intent i = new Intent(GSTcalculator.this, Aboutus.class);
            startActivity(i);
        }

        if (id == R.id.action_setting3) {
            Intent i = new Intent(GSTcalculator.this, HelpServices.class);
            startActivity(i);
        }

        if (id == R.id.action_setting4) {
            Intent i = new Intent(GSTcalculator.this, MainActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
}
