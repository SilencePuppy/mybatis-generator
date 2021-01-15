package com.huice.core.system.service;

import com.huice.database.entity.Operation;
import com.huice.common.service.EnhanceIService;

import com.huice.common.enums.ResultCode;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *
 * 操作表 服务类
 * @Classname OperationService
 * @author Li Xiaobing
 * @date 2021/01/12 14:06
 */
public interface OperationService extends EnhanceIService<Operation> {

    /**
     *
     * 添加操作表
     * @param operation 操作表
     * @return ResultCode
     * @author Li Xiaobing
     * @date 2021/01/12 14:06
     */
    ResultCode add(Operation operation);

    /**
     * 删除操作表
     *
     * @param id 主键
     * @return void
     */
    void delete(Long id);

    /**
     *
     * 修改操作表
     * @param operation 操作表
     * @return ResultCode
     * @author Li Xiaobing
     * @date 2021/01/12 14:06
     */
    ResultCode updateData(Operation operation);

    /**
     *
     * id查询数据
     * @param id id
     * @return Operation
     * @author Li Xiaobing
     * @date 2021/01/12 14:06
     */
    Operation findById(Long id);
}
