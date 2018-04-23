package com.shaistech.webrequest;

import android.content.Context;
import android.net.Uri;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bikomobile.multipart.Multipart;


import java.util.Map;

public class WebRequest {

    private final static int GET = 0;
    private final static int POST =1;

    private Context mContext;

    public WebRequest(Context mContext) {
        this.mContext = mContext;
    }

    public void getRequest(final String url, final Callback c){
        c.preExecute(url);
        MySingleTon.getInstance(mContext)
                .addToRequestQueue(new StringRequest(
                        GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //c.onResponse(response,url);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                c.onErrorResponse(error,url,error.networkResponse.statusCode);
                            }
                        }

                ){
                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        c.onResponse(new String(response.data),url,response.statusCode);
                        return super.parseNetworkResponse(response);
                    }
                });


    }

    public void postRequest(final String url, final Callback c, final Map<String,String> postData){
        c.preExecute(url);
        MySingleTon.getInstance(mContext)
                .addToRequestQueue(new StringRequest(
                        POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //c.onResponse(response,url);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                c.onErrorResponse(error,url, error.networkResponse.statusCode);
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        return postData;
                    }

                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        c.onResponse(new String(response.data),url,response.statusCode);
                        return super.parseNetworkResponse(response);
                    }
                });

    }


    public void multipartRequest(final String url, final Callback c, Map<String,String> postParam,String contentType, String imgKey, String imgName, Uri imgURI){
        c.preExecute(url);
        Multipart multipart = new Multipart(mContext);
        multipart.addFile(contentType,imgKey,imgName,imgURI);
        multipart.addParams(postParam);
        MySingleTon.getInstance(mContext)
                .addToRequestQueue(multipart.getRequest(url, new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        String resultResponse = new String(response.data);
                        c.onResponse(resultResponse,url, response.statusCode);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        c.onErrorResponse(error,url, error.networkResponse.statusCode);
                    }
                }));
    }



    public interface Callback{
        public void preExecute(String url);
        public void onResponse(String response, String url, int statusCode);
        public void onErrorResponse(VolleyError error, String url, int statusCode);
    }


    static class ContentType {
        public static final String IMAGE_JPEG = "image/jpeg" ;
        public static final String IMAGE_JPG = "image/jpg" ;
        public static final String IMAGE_PNG = "image/png" ;
        public static final String IMAGE_ALL_TYPE = "image/*" ;

        public static final String VIDEO_ALL_TYPE = "video/*" ;
        public static final String VIDEO_MP4 = "video/mp4" ;

    }
}

