package com.chenjiarun.xvolley.net;


import android.util.Log;

import com.android.volley.Response;
import com.chenjiarun.xvolley.app.VolleyApp;

import org.json.JSONObject;

/**
 * Created by Ives on 16/8/24.
 */
public abstract class BasicHttp implements Response.Listener<JSONObject>, Response.ErrorListener {


    private BasicAPI api;

    private BasicRequest request;

    public BasicHttp(BasicAPI api) {
        this.api = api;
    }

    public void request() {

        request = new BasicRequest(api, this, this);

        //请求URL日志
        Log.e("XVolley", "[URL] " + request.getUrl());

        //请求参数日志
        StringBuffer strParams = new StringBuffer("{");
        for (String key : api.getParams().keySet()) {
            strParams.append("{" + key + ":" + api.getParams().get(key) + ",");
        }
        strParams.append("}");

        Log.e("XVolley", "[params] " + strParams);

        //请求header日志
        StringBuffer strHeader = new StringBuffer("{");
        for (String key : api.getHeaders().keySet()) {
            strHeader.append(key + ":" + api.getHeaders().get(key) + ",");
        }
        strHeader.append("}");

        Log.e("XVolley", "[header] " + strHeader);

        VolleyApp.getQueue().add(request);
    }

}
