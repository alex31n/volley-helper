package com.ornach.volleyhelper;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyController  {

      private static final String TAG = VolleyController.class.getSimpleName();

      private RequestQueue mRequestQueue;

      private Context context;

      private static Configuration mConfig;

      private VolleyController(Context context){
            this.context = context;
            if (mConfig ==null) {
                mConfig = new Configuration.Builder().build();
            }
      }

      protected static synchronized VolleyController getInstance(Context context) {
            return new VolleyController(context);
      }

      private RequestQueue getRequestQueue() {
            if (mRequestQueue == null) {
                  mRequestQueue = Volley.newRequestQueue(context);
            }
            return mRequestQueue;
      }

      protected <T> void addToRequestQueue(Request<T> req, String tag) {

            req.setShouldCache(mConfig.isCache());
            req.setRetryPolicy(new DefaultRetryPolicy(
                    mConfig.getTimeout(), mConfig.getMaxNumRetries(), DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            // set the default tag if tag is empty
            req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
            getRequestQueue().add(req);

      }


      protected <T> void addToRequestQueue(Request<T> req) {
            addToRequestQueue(req,TAG);
      }

      protected void cancelPendingRequests(Object tag) {
            if (mRequestQueue != null) {
                  mRequestQueue.cancelAll(tag);
            }
      }

      public static void setConfigure(Configuration configuration){
            mConfig = configuration;
      }

      public static Configuration getConfiguration() {
            return mConfig;
      }


}