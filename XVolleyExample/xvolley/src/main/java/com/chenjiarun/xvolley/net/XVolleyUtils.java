package com.chenjiarun.xvolley.net;

import com.chenjiarun.android.tools.util.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * 功能：提供了一个随机生成Http报文分隔符的方法: generateBoundary
 * -------------------------------------------------------------------------------------------------
 * 创建者：陈佳润
 * -------------------------------------------------------------------------------------------------
 * 创建日期：16/8/25
 * -------------------------------------------------------------------------------------------------
 * 更新历史(日期/更新人/更新内容)
 */
public class XVolleyUtils {

    /**
     * 字符集,用于随机生成Http报文分隔符
     */
    private final static char[] MULTIPART_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
            .toCharArray();

    /**
     * 生成Http报文的分隔符
     *
     * @return
     */
    public static String generateBoundary() {
        final StringBuffer buf = new StringBuffer();
        final Random rand = new Random();
        for (int i = 0; i < 30; i++) {
            buf.append(MULTIPART_CHARS[rand.nextInt(MULTIPART_CHARS.length)]);
        }
        return buf.toString();
    }

    /**
     * 获取报文参数的第一行
     *
     * @param boundary
     * @return
     */
    public static byte[] generateBeginLine(String boundary) {
        return ("--" + boundary + XVolleyConstant.NEW_LINE_STR).getBytes();
    }

    /**
     * 获取报文参数结束行
     *
     * @param boundary
     * @return
     */
    public static byte[] generateEndLine(String boundary) {
        return ("--" + boundary + "--" + XVolleyConstant.NEW_LINE_STR).getBytes();
    }

    /**
     * 为Text型参数生成 Content-Disposition
     *
     * @param key
     * @return
     */
    public static byte[] generateContentDispositionForText(String key) {

        String line = "Content-Disposition: form-data; name=\"" + key + "\"\r\n";

        return line.getBytes();
    }

    /**
     * 为文件型参数生成 Content-Disposition
     *
     * @param key
     * @param fileName
     * @return
     */
    public static byte[] generateContentDispositionForFile(String key, String fileName) {

        String line = "Content-Disposition: form-data; name=\"" + key + "\"; filename=\"" + fileName + "\"\r\n";

        System.out.println("XVolley:" + line);

        return line.getBytes();

    }

    /**
     * 把文件写到输出流中
     *
     * @param file 文件对象
     * @param bos  输出流
     */
    public static void writeFileToOutputStream(File file, ByteArrayOutputStream bos) {

        InputStream is = null;

        try {
            is = new FileInputStream(file);

            final byte[] tmp = new byte[4096];

            int len = 0;

            while ((len = is.read(tmp)) != -1) {

                bos.write(tmp, 0, len);

            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            IOUtils.close(is);
        }

    }


}
