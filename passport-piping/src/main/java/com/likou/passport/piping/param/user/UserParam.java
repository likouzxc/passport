package com.likou.passport.piping.param.user;

/**
 * Created by jiangli on 16/9/20.
 */
public class UserParam {

    /**
     * 用户id
     */
    private String id;
    /**
     * 用户名称，可作为登录名称，由英文和数字组成 
     */
    private String userName;
    /**
     * 用户昵称 
     */
    private String nickName;
    /**
     * 用户email，可作为登录使用 
     */
    private String email;
    /**
     * 用户手机号，可作为登录使用，纯数字 
     */
    private String mobile;
    /**
     * 用户登录密码，RSA加密 
     */
    private String password;
    /**
     * 用户来源 
     */
    private int source;
    /**
     * 来源标记 
     */
    private String flag;
    /**
     * 用户类型，0:用户（客户） ，1:员工 
     */
    private boolean type;
    /**
     * 用户创建时间 
     */
    private long createTime;
    /**
     * 用户信息修改时间 
     */
    private long updateTime;
    /**
     * 用户最后登录时间 
     */
    private long lastLogin;


    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
