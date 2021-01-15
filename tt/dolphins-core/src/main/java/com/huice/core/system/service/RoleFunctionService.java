package com.huice.core.system.service;

import com.huice.database.entity.RoleFunction;
import com.huice.common.service.EnhanceIService;

import com.huice.common.enums.ResultCode;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *
 *  服务类
 * @Classname RoleFunctionService
 * @author Li Xiaobing
 * @date 2021/01/15 13:58
 */
public interface RoleFunctionService extends EnhanceIService<RoleFunction> {

    /**
     *
     * 添加
     * @param roleFunction 
     * @return ResultCode
     * @author Li Xiaobing
     * @date 2021/01/15 13:58
     */
    ResultCode add(RoleFunction roleFunction);

    /**
     * 删除
     *
     * @param id 主键
     * @return void
     */
    void delete(Long id);

    /**
     *
     * 修改
     * @param roleFunction 
     * @return ResultCode
     * @author Li Xiaobing
     * @date 2021/01/15 13:58
     */
    ResultCode updateData(RoleFunction roleFunction);

    /**
     *
     * id查询数据
     * @param id id
     * @return RoleFunction
     * @author Li Xiaobing
     * @date 2021/01/15 13:58
     */
    RoleFunction findById(Long id);
}
