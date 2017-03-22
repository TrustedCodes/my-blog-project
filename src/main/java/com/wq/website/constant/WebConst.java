package com.wq.website.constant;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by BlueT on 2017/3/3.
 */
@Component
public class WebConst {
    /**
     * 存储安装信息的配置文件名称
     */
    public static final String INSTALL_FILE_CONF = "install.lock";
    public static final String USER_IN_COOKIE = "S_L_ID";
    /**
     * 最大获取文章条数
     */
    public static final int MAX_POSTS = 9999;
    /**
     * 最大页码
     */
    public static final int MAX_PAGE = 100;
    /**
     * 点击次数超过多少更新到数据库
     */
    public static final int HIT_EXCEED = 10;
    /**
     * 要过滤的ip列表
     */
    public static final Set<String> BLOCK_IPS = new HashSet<>(16);
    public static Map<String, String> initConfig = new HashMap<>();
    /**
     * 是否进行过安装
     */
    public static Boolean INSTALL = false;
    public static String LOGIN_SESSION_KEY = "login_user";
    /**
     * aes加密加盐
     */
    public static String AES_SALT = "0123456789abcdef";
    /**
     * 上传文件最大20M
     */
    public static Integer MAX_FILE_SIZE = 204800;
}
