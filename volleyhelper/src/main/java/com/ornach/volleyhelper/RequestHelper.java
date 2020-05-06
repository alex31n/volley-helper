package com.ornach.volleyhelper;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RequestHelper {

    private final Context context;
    private Map<String, String> params;
    private Map<String, String> headerParams;
    private byte[] body;

    private RequestHelper(Context context) {
        this.context = context;
        params = new HashMap<>();
        headerParams = new HashMap<>();
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void setHeaders(Map<String, String> params) {
        this.headerParams = params;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public static RequestHelper newInstance(Context context) {
        return new RequestHelper(context);
    }


    /**
     * Creates a new request with the given method.
     *
     * @param method the request {@link Method} to use
     * @param url URL to fetch the string at
     * @param params url Parameters
     * @param listener Listener to receive the String response or errors
     */
    public void httpResponse(int method, String url, final Map<String, String> params, final Listener listener) {
        StringRequest stringRequest = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String msg = "An unknown error occurred while network operation";
                if (error != null) {
                    if (!TextUtils.isEmpty(error.getLocalizedMessage())) {
                        msg = error.getLocalizedMessage();
                    }else if (error.networkResponse!=null && error.networkResponse.data.length>0){
                        String ms = getNetworkResponseData(new String(error.networkResponse.data));
                        if (!TextUtils.isEmpty(ms)){
                            msg = ms;
                        }
                    }
                }
                listener.onError(msg);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return !headerParams.isEmpty() ? headerParams : super.getHeaders();
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return !params.isEmpty() ? params : super.getParams();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return (body != null && body.length > 0) ? body : super.getBody();
            }
        };

        VolleyController.getInstance(context).addToRequestQueue(stringRequest);
    }


    /**
     * Creates a new request with the given method.
     *
     * @param method the request {@link Method} to use
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response or errors
     */
    public void httpResponse(int method, String url, Listener listener) {
        this.httpResponse(method, url, params, listener);
    }

    /**
     * Creates a new request with the GET method.
     *
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response or errors
     */
    public void httpResponse(String url, Listener listener) {
        this.httpResponse(Request.Method.GET, url, params, listener);
    }

    /**
     * Creates a new request with progress update.
     *
     * @param method the request {@link Method} to use
     * @param url URL to fetch the string at
     * @param params url Parameters
     * @param listener Listener to receive the String response or errors
     */
    public void httpResponseWithProgress(int method, String url, final Map<String, String> params, final Listener listener) {
        final ProgressDialog dialog = ProgressDialog.show(context);
        httpResponse(method, url, params, new Listener() {
            @Override
            public void onSuccess(String response) {
                dialog.dismiss();
                listener.onSuccess(response);
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                listener.onError(error);
            }
        });
    }

    /**
     * Creates a new request with progress update.
     *
     * @param method the request {@link Method} to use
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response or errors
     */
    public void httpResponseWithProgress(int method, String url, final Listener listener) {
        final ProgressDialog dialog = ProgressDialog.show(context);
        httpResponse(method, url, new Listener() {
            @Override
            public void onSuccess(String response) {
                dialog.dismiss();
                listener.onSuccess(response);
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                listener.onError(error);
            }
        });
    }

    /**
     * Creates a new GET request with progress update.
     *
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response or errors
     */
    public void httpResponseWithProgress(String url, final Listener listener) {
        final ProgressDialog dialog = ProgressDialog.show(context);
        httpResponse(url, new Listener() {
            @Override
            public void onSuccess(String response) {
                dialog.dismiss();
                listener.onSuccess(response);
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                listener.onError(error);
            }
        });
    }


    private String getNetworkResponseData(String data){
        String msg ="";
        if (data !=null && data.length()>0){
            try {
                JSONObject json =new JSONObject(data);
                if (json.has("Message"))
                msg = json.getString("Message");

            } catch (JSONException e) {
                //e.printStackTrace();
            }
        }

        return msg;
    }

}
