package com.chenjiarun.xvolley.xvolleyexample;

import com.chenjiarun.xvolley.net.StatusPair;

/**
 * 作用：
 * 创建者：陈佳润
 * 创建日期：16/3/23
 * 更新历史：
 */
public class APIStatus {


    /**
     * 等于
     *
     * @param code
     * @param statusPair
     * @return
     */
    public static boolean equal(Long code, StatusPair statusPair) {

        if (statusPair.getCode() == code) {
            return true;
        }
        return false;
    }


    /**
     * 请求成功
     */
    public static final StatusPair SUCCESS = new StatusPair(200L, "请求成功");

    public static final StatusPair ACCESS_TOKEN_EXPIRED = new StatusPair(801, "access_token过期");
    public static final StatusPair REFRESH_TOKEN_EXPIRED = new StatusPair(802, "refresh_token过期");

    public static final StatusPair TOKEN_EXPIRED = new StatusPair(702, "访问令牌为空");

    public static final StatusPair ACCESS_TOKEN_EMPTY = new StatusPair(701, "access_token为空");
    public static final StatusPair REFRESH_TOKEN_EMPTY = new StatusPair(701, "refresh_token为空");

    public static final StatusPair JSON_ERROR = new StatusPair(-1, "数据解析出错，请检查请求数据");

    public static final StatusPair NO_TOKEN_LOGIN = new StatusPair(-2, "");


    /**
     * 通用提示码
     */
    public static long TIP = 604;

    /**
     * API Token错误
     */
    public static long AUTHORIZATION__ERROR = 600;
    public static String AUTHORIZATION_ERROR_INFO = "未知错误，请下载最新版本或联系客服";

    /**
     * 返回Response为空
     */
    public static long EMPTY_RETURN = 601;
    public static String EMPTY_RETURN_INFO = "Respone is null";


    /**
     * 服务器繁忙
     */
    public static long SERVER_ERROR = 666;
    public static String SERVER_ERROR_INFO = "服务繁忙，请稍后再试";


}
