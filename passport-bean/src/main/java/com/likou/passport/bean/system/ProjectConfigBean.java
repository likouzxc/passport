package com.likou.passport.bean.system;

/**
 * Created by jiangli on 16/10/26.
 */
public class ProjectConfigBean {

    /**
     * 项目id
     */
    private String id;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 项目domain
     */
    private String domain;
    /**
     * 是否启用，false:禁用，true:启用
     */
    private boolean status;
    /**
     * 创建时间
     */
    private long createTime;
    /**
     * 项目描述
     */
    private String description;


    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
