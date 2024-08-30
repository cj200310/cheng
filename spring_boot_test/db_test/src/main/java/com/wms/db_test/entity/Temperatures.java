package com.wms.db_test.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author db_test
 * @since 2024-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Temperatures对象", description="")
public class Temperatures implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("temperatureC")
    private Double temperaturec;

    @TableField("humidityPercent")
    private Double humiditypercent;


}
