package com.chenjiarun.xvolley.xvolleyexample;

import android.util.Log;

import com.android.volley.VolleyError;

import org.json.JSONObject;


/**
 * 作用：
 * 创建者：陈佳润
 * 创建日期：16/3/23
 * 更新历史：
 */
public class LSHttp extends BasicHttp {

    private LSAPI api;

    public LSHttp(LSAPI api) {
        super(api);
        this.api = api;

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        api.requestFailure(APIStatus.SERVER_ERROR, APIStatus.SERVER_ERROR_INFO);
    }

    @Override
    public void onResponse(JSONObject response) {

        if (response == null) {
            api.requestFailure(APIStatus.EMPTY_RETURN, APIStatus.EMPTY_RETURN_INFO);
            return;
        }

        Log.e("XVolley", "[result] " + response.toString());

    }

}
