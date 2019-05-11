package com.example.omarg.bbhatest;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText1,editText2,editText3,editText4,editText5,editText6,editText7,editText8,editText9,editText10,editText11,editText12,editText13;
    SessionManager session;
    boolean isInRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionManager(this);
        textView = findViewById(R.id.textView);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);
        editText7 = findViewById(R.id.editText7);
        editText8 = findViewById(R.id.editText8);
        editText9 = findViewById(R.id.editText9);
        editText10 = findViewById(R.id.editText10);
        editText11 = findViewById(R.id.editText11);
        editText12 = findViewById(R.id.editText12);
        editText13 = findViewById(R.id.editText13);
        /*if (!session.loggedIn()) {
            session.setLoggedIn(false);
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }*/
    }

    public void btnClick(View v) {
        if(editText1.getText().toString().equals("")||editText2.getText().toString().equals("")||editText3.getText().toString().equals("")||editText4.getText().toString().equals("")
                ||editText5.getText().toString().equals("")||editText6.getText().toString().equals("")||editText7.getText().toString().equals("")||editText8.getText().toString().equals("")
                ||editText9.getText().toString().equals("")||editText10.getText().toString().equals("")||editText11.getText().toString().equals("")||editText12.getText().toString().equals("")
                ||editText13.getText().toString().equals("")){
            new AlertDialog.Builder(this)
                    .setTitle("ERROR")
                    .setMessage("Please Fill All Fields to Proceed")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        else {
            isInRange = true;
            String tblData = editText1.getText().toString() + "," + editText2.getText().toString() + ","
                    + editText3.getText().toString() + "," + editText4.getText().toString() + ","
                    + editText5.getText().toString() + "," + editText6.getText().toString() + ","
                    + editText7.getText().toString() + "," + editText8.getText().toString() + ","
                    + editText9.getText().toString() + "," + editText10.getText().toString() + ","
                    + editText11.getText().toString() + "," + editText12.getText().toString() + ","
                    + editText13.getText().toString() + ",";
            checkText();
            if(isInRange)
            {
                final String url = "http://10f62700.ngrok.io/BBHA/BBHA_Predict";
                JSONArray array = new JSONArray();
                array.put(tblData);
                array.put("%f,%f,%f,%f,%f,%f,%f,%f,%f,%f,%f,%f,%f,");
                array.put(13);
                array.put(1);
                JSONObject params = new JSONObject();
                try {
                    params.put("nargout", 5);
                    params.put("rhs", array);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest objectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        params,
                        new Response.Listener<JSONObject>() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onResponse(JSONObject response) {
                                //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                                try {
                                    int healthyCount, diseasedCount;

                                    JSONArray jsonArray = response.getJSONArray("lhs");

                                    JSONObject object = jsonArray.getJSONObject(1);
                                    JSONArray healthy = object.getJSONArray("mwdata");
                                    diseasedCount = healthy.getInt(0);

                                    JSONObject object2 = jsonArray.getJSONObject(2);
                                    JSONArray diseased = object2.getJSONArray("mwdata");
                                    healthyCount = diseased.getInt(0);

                                    showResults(healthyCount, diseasedCount);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //Log.d("Error", error.getMessage());
                            }
                        }
                );
                requestQueue.add(objectRequest);
            }
            closeKeyboard();
        }
    }

    public void checkText(){
        if (Integer.parseInt(editText2.getText().toString()) != 0 && Integer.parseInt(editText2.getText().toString()) != 1){
            editText2.setText(""); editText2.setHintTextColor(Color.RED); isInRange = false;}
        if (Integer.parseInt(editText3.getText().toString()) != 0 && Integer.parseInt(editText3.getText().toString()) != 1 && Integer.parseInt(editText3.getText().toString()) != 2 && Integer.parseInt(editText3.getText().toString()) != 3){
            editText3.setText(""); editText3.setHintTextColor(Color.RED); isInRange = false;}
        if (Double.parseDouble(editText4.getText().toString()) < 90.00 || Double.parseDouble(editText4.getText().toString()) > 200.00){
            editText4.setText(""); editText4.setHintTextColor(Color.RED); isInRange = false;}
        if (Double.parseDouble(editText5.getText().toString()) < 120.00 || Double.parseDouble(editText5.getText().toString()) > 580.00){
            editText5.setText(""); editText5.setHintTextColor(Color.RED); isInRange = false;}
        if (Integer.parseInt(editText6.getText().toString()) != 0 && Integer.parseInt(editText6.getText().toString()) != 1){
            editText6.setText(""); editText6.setHintTextColor(Color.RED); isInRange = false;}
        if (Integer.parseInt(editText7.getText().toString()) != 0 && Integer.parseInt(editText7.getText().toString()) != 1 && Integer.parseInt(editText7.getText().toString()) != 2){
            editText7.setText(""); editText7.setHintTextColor(Color.RED); isInRange = false;}
        if (Double.parseDouble(editText8.getText().toString()) < 70.00 || Double.parseDouble(editText8.getText().toString()) > 200.00){
            editText8.setText(""); editText8.setHintTextColor(Color.RED); isInRange = false;}
        if (Integer.parseInt(editText9.getText().toString()) != 0 && Integer.parseInt(editText9.getText().toString()) != 1){
            editText9.setText(""); editText9.setHintTextColor(Color.RED); isInRange = false;}
        if (Double.parseDouble(editText10.getText().toString()) < 0.00 || Double.parseDouble(editText10.getText().toString()) > 6.50){
            editText10.setText(""); editText10.setHintTextColor(Color.RED); isInRange = false;}
        if (Integer.parseInt(editText11.getText().toString()) != 0 && Integer.parseInt(editText11.getText().toString()) != 1 && Integer.parseInt(editText11.getText().toString()) != 2){
            editText11.setText(""); editText11.setHintTextColor(Color.RED); isInRange = false;}
        if (Integer.parseInt(editText12.getText().toString()) != 0 && Integer.parseInt(editText12.getText().toString()) != 1 && Integer.parseInt(editText12.getText().toString()) != 2 && Integer.parseInt(editText12.getText().toString()) != 3 && Integer.parseInt(editText12.getText().toString()) != 4){
            editText12.setText(""); editText12.setHintTextColor(Color.RED); isInRange = false;}
        if (Integer.parseInt(editText13.getText().toString()) != 1 && Integer.parseInt(editText13.getText().toString()) != 2 && Integer.parseInt(editText13.getText().toString()) != 3){
            editText13.setText(""); editText13.setHintTextColor(Color.RED); isInRange = false;}
    }

    public void showResults(int healthy, int diseased){
        String str;
        str = "Healthy:\t\t" + healthy + "\nDiseased:\t" + diseased;
        textView.setText(str);
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();

        if(healthy == 0)
            new AlertDialog.Builder(this)
                    .setTitle("RESULTS")
                    .setMessage("Your results have returned in\nPOSITIVE!")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
        else if(diseased == 0)
            new AlertDialog.Builder(this)
                    .setTitle("RESULTS")
                    .setMessage("Your results have returned in\nNEGATIVE!")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .show();
        else
            new AlertDialog.Builder(this)
                    .setTitle("ERROR")
                    .setMessage("Error Occurred! Press Try Again!")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
