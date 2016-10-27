package com.likou.passport.dao.slave.system;

import com.likou.passport.bean.system.ProjectConfigBean;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangli on 16/10/26.
 */
@Repository
public interface ProjectConfigSlaveDAO {

    /**
     * 通过id获取数据
     * @param id
     * @return
     */
    public ProjectConfigBean getByID(String id);
    /**
     * 通过name获取数据
     * @param name
     * @return
     */
    public ProjectConfigBean getByName(String name);
    /**
     * 通过domain获取数据
     * @param domain
     * @return
     */
    public ProjectConfigBean getByDomain(String domain);
}
