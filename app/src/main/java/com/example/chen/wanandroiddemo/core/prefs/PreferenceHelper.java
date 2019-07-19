package com.example.chen.wanandroiddemo.core.prefs;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/16 10:51
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

    /**
     * 设置夜间模式
     *
     * @param mode
     */
    void setNightMode(boolean mode);

    /**
     * 获取夜间模式
     *
     * @return
     */
    boolean getNightMode();

    /**
     * 设置网络状态
     *
     * @param state
     */
    void setNetState(String state);

    /**
     * 获取网络状态
     *
     * @return
     */
    String getNetState();

}
