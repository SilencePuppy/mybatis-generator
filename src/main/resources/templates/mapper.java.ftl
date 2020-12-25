package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};

/**
 *
 * $!{table.comment} Mapper 接口
 * @classname ${table.mapperName}
 * @author ${author}
 * @since ${cfg.datetime}
 */
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
    <#if cfg.needCheckRepeated>

        int count${cfg.checkRepeatedField?cap_first}(Long id,String ${cfg.checkRepeatedField});
    </#if>
}