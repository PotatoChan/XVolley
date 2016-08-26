package com.chenjiarun.xvolley.xvolleyexample;


import com.chenjiarun.xvolley.net.BasicAPI;

import org.json.JSONObject;


/**
 * 作用：
 * 创建者：陈佳润
 * 创建日期：16/3/23
 * 更新历史：
 */
public abstract class LSAPI extends BasicAPI {

    public LSAPI() {
        addHeader("apiToken", "213123DFSF#234SFF");
        addHeader("platform", "21");
    }

    @Override
    public void requestSuccess(JSONObject data, String msg) {

        try {
            success(data, msg);

        } catch (Exception e) {
            e.printStackTrace();
            error(-1L, "数据解析出错");
        }
    }

    @Override
    public void requestFailure(Long code, String msg) {
        error(code, msg);
    }


    public abstract void success(JSONObject data, String msg) throws Exception;

    public abstract void error(Long code, String msg);

}
