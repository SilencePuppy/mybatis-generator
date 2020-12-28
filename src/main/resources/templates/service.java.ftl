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
     * 查询${table.comment!}分页数据
     * @param currentPage      页码
     * @param pageRows 每页条数
     * @return JsonBean
     * @author ${author}
     * @date ${cfg.datetime}
     */
    IPage<${entity}> findListByPage(Integer currentPage, Integer pageRows);

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

    <#if cfg.needCheckRepeated>
    /**
     * 判断${cfg.checkRepeatedField}是否已使用，id null 表示判断新增，否则判断修改
     * @param id
     * @param ${cfg.checkRepeatedField}
     * @return boolean true表示已经使用了，false未使用
     * @author ${author}
     * @date ${cfg.datetime}
     */
    boolean is${cfg.checkRepeatedField?cap_first}Repeated(Long id,String ${cfg.checkRepeatedField});
    </#if>
}
</#if>
