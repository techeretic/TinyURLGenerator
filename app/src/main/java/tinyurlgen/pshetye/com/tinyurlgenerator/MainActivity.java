package tinyurlgen.pshetye.com.tinyurlgenerator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import java.util.Objects;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUrl;
    private Button mButton;
    private TextView mResult;
    private static final String sUrls = "https://google.com,https://facebook.com,https://tripadvisor.com,https://twitter.com,https://android.com,https://google.com,https://facebook.com,https://tripadvisor.com,https://twitter.com,https://android.com,https://google.com,https://facebook.com,https://tripadvisor.com,https://twitter.com,https://android.com";
    private TinyUrlService mTinyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUrl = (EditText) findViewById(R.id.url);
        mResult = (TextView) findViewById(R.id.result);
        mButton = (Button) findViewById(R.id.button);

        mUrl.setText(sUrls);

        mButton.setOnClickListener(this);

        mTinyService = new TinyUrlService();

        OBus.getBus().register(mTinyService);
    }

    @Override
    public void onClick(View v) {
        if (mResult != null) {
            mResult.setText("");
        }
        if (mUrl != null && !TextUtils.isEmpty(mUrl.getText().toString())) {
            OBus.getBus().post(new Request(sUrls));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        OBus.getBus().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        OBus.getBus().register(this);
    }

    @Subscribe
    public void onApiError(retrofit.RetrofitError error) {
        if (mResult != null) {
            mResult.setText("FAILED!!");
        }
    }

    @Subscribe
    public void onApiCallComplete(String result) {
        Log.d("Prathamesh","in onApiCallComplete - " + result);
        if (mResult != null) {
            String prev = mResult.getText().toString();
            StringBuilder sb = new StringBuilder();
            if (result != null) {
                if (!TextUtils.isEmpty(prev)) {
                    sb.append(prev);
                    sb.append("\n");
                }
                sb.append(result);
            }
            mResult.setText(sb.toString());
        }
    }

    public class TinyUrlService implements retrofit.Callback {
        @Subscribe
        public void fetchUrls(Request request) {
            String urls[] = request.getData().split(",");
            for (String url : urls) {
                Log.d("Prathamesh","Fetching URL : " + url);
                TinyUrlGen.getTinyUrl(url, this);
            }
        }

        @Override
        public void success(Object o, retrofit.client.Response response) {
            OBus.getBus().post(((Response)o).getResult());
        }

        @Override
        public void failure(RetrofitError error) {
            OBus.getBus().post(error);
        }
    }

    private class Request {
        private String data;

        public Request(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }
}
