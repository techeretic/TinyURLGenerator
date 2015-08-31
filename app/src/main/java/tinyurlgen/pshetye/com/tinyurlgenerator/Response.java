package tinyurlgen.pshetye.com.tinyurlgenerator;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pshetye on 8/30/15.
 */
public class Response {

    @SerializedName("status")
    private boolean mStatus;

    @SerializedName("q")
    private String mOriginal;

    @SerializedName("result")
    private String mResult;

    @SerializedName("stored")
    private boolean mStored;

    public Response(boolean status, String original, String result, boolean stored) {
        mStatus = status;
        mOriginal = original;
        mResult = result;
        mStored = stored;
    }


    public boolean isStatus() {
        return mStatus;
    }

    public String getOriginal() {
        return mOriginal;
    }

    public String getResult() {
        return mResult;
    }

    public boolean isStored() {
        return mStored;
    }

    public void setStatus(boolean status) {
        mStatus = status;
    }

    public void setOriginal(String original) {
        mOriginal = original;
    }

    public void setResult(String result) {
        mResult = result;
    }

    public void setStored(boolean stored) {
        mStored = stored;
    }
}
