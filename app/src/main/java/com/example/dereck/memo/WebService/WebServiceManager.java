package com.example.dereck.memo.WebService;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.dereck.memo.App;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Try to create WS outside Activity.
 */
public class WebServiceManager {
    public static final String KEY_FROM = "from";
    public static final String KEY_MEMO = "memo";

    private AsyncHttpClient client;
    private RequestParams requestParams;
    private String url;

    public WebServiceManager(String url)
    {
        this.url = url;
        this.client = new AsyncHttpClient();
        this.requestParams = new RequestParams();
    }

    public <T> WebServiceManager put(String key, T value)
    {
        this.requestParams.put(key, value);
        return this;
    }

    public void resetParams()
    {
        this.requestParams = null;
        this.requestParams = new RequestParams();
    }

    public static boolean isConnecte(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null)
        {
            return networkInfo.isConnected();
        }
        return false;
    }

    public void call(final Context context)
    {
        if (this.isConnecte(App.getContext()))
        {
            client.post(this.url, this.requestParams, new AsyncHttpResponseHandler()
            {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String retour = new String(responseBody);

                    try
                    {
                        JSONObject jsonObject = new JSONObject(retour);
                        JSONObject jsonObjectForm = jsonObject.getJSONObject(KEY_FROM);
                        Toast.makeText(context, jsonObjectForm.getString(KEY_MEMO), Toast.LENGTH_LONG).show();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
        }
    }
}
