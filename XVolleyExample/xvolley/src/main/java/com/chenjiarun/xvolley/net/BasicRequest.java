package com.chenjiarun.xvolley.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.chenjiarun.android.tools.util.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Ives on 16/8/24.
 */
public class BasicRequest extends JsonObjectRequest {

    private BasicAPI api;

    public BasicRequest(BasicAPI api, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(api.getMethod(), api.getFinalUrl(), null, listener, errorListener);
        this.api = api;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return api.getHeaders();
    }

    @Override
    public byte[] getBody() {
        return api.getBody();
    }

    /**
     * 重写parseNetworkResponse方法，将返回的数据格式化位UTF-8
     *
     * @param response
     * @return
     */
    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String je = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            String temp = new String(response.data, api.getParamsEncoding());

            return Response.success(new JSONObject(temp), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException var3) {
            return Response.error(new ParseError(var3));
        } catch (JSONException var4) {
            return Response.error(new ParseError(var4));
        }
    }


    @Override
    public String getBodyContentType() {

        if (StringUtils.isNoEmpty(api.getDiyBodyContentType())) {
            return api.getDiyBodyContentType();
        }

        return super.getBodyContentType();
    }
}
