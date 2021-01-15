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
 * 操作表
 * @Classname Operation
 * @author Li Xiaobing
 * @since 2021/01/12 14:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("s_operation")
@ApiModel(value="Operation对象", description="操作表")
public class Operation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "操作名称")
    private String name;

    @ApiModelProperty(value = "操作方法名称")
    private String abbr;

    @ApiModelProperty(value = "基本权限、批量权限等")
    private Integer type;

    private Integer seq;

    @ApiModelProperty(value = "0:列表 1:列表上  2:详情")
    private Integer position;

    @ApiModelProperty(value = "按钮图片")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer sort;


}
