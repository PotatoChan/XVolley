package com.chenjiarun.xvolley.xvolleyexample;

import android.util.Log;

import com.android.volley.VolleyError;
import com.chenjiarun.xvolley.net.BasicHttp;

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
        api.requestFailure(666L, "服务器繁忙,请稍后再试");
    }

    @Override
    public void onResponse(JSONObject response) {

        if (response == null) {
            api.requestFailure(601L, "response 没有返回");
            return;
        }

        Log.e("XVolley", "[result] " + response.toString());

        api.requestSuccess(response, "");
    }

}
