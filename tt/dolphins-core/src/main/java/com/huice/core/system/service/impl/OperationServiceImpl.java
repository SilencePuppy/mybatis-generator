package com.huice.core.system.service.impl;

import com.huice.database.entity.Operation;
import com.huice.core.system.dao.OperationDao;
import com.huice.core.system.service.OperationService;
import com.huice.common.service.EnhanceServiceImpl;
import org.springframework.stereotype.Service;
import com.huice.common.enums.ResultCode;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 *
 * 操作表 服务实现类
 * @Classname OperationServiceImpl
 * @author Li Xiaobing
 * @date 2021/01/12 14:06
 */
@Service
public class OperationServiceImpl extends EnhanceServiceImpl<OperationDao, Operation> implements OperationService {

    @Override
    public ResultCode add(Operation operation){
        baseMapper.insert(operation);
        return ResultCode.SERVICE_OK;
    }

    @Override
    public void delete(Long id){
        baseMapper.deleteById(id);
    }

    @Override
    public ResultCode updateData(Operation operation){
        baseMapper.updateById(operation);
        return ResultCode.SERVICE_OK;
    }

    @Override
    public Operation findById(Long id){
        return baseMapper.selectById(id);
    }
}
