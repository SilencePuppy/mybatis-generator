package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

import com.huice.common.enums.ResultCode;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *
 * ${table.comment!} 服务类
 * @classname ${table.serviceName}
 * @author ${author}
 * @date ${cfg.datetime}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     *
     * 添加${table.comment!}
     * @param ${entity?uncap_first} ${table.comment!}
     * @return ResultCode
     * @author ${author}
     * @date ${cfg.datetime}
     */
    ResultCode add(${entity} ${entity?uncap_first});

    /**
     * 删除${table.comment!}
     *
     * @param id 主键
     * @return void
     */
    void delete(Long id);

    /**
     *
     * 修改${table.comment!}
     * @param ${entity?uncap_first} ${table.comment!}
     * @return ResultCode
     * @author ${author}
     * @date ${cfg.datetime}
     */
    ResultCode updateData(${entity} ${entity?uncap_first});

    /**
     *
     * id查询数据
     * @param id id
     * @return ${entity}
     * @author ${author}
     * @date ${cfg.datetime}
     */
    ${entity} findById(Long id);
}
</#if>
