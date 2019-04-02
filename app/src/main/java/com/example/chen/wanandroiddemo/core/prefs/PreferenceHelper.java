package com.example.chen.wanandroiddemo.core.prefs;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/16 10:51
 */
public interface PreferenceHelper {
    /**
     * 设置登录状态
     *
     * @param status
     */
    void setLoginStatus(boolean status);

    /**
     * 获取登录状态
     *
     * @return
     */
    boolean getLoginStatus();

    /**
     * 设置登录账号
     *
     * @param account
     */
    void setLoginAccount(String account);

    /**
     * 获取登录账号
     *
     * @return
     */
    String getLoginAccount();

    /**
     * 设置登录密码
     *
     * @param password
     */
    void setLoginPassword(String password);

    /**
     * 获取登录密码
     *
     * @return
     */
    String getLoginPassword();


}
