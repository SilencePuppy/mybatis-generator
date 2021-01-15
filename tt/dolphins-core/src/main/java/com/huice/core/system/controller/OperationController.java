package com.huice.core.system.controller;

import com.huice.common.util.JsonBean;
import com.huice.common.enums.ResultCode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import com.huice.core.system.service.OperationService;
import com.huice.database.entity.Operation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 操作表 前端控制器
 * @Classname OperationController
 * @author Li Xiaobing
 * @since 2021/01/12 14:06
 */
@Api(tags = {"操作表"})
@RestController
@RequestMapping("/operation")
public class OperationController {

    private Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    private OperationService operationService;

    @ApiOperation(value = "查询操作表分页数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "currentPage", value = "页码"),
        @ApiImplicitParam(name = "pageRows", value = "每页条数")
    })
    @PostMapping("query")
    public JsonBean findListByPage(@RequestParam Integer currentPage,@RequestParam Integer pageRows){
        IPage<Operation> result = operationService.findListByPage(currentPage, pageRows);
        return JsonBean.returnResponse(result.getRecords());
    }

    @ApiOperation(value = "新增操作表")
    @PostMapping("create")
    public JsonBean add(@RequestBody Operation operation){
        ResultCode resultCode = operationService.add(operation);
        return JsonBean.returnResponse(ResultCode.SERVICE_OK.equals(resultCode), resultCode);
    }

    @ApiOperation(value = "删除操作表")
    @DeleteMapping("delete/{id}")
    public JsonBean delete(@PathVariable("id") Long id){
        operationService.delete(id);
        return JsonBean.returnResponse();
    }

    @ApiOperation(value = "更新操作表")
    @PatchMapping("update")
    public JsonBean update(@RequestBody Operation operation){
        ResultCode resultCode = operationService.updateData(operation);
        return JsonBean.returnResponse(ResultCode.SERVICE_OK.equals(resultCode), resultCode);
    }

    @ApiOperation(value = "id查询操作表")
    @GetMapping("info/{id}")
    public JsonBean findById(@PathVariable Long id){
        Operation operation= operationService.findById(id);
        return JsonBean.returnResponse(operation);
    }

}
