package com.chenjiarun.xvolley.net;

import com.android.volley.Request;
import com.chenjiarun.android.tools.util.MapUtils;
import com.chenjiarun.xvolley.util.NetUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * define a basic api with all attr
 * <p/>
 * Created by Ives on 16/8/24.
 */
public abstract class BasicAPI {


    /**
     * params
     */
    public Map<String, String> params = new HashMap<String, String>();


    /**
     * header
     */
    public Map<String, String> headers = new HashMap<String, String>();


    /**
     * add header
     *
     * @param key   Header's key
     * @param value Header's value
     */
    public void addHeader(String key, String value) {
        MapUtils.add(headers, key, value);
    }

    public void modifyHeader(String key, String value) {
        headers.put(key, value);
    }


    /**
     * get all header
     *
     * @return all header's key-value
     */
    public Map<String, String> getHeaders() {
        return headers;
    }


    /**
     * add Param
     *
     * @param key   param's key
     * @param value params'value
     */
    public void addParam(String key, Object value) {
        MapUtils.add(params, key, value);
    }


    /**
     * get all param
     *
     * @return all param as Map<String,String>
     */
    public Map<String, String> getParams() {
        return params;
    }

    /**
     * encoding of params, default "UTF-8"
     *
     * @return String
     */
    public String getParamsEncoding() {
        return NetBuild.ENCODING;
    }


    /**
     * request method of api
     *
     * @return
     */
    public abstract int getMethod();

    /**
     * request url of api
     *
     * @return
     */
    public abstract String getUrl();

    /**
     * param's type ， such as raw , form-data or DIY
     *
     * @return ParamType
     */
    public ParamType getParamType() {
        return ParamType.formData;
    }


    public String getFinalUrl() {

        if (getMethod() == Request.Method.GET) {

            String param = "?";

            Set<String> keySet = getParams().keySet();

            for (String key : keySet) {
                param += key + "=" + getParams().get(key) + "&";
            }

            return getUrl() + param;
        }

        return getUrl();
    }


    /**
     * 获取Body
     *
     * @return
     */
    public byte[] getBody() {

        Map<String, String> params = getParams();

        switch (getParamType()) {
            //如果是以raw方式传参,默认为json
            case raw:

                //把表单参数转为body
                return NetUtils.encodeParametersToJson(params, getParamsEncoding());

            //以表单方式传参
            case formData:

                //把表单参数转为body
                return NetUtils.encodeParameters(params, getParamsEncoding());

            case DIY:

                return getDiyBody();
        }

        return null;
    }


    /**
     * 自定义body
     */
    private byte[] diyBody = null;

    /**
     * 自定义body的content-type
     */
    private String diyBodyContentType;

    public byte[] getDiyBody() {
        return diyBody;
    }

    public void setDiyBody(byte[] diyBody) {
        this.diyBody = diyBody;
    }

    public String getDiyBodyContentType() {
        return diyBodyContentType;
    }

    public void setDiyBodyContentType(String diyBodyContentType) {
        this.diyBodyContentType = diyBodyContentType;
    }


    /**
     * 请求成功
     *
     * @param data
     * @param msg
     */
    public abstract void requestSuccess(JSONObject data, String msg);

    /**
     * 请求失败
     *
     * @param code
     * @param msg
     */
    public abstract void requestFailure(Long code, String msg);


}
