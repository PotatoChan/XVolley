package com.chenjiarun.xvolley.xvolleyexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chenjiarun.android.tools.util.sdutils.SDCardNotExistException;
import com.chenjiarun.android.tools.util.sdutils.SDUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            String path = SDUtils.getSDCardPath() + "/DCIM/Camera/123.jpg";

            List<String> pathList = new ArrayList<String>();

            pathList.add(path);

            new ImageAPI(pathList);

        } catch (SDCardNotExistException e) {
            e.printStackTrace();
        }


    }


}
