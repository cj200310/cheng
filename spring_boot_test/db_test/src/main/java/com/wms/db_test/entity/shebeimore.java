package com.wms.db_test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="shebeimore对象", description="")
public class shebeimore implements Serializable  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "shebeiId", type = IdType.AUTO)
    private Integer shebeiid;

    private String shebeiname;

    private String type;

    private String state;

    private Integer dapengid;

    private String dapengname;

    private String commandtype;
    private String commandvalue;
    private String executionstatus;
    private java.time.LocalDateTime executiontime;
}
