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

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "Received an intent: " + intent);
        if (intent != null) {
            handleActionGetQuestInfo("test");
        }
    }

    private void handleActionGetQuestInfo(String mUri) {
        Log.i(TAG, "GetQuestInfo function has been launched with argument: " + mUri);
    }

}
