package test.my.mytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "test.my.mytest.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Send button */
    public void sendMessage (View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message;
        try {
             message = editText.getText().toString();
        } catch (Exception e) {
            message = e.getStackTrace().toString();
        }
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void sendJsonRequest (View view) {
        final Intent intent = new Intent(this, DisplayMessageActivity.class);
        String url = "https://api.ipify.org/?format=json";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        intent.putExtra(EXTRA_MESSAGE, response.toString());
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        intent.putExtra(EXTRA_MESSAGE, error.toString());
                        startActivity(intent);
                    }
                });

        // Access the RequestQueue through your singleton class.
        HTTPRequestQueueSingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }
}
