package com.likou.passport.domain.system;

import com.likou.passport.bean.system.ProjectConfigBean;
import com.likou.passport.bean.user.UserBean;
import org.springframework.cglib.beans.BeanCopier;

/**
 * Created by jiangli on 16/10/26.
 */
public class ProjectConfigDomain {

    private ProjectConfigRepository projectConfigRepository;

    BeanCopier bean2domain = BeanCopier.create(ProjectConfigBean.class,ProjectConfigDomain.class,false);

    public ProjectConfigDomain(ProjectConfigRepository projectConfigRepository) {
        this.projectConfigRepository = projectConfigRepository;
    }

    /**
     * 构造器
     * @param type 1:以id进行初始化,2:以name进行初始化,3:以domain进行初始化,其他:返回空内容对象
     * @param content
     */
    ProjectConfigDomain(ProjectConfigRepository projectConfigRepository,int type,String content){
        this(projectConfigRepository);
        switch(type){
            case 1:{
                setId(content);
                initByID();
                break;
            }
            case 2:{
                setName(content);
                initByName();
                break;
            }
            case 3:{
                setDomain(content);
                initByDomain();
                break;
            }
        }
    }

    private void initByID(){

        ProjectConfigBean projectConfigBean = this.getProjectConfigRepository().initByID(this.getId());
        if(projectConfigBean == null ){
            this.setId(null);
        }else{
            bean2domain.copy(projectConfigBean,this,null);
        }
    }
    private void initByName(){
        ProjectConfigBean projectConfigBean = this.getProjectConfigRepository().initByName(this.getId());
        if(projectConfigBean == null ){
            this.setId(null);
        }else{
            bean2domain.copy(projectConfigBean,this,null);
        }
    }
    private void initByDomain(){
        ProjectConfigBean projectConfigBean = this.getProjectConfigRepository().initByDomain(this.getId());
        if(projectConfigBean == null ){
            this.setId(null);
        }else{
            bean2domain.copy(projectConfigBean,this,null);
        }
    }


    public ProjectConfigRepository getProjectConfigRepository() {
        return projectConfigRepository;
    }

    public void setProjectConfigRepository(ProjectConfigRepository projectConfigRepository) {
        this.projectConfigRepository = projectConfigRepository;
    }

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
