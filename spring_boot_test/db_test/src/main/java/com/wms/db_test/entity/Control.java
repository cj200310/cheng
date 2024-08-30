package com.wms.db_test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2024-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Control对象", description="")
public class Control implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "commandid", type = IdType.AUTO)
    private Integer commandid;

    private Integer deviceid;

    private String commandtype;

    private String commandvalue;

    private LocalDateTime executiontime;

    private String executionstatus;


}
