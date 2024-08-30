package com.wms.db_test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author db_test
 * @since 2024-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Dapengname对象", description="")
public class Dapengname implements Serializable {


    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "dapengid", type = IdType.AUTO)
    private Integer dapengid;

    @TableField("dapengName")
    private String dapengname;

    private String seat;

    private String area;

    private String status;


}
