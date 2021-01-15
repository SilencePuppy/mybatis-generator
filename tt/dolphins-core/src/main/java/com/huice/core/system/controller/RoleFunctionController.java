package com.huice.core.system.controller;

import com.huice.common.util.JsonBean;
import com.huice.common.enums.ResultCode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.huice.core.system.service.RoleFunctionService;
import com.huice.database.entity.RoleFunction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *  前端控制器
 * @Classname RoleFunctionController
 * @author Li Xiaobing
 * @since 2021/01/15 13:58
 */
@Api(tags = {""})
@RestController
@RequestMapping("/role-function")
public class
    RoleFunctionController extends BaseController{

    private Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private RoleFunctionService roleFunctionService;

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "currentPage", value = "页码"),
        @ApiImplicitParam(name = "pageRows", value = "每页条数")
    })
    @PostMapping("query")
    public JsonBean findListByPage(@RequestParam Integer currentPage,@RequestParam Integer pageRows){
        IPage<RoleFunction> result = roleFunctionService.findListByPage(currentPage, pageRows);
        return JsonBean.returnResponse(result.getRecords());
    }

    @ApiOperation(value = "新增")
    @PostMapping("create")
    public JsonBean add(@RequestBody RoleFunction roleFunction){
        ResultCode resultCode = roleFunctionService.add(roleFunction);
        return JsonBean.returnResponse(ResultCode.SERVICE_OK.equals(resultCode), resultCode);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("delete/{id}")
    public JsonBean delete(@PathVariable("id") Long id){
        roleFunctionService.delete(id);
        return JsonBean.returnResponse();
    }

    @ApiOperation(value = "更新")
    @PatchMapping("update")
    public JsonBean update(@RequestBody RoleFunction roleFunction){
        ResultCode resultCode = roleFunctionService.updateData(roleFunction);
        return JsonBean.returnResponse(ResultCode.SERVICE_OK.equals(resultCode), resultCode);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("info/{id}")
    public JsonBean findById(@PathVariable Long id){
        RoleFunction roleFunction= roleFunctionService.findById(id);
        return JsonBean.returnResponse(roleFunction);
    }

}
