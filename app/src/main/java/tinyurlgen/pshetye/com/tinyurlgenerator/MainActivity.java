package tinyurlgen.pshetye.com.tinyurlgenerator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit.RetrofitError;
import retrofit.client.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, retrofit.Callback {

    private EditText mUrl;
    private Button mButton;
    private TextView mResult;
    private static final String sUrls = "https://google.com,https://facebook.com,https://tripadvisor.com,https://twitter.com,https://android.com,https://google.com,https://facebook.com,https://tripadvisor.com,https://twitter.com,https://android.com,https://google.com,https://facebook.com,https://tripadvisor.com,https://twitter.com,https://android.com,https://google.com,https://facebook.com,https://tripadvisor.com,https://twitter.com,https://android.com,https://google.com,https://facebook.com,https://tripadvisor.com,https://twitter.com,https://android.com,https://google.com,https://facebook.com,https://tripadvisor.com,https://twitter.com,https://android.com,https://google.com,https://google.com,https://facebook.com,https://tripadvisor.com,https://twitter.com,https://android.com,https://google.com";

    @Override
    public void success(Object o, retrofit.client.Response response) {
        if (mResult != null) {
            String prev = mResult.getText().toString();
            Response r = (Response) o;
            StringBuilder sb = new StringBuilder();
            if (r != null) {
                if (!TextUtils.isEmpty(prev)) {
                    sb.append(prev);
                    sb.append("\n");
                }
                sb.append(r.getResult());
            }
            mResult.setText(sb.toString());
        }
    }

    @Override
    public void failure(RetrofitError error) {
        if (mResult != null) {
            mResult.setText("FAILED!!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUrl = (EditText) findViewById(R.id.url);
        mResult = (TextView) findViewById(R.id.result);
        mButton = (Button) findViewById(R.id.button);

        mUrl.setText(sUrls);

        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mResult != null) {
            mResult.setText("");
        }
        if (mUrl != null && !TextUtils.isEmpty(mUrl.getText().toString())) {
            String urls[] = sUrls.split(",");
            for (String url : urls) {
                TinyUrlGen.getTinyUrl(url, this);
            }
        }
    }
}
