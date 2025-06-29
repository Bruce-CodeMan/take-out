package com.brucecompiler.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressBook implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private String consignee;

    private String phone;

    private String sex;

    // 省级区划编号
    private String provinceCode;

    // 省级名称
    private String provinceName;

    // 市级区划编号
    private String cityCode;

    // 市级名称
    private String cityName;

    // 区级区划编号
    private String districtCode;

    private String districtName;

    // 详细地址
    private String detail;

    private String label;

    private Integer isDefault;
}
