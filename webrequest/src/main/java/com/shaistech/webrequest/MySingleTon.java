package com.shaistech.webrequest;

/**
 * Created by rizz on 25-03-2018.
 */

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class MySingleTon {

    private RequestQueue requestQueue;
    private static Context mContext;
    private static MySingleTon mInstance;

    private MySingleTon(Context context){
        mContext = context;
        requestQueue = getRequestQueue();

    }

    private RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySingleTon getInstance(Context context){
        if(mInstance==null){
            mInstance = new MySingleTon(context);
        }
        return mInstance;
    }

    public <T> void addToRequestQueue(Request request){
        requestQueue.add(request);
    }
}

