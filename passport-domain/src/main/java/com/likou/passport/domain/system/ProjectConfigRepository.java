package com.likou.passport.domain.system;

import com.likou.passport.bean.system.ProjectConfigBean;
import com.likou.passport.dao.master.system.ProjectConfigMasterDAO;
import com.likou.passport.dao.master.user.UserMasterDAO;
import com.likou.passport.dao.slave.system.ProjectConfigSlaveDAO;
import com.likou.passport.dao.slave.user.UserSlaveDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiangli on 16/10/26.
 */
@Service
public class ProjectConfigRepository {


    @Autowired
    ProjectConfigMasterDAO projectConfigMasterDAO;
    @Autowired
    ProjectConfigSlaveDAO projectConfigSlaveDAO;


    protected ProjectConfigBean initByID(String id){
        return projectConfigSlaveDAO.getByID(id);
    }
    protected ProjectConfigBean initByName(String name){
        return projectConfigSlaveDAO.getByName(name);
    }
    protected ProjectConfigBean initByDomain(String domain){
        return projectConfigSlaveDAO.getByDomain(domain);
    }

}
