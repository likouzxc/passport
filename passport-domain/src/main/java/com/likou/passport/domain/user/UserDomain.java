package com.likou.passport.domain.user;

import com.likou.common.character.IDGen;
import com.likou.passport.bean.user.UserBean;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by jiangli on 16/6/29.
 */
public class UserDomain {

    private UserRepository userRepository;

    public UserDomain(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    BeanCopier bean2domain = BeanCopier.create(UserBean.class,UserDomain.class,false);

    private final String passwordMD5 = "lijiang";

    /**
     * 通过id初始化domain
     * @param userRepository
     * @param id
     */
    public UserDomain(UserRepository userRepository , String id){
        this(userRepository);
        initUserByID();
    }

    /**
     * 根据email、username、mobile查询用户
     * @param userRepository
     * @param type 登录类型,0:使用email初始化,1:使用用户名初始化,2:使用手机号初始化,其他使用id初始化
     * @param value 数值
     */
    public UserDomain(UserRepository userRepository , int type , String value){
        this(userRepository);
        switch (type){
            case 0 :{
                setEmail(value);
                initUserByEmail();break;
            }
            case 1 :{
                setUserName(value);
                initUserByUserName();break;
            }
            case 2 :{
                setMobile(value);
                initUserByMobile();break;
            }
            default:initUserByID();
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public boolean regUser() throws Exception {
        UserBean bean = new UserBean();
        bean.setCreateTime(new Date().getTime()/1000);
        bean.setEmail(this.email);
        bean.setFlag(this.flag);
        bean.setId(IDGen.get32ID());
        bean.setLastLogin(this.lastLogin);
        bean.setMobile(this.mobile);
        bean.setNickName(this.nickName);
        bean.setPassword(getRSAPassword(this.password));
        bean.setSource(this.source);
        bean.setType(this.type);
        bean.setUpdateTime(this.updateTime);
        bean.setUserName(this.userName);

        int count = this.getUserRepository().regUser(bean);
        if(count>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkPassword(String password){
        if(StringUtils.isNotBlank(password)&&getRSAPassword(password).equals(this.getPassword())){
            return true;
        }else{
            return false;
        }
    }

    private String getRSAPassword(String password){
        return DigestUtils.md5Hex(password+passwordMD5);
    }
    private void initUserByID(){
        UserBean userBean = this.getUserRepository().initUserByID(this.getId());
        if(userBean == null ){
            this.setId(null);
        }else{
            bean2domain.copy(userBean,this,null);
        }
    }
    private void initUserByEmail(){
        UserBean userBean = this.getUserRepository().initUserByEmail(this.getEmail());
        if(userBean != null ){
            bean2domain.copy(userBean,this,null);
        }
    }
    private void initUserByUserName(){
        UserBean userBean = this.getUserRepository().initUserByUserName(this.getUserName());
        if(userBean != null ){
            bean2domain.copy(userBean,this,null);
        }
    }
    private void initUserByMobile(){
        UserBean userBean = this.getUserRepository().initUserByMobile(this.getMobile());
        if(userBean != null ){
            bean2domain.copy(userBean,this,null);
        }
    }


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

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
