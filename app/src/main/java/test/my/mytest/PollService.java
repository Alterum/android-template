package test.my.mytest;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
public class PollService extends IntentService {
    private static final String TAG = "PollService";

    private static final String ACTION_QUEST_INFO = "test.my.mytest.action.QUEST_INFO";

    private static final String EXTRA_1 = "test.my.mytest.action.TEST_VALUE";

    public PollService() {
        super(TAG);
    }

    public static void startGetQuestInfo(Context context, final String extraResponseKey) {
        Intent intent = new Intent(context, PollService.class);
        intent.setAction(ACTION_QUEST_INFO);
        intent.putExtra(EXTRA_1, extraResponseKey);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "Received an intent: " + intent);
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_QUEST_INFO.equals(action)) {
                final String param = intent.getStringExtra(EXTRA_1);
                handleActionGetQuestInfo(param);
            }
        }
    }

    private void handleActionGetQuestInfo(final String extraResponseKey) {
        Log.i(TAG, "GetQuestInfo function has been launched with argument: " + extraResponseKey);

        final Intent intent = new Intent(this, DisplayMessageActivity.class);
        String url = "http://jklab.cf:33666/";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        intent.putExtra(extraResponseKey, response.toString());
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        intent.putExtra(extraResponseKey, error.toString());
                        startActivity(intent);
                    }
                });

        // Access the RequestQueue through your singleton class.
        HTTPRequestQueueSingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }

}
