package ${package.Controller};

import com.huice.common.util.JsonBean;
import com.huice.common.enums.ResultCode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 *
 * ${table.comment!} 前端控制器
 * @Classname ${table.controllerName}
 * @author ${author}
 * @since ${cfg.datetime}
 */
<#if restControllerStyle>
@Api(tags = {"${table.comment!}"})
@RestController
<#else>
@Controller
</#if>@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>class ${table.controllerName}<#if superControllerClass??>:${superControllerClass}()</#if><#else><#if
superControllerClass??>public class ${table.controllerName} extends ${superControllerClass}{<#else>public class
    ${table.controllerName} extends BaseController{</#if>

    private Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private ${table.serviceName} ${table.serviceName?uncap_first};

    @ApiOperation(value = "查询${table.comment!}分页数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "currentPage", value = "页码"),
        @ApiImplicitParam(name = "pageRows", value = "每页条数")
    })
    @PostMapping("query")
    public JsonBean findListByPage(@RequestParam Integer currentPage,@RequestParam Integer pageRows){
        IPage<${entity}> result = ${table.serviceName?uncap_first}.findListByPage(currentPage, pageRows);
        return JsonBean.returnResponse(result.getRecords());
    }

    @ApiOperation(value = "新增${table.comment!}")
    @PostMapping("create")
    public JsonBean add(@RequestBody ${entity} ${entity?uncap_first}){
        ResultCode resultCode = ${table.serviceName?uncap_first}.add(${entity?uncap_first});
        return jsonBeanBuilder.build(resultCode);
    }

    @ApiOperation(value = "删除${table.comment!}")
    @GetMapping("delete")
    public JsonBean delete(@RequestParam("id") Long id){
        ${table.serviceName?uncap_first}.delete(id);
        return jsonBeanBuilder.success();
    }

    @ApiOperation(value = "更新${table.comment!}")
    @PostMapping("update")
    public JsonBean update(@RequestBody ${entity} ${entity?uncap_first}){
        ResultCode resultCode = ${table.serviceName?uncap_first}.updateData(${entity?uncap_first});
        return jsonBeanBuilder.build(resultCode);
    }

    @ApiOperation(value = "id查询${table.comment!}")
    @GetMapping("detail")
    public JsonBean findById(@RequestParam("id") Long id){
        ${entity} ${entity?uncap_first}= ${table.serviceName?uncap_first}.findById(id);
        return JsonBean.returnResponse(${entity?uncap_first});
    }

}
</#if>