package com.chenjiarun.xvolley.xvolleyexample;

import com.android.volley.Request;
import com.chenjiarun.xvolley.net.ParamType;
import com.chenjiarun.xvolley.net.XVolleyMessage;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by Ives on 16/8/24.
 */
public class ImageAPI extends LSAPI {


    public ImageAPI(List<String> pathList) {

        XVolleyMessage msg = new XVolleyMessage();


        for (int i = 0; i < pathList.size(); i++) {
            msg.addText("type", 8);
            msg.addFile("image", pathList.get(i));
        }

        try {

            setDiyBody(msg.getMessage());

            setDiyBodyContentType(msg.getBodyContentType());

            new LSHttp(this).request();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public ParamType getParamType() {
        return ParamType.DIY;
    }

    @Override
    public void success(JSONObject data, String msg) throws Exception {

    }

    @Override
    public void error(Long code, String msg) {

    }

    @Override
    public int getMethod() {
        return Request.Method.POST;
    }

    @Override
    public String getUrl() {
        return "请填写实际请求的地址";
    }


}


