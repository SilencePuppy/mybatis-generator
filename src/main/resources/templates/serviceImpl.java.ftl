package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import com.huice.common.util.JsonBean;
import com.huice.common.enums.ResultCode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 *
 * ${table.comment!} 服务实现类
 * @classname ${table.serviceImplName}
 * @author ${author}
 * @date ${cfg.datetime}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Override
    public IPage<${entity}> findListByPage(Integer currentPage, Integer pageRows){
        IPage<${entity}> page = new Page<>(currentPage,pageRows,false);
        page = baseMapper.selectPage(page, Wrappers.<${entity}>emptyWrapper());
        return page;
    }

    @Override
    public ResultCode add(${entity} ${entity?uncap_first}){
        <#if cfg.needCheckRepeated>
        <#assign entityCamelName = entity?uncap_first>
        boolean repeated = is${cfg.checkRepeatedField?cap_first}Repeated(${entityCamelName}.getId(),${entityCamelName}.get${cfg.checkRepeatedField?cap_first}());
        if(repeated){
            return ResultCode.NAME_REPEATED;
        }
        </#if>
        baseMapper.insert(${entity?uncap_first});
        return ResultCode.SERVICE_OK;
    }

    @Override
    public void delete(Long id){
        baseMapper.deleteById(id);
    }

    @Override
    public ResultCode updateData(${entity} ${entity?uncap_first}){
        <#if cfg.needCheckRepeated>
            <#assign entityCamelName = entity?uncap_first>
            boolean repeated = is${cfg.checkRepeatedField?cap_first}Repeated(${entityCamelName}.getId(),${entityCamelName}.get${cfg.checkRepeatedField?cap_first}());
            if(repeated){
            return ResultCode.NAME_REPEATED;
            }
        </#if>
        baseMapper.updateById(${entity?uncap_first});
        return ResultCode.SERVICE_OK;
    }

    @Override
    public ${entity} findById(Long id){
        return baseMapper.selectById(id);
    }

    <#if cfg.needCheckRepeated>
    @Override
    public boolean is${cfg.checkRepeatedField?cap_first}Repeated(Long id,String ${cfg.checkRepeatedField}){
        int num = baseMapper.count${cfg.checkRepeatedField?cap_first}(id,${cfg.checkRepeatedField});
        return num != 0;
    }
    </#if>
}
</#if>
