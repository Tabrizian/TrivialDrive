package ir.saanieh.iabtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import ir.saanieh.iabtest.util.Base64DecoderException;
import ir.saanieh.iabtest.util.IabHelper;
import ir.saanieh.iabtest.util.IabResult;
import ir.saanieh.iabtest.util.Purchase;

public class PurchuseActivity extends Activity {

    IabHelper mHelper;
    String base64EncodedString = "MIHNMA0GCSqGSIb3DQEBAQUAA4G7ADCBtwKBrwDXA/G0IkT85G90jP5fV8Vodd0H" +
            "8Y1cKUHpXL4rPQqIQCvkoN08vp0vT/hPTOZ/YpxatqopIE1Lf4j9BCPUpQiSo/I7Sa0vljhhzJEtDlApdqmO6" +
            "mPUTXLfSJvOyA8A42xC7RvSGChS0bDgRA8UVr/xJohlbFLUOX5VdK4ygFixA8352M/Fy5kzhMDz5G94JeP5VD" +
            "JrEWE5a4ZpSUOw6Q/atF3T7Kb2te783m3wC6kCAwEAAQ==";
    String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchuse);

        mHelper = new IabHelper(this, base64EncodedString);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            @Override
            public void onIabSetupFinished(IabResult result) {
                Log.d(TAG, "Problem setting up In-app Billing: " + result);
            }
        });

        mHelper.launchPurchaseFlow(this, "1000", 10001,
                new IabHelper.OnIabPurchaseFinishedListener() {
                    @Override
                    public void onIabPurchaseFinished(IabResult result, Purchase info) {
                        Log.d(TAG, "Purchase was successful!");
                    }
                }, "bGoa+V7g/yqDXvKRqq+JTFn4uQZbPiQJo4pf9RzJ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }
}
