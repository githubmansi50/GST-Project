package main.com.gstsolution;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Registration extends AppCompatActivity {
    EditText uname,uaddress,umobile,upass,uemail;
    Spinner sstate,sdistrict;
    String[] states,districts;
    String name="",state="",district="",mobile="",password="",email="";
    Button submit;
    private String regURL = IPaddress.ip+"uregister.php";
    private String getlistURL = IPaddress.ip+"getstate.php";
    private String getdistrictURL = IPaddress.ip+"getdistrict.php";
    static ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        pDialog = new ProgressDialog(Registration.this);

        uname = (EditText) findViewById(R.id.editUserRegName);
        sstate = (Spinner) findViewById(R.id.editUserRegAddressState);
        sdistrict = (Spinner) findViewById(R.id.editUserRegAddressDistrict);
        umobile = (EditText) findViewById(R.id.editUserRegMobile);
        upass = (EditText) findViewById(R.id.editUserRegPassword);
        uemail = (EditText) findViewById(R.id.editUserRegEmail);
        submit = (Button) findViewById(R.id.btnUserRegSubmit);

        getStateList();

        sstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0: state="";
                        districts = new String[1];
                        districts[0] = "Select district";
                        ArrayAdapter a1 = new ArrayAdapter(Registration.this, android.R.layout.simple_spinner_dropdown_item, districts);
                        sdistrict.setAdapter(a1);
                        break;
                    default:
                        state=adapterView.getItemAtPosition(i).toString();
                        getDistrictList();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sdistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0 : district="";
                        break;
                    default:
                        district = adapterView.getItemAtPosition(i).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = uname.getText().toString().trim();
                password = upass.getText().toString().trim();
                mobile = umobile.getText().toString().trim();
                email = uemail.getText().toString().trim();
                if(name.isEmpty() || password.isEmpty() || mobile.isEmpty() || email.isEmpty() || state.isEmpty() || district.isEmpty()){
                    Toast.makeText(Registration.this,"",Toast.LENGTH_SHORT).show();
                }else {
                    register();
                }
            }
        });

    }

    private void register() {
        RequestParams params = new RequestParams();
        params.put("name", name);
        params.put("password", password);
        params.put("state", state);
        params.put("district",district);
        params.put("mobile",mobile);
        params.put("email",email);

        pDialog.setMessage("processing..");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(regURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pDialog.dismiss();
                String res = new String(responseBody);
                if(res.equals("200")){
                    Toast.makeText(Registration.this,"Registration done successfully",Toast.LENGTH_SHORT).show();
                    finish();
                }else if(res.equals("202")){
                    Toast.makeText(Registration.this,"Mobile number already registered!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
                Toast.makeText(Registration.this,"Connectivity failed!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getStateList(){
        RequestParams params = new RequestParams();
        params.put("id","1");

        pDialog.setMessage("please wait..");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getlistURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pDialog.dismiss();
                String res = new String(responseBody);
                try{
                    JSONObject o = new JSONObject(res);
                    if(o.getString("success").equals("200")){
                        JSONArray a = o.getJSONArray("state");
                        states = new String[a.length()+1];
                        states[0] = "Select state";
                        for(int i=1;i<=a.length();i++){
                            states[i] = a.getString(i-1);
                        }
                        ArrayAdapter a2 = new ArrayAdapter(Registration.this, android.R.layout.simple_spinner_dropdown_item, states);
                        sstate.setAdapter(a2);
                        districts = new String[1];
                        districts[0] = "Select district";
                        ArrayAdapter a1 = new ArrayAdapter(Registration.this, android.R.layout.simple_spinner_dropdown_item, districts);
                        sdistrict.setAdapter(a1);
                    }
                }catch(Exception e){
                    Toast.makeText(Registration.this,res,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
                Toast.makeText(Registration.this,"Some error in server connectivity!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDistrictList(){
        RequestParams params = new RequestParams();
        params.put("id","1");
        params.put("state",state);

        pDialog.setMessage("please wait..");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getdistrictURL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pDialog.dismiss();
                String res = new String(responseBody);
                try{
                    JSONObject o = new JSONObject(res);
                    if(o.getString("success").equals("200")){
                        JSONArray a = o.getJSONArray("district");
                        districts = new String[a.length()+1];
                        districts[0] = "Select district";
                        for(int i=1;i<=a.length();i++){
                            districts[i] = a.getString(i-1);
                        }
                        ArrayAdapter a1 = new ArrayAdapter(Registration.this, android.R.layout.simple_spinner_dropdown_item, districts);
                        sdistrict.setAdapter(a1);
                    }
                }catch(Exception e){
                    Toast.makeText(Registration.this,res,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
                Toast.makeText(Registration.this,"Some error in server connectivity!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

