package com.huice.core.system.service.impl;

import com.huice.database.entity.RoleFunction;
import com.huice.core.system.dao.RoleFunctionDao;
import com.huice.core.system.service.RoleFunctionService;
import com.huice.common.service.EnhanceServiceImpl;
import org.springframework.stereotype.Service;
import com.huice.common.enums.ResultCode;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 *
 *  服务实现类
 * @Classname RoleFunctionServiceImpl
 * @author Li Xiaobing
 * @date 2021/01/15 13:58
 */
@Service
public class RoleFunctionServiceImpl extends EnhanceServiceImpl<RoleFunctionDao, RoleFunction> implements RoleFunctionService {

    @Override
    public ResultCode add(RoleFunction roleFunction){
        baseMapper.insert(roleFunction);
        return ResultCode.SERVICE_OK;
    }

    @Override
    public void delete(Long id){
        baseMapper.deleteById(id);
    }

    @Override
    public ResultCode updateData(RoleFunction roleFunction){
        baseMapper.updateById(roleFunction);
        return ResultCode.SERVICE_OK;
    }

    @Override
    public RoleFunction findById(Long id){
        return baseMapper.selectById(id);
    }
}
