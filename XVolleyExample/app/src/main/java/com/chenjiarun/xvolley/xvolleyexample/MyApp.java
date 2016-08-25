package com.chenjiarun.xvolley.xvolleyexample;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.chenjiarun.android.tools.app.App;


/**
 * Created by Potato on 16/3/19.
 */
public class MyApp extends App {

    public static MyApp app;

    public static MyApp getApp() {
        if (app == null) {
            synchronized (App.class) {
                if (app == null) {
                    app = new MyApp();
                }
            }
        }
        return app;
    }

    /**
     * 处理网络请求的消息队列
     */
    public static RequestQueue queue;

    public static RequestQueue getQueue() {
        if (queue == null) {
            synchronized (RequestQueue.class) {
                if (queue == null) {
                    //初始化Volley请求队列
                    queue = Volley.newRequestQueue(context);
                }
            }
        }
        return queue;
    }


    @Override
    public void onCreate() {

        super.onCreate();
    }


}
