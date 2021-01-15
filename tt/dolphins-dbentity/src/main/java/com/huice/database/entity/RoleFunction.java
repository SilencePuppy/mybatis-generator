package com.huice.database.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.huice.database.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * 
 * @Classname RoleFunction
 * @author Li Xiaobing
 * @since 2021/01/15 13:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("s_role_function")
@ApiModel(value="RoleFunction对象", description="")
public class RoleFunction extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "角色id")
    private Long roleId;

    @ApiModelProperty(value = "模块id")
    private Long functionId;

    @ApiModelProperty(value = "操作id集合")
    private String operationIds;


}
