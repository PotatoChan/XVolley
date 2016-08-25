package com.chenjiarun.xvolley.net;

import com.chenjiarun.android.tools.util.CastUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 功能：自定义报文实体类
 * -------------------------------------------------------------------------------------------------
 * 创建者：陈佳润
 * -------------------------------------------------------------------------------------------------
 * 创建日期：16/8/25
 * -------------------------------------------------------------------------------------------------
 * 更新历史(日期/更新人/更新内容)
 */
public class XVolleyMessage {

    private String boundary;

    private ByteArrayOutputStream bos;


    private byte[] beginLine; //报文参数首行

    private byte[] endLine; //报文参数结束行


    public XVolleyMessage() {

        //生成分隔符
        this.boundary = XVolleyUtils.generateBoundary();

        //实例化输出流
        bos = new ByteArrayOutputStream();

        //生成首行
        this.beginLine = XVolleyUtils.generateBeginLine(this.boundary);

        //生成尾行
        this.endLine = XVolleyUtils.generateEndLine(this.boundary);

    }


    /**
     * 添加 Text 参数
     *
     * @param key
     * @param value
     */
    public void addText(String key, Object value) {

        try {

            bos.write(beginLine);

            bos.write(XVolleyConstant.CONTENT_TYPE_TEXT_PLAIN);

            bos.write(XVolleyUtils.generateContentDispositionForText(key));

            bos.write(XVolleyConstant.ENCODING_BIT);

            bos.write(CastUtils.castString(value).getBytes());

            bos.write(XVolleyConstant.NEW_LINE_STR_BYTE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加 File型参数
     *
     * @param key  参数名
     * @param path 文件路径
     */
    public void addFile(String key, String path) {
        File file = new File(path);
        addFile(key, file, file.getName());
    }

    /**
     * 添加File型参数
     *
     * @param key  参数名
     * @param file 文件对象
     */
    public void addFile(String key, File file) {
        addFile(key, file, file.getName());
    }


    /**
     * 添加 File型参数
     *
     * @param key      参数名
     * @param path     文件路径
     * @param fileName 文件名
     */
    public void addFile(String key, String path, String fileName) {
        File file = new File(path);
        addFile(key, file, fileName);
    }

    /**
     * 添加File型参数
     *
     * @param key      参数名
     * @param file     文件对象
     * @param fileName 文件名
     */
    public void addFile(String key, File file, String fileName) {

        try {
            bos.write(beginLine);

            bos.write(XVolleyConstant.CONTENT_TYPE_OCTET_STREAM);

            bos.write(XVolleyUtils.generateContentDispositionForFile(key, fileName));

            bos.write(XVolleyConstant.ENCODING_BINARY);

            XVolleyUtils.writeFileToOutputStream(file, bos);

            bos.write(XVolleyConstant.NEW_LINE_STR_BYTE);
            bos.write(XVolleyConstant.NEW_LINE_STR_BYTE);

            bos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMessage(final OutputStream os) throws IOException {

        os.write(bos.toByteArray());
        os.write(endLine);
    }

    public byte[] getMessage() throws IOException {

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        getMessage(os);
        return os.toByteArray();
    }


    public String getBodyContentType() {

        return "multipart/form-data" + "; boundary=" + boundary;

    }


    public String getBoundary() {
        return boundary;
    }
}
