package com.wms.db_test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="Shebei对象", description="")
public class Shebei implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "shebeiId", type = IdType.AUTO)
    private Integer shebeiid;

    private String shebeiname;

    private String type;

    private String state;

    private Integer dpid;

}
