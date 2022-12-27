package com.mengship.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description 船只信息
 * @Author meng
 * @Data 2022/10/30 12:26
 */
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "ship")
@ApiModel(value = "Ship对象", description = "")
public class Ship implements Serializable {

    private static final long serialVersionUID = 1L;

    @Alias("船只ID")
    @ApiModelProperty("ID")
    @TableId(value = "ship_id", type = IdType.AUTO)
    private Integer shipId;

    @Alias("船只名称")
    @ApiModelProperty("船只名称")
    private String shipName;

    @Alias("船只编号")
    @ApiModelProperty("船只编号")
    private String shipCode;

    @Alias("船籍港")
    @ApiModelProperty("船籍港")
    private String shipPort;

    @Alias("船只类型")
    @ApiModelProperty("船只类型")
    private String shipType;

    @Alias("船只宽度")
    @ApiModelProperty("船只宽度")
    private BigDecimal shipWidth;

    @Alias("船只长度")
    @ApiModelProperty("船只长度")
    private BigDecimal shipLength;

    @Alias("型深")
    @ApiModelProperty("型深")
    private BigDecimal typeDeep;

    @Alias("总吨数")
    @ApiModelProperty("总吨数")
    private Double totalTons;

    @Alias("主机型号")
    @ApiModelProperty("主机型号")
    private String hostModel;

    @Alias("主机数量")
    @ApiModelProperty("主机数量")
    private Double hostNum;

    @Alias("主机编号")
    @ApiModelProperty("主机编号")
    private String hostId;

    @Alias("主机功率")
    @ApiModelProperty("主机功率")
    private BigDecimal hostPower;

    @Alias("建造厂商")
    @ApiModelProperty("建造厂商")
    private String shipFactory;

    @Alias("检验登记号")
    @ApiModelProperty("检验登记号")
    private String shipRegNum;

    @Alias("船只材质")
    @ApiModelProperty("船只材质")
    private String shipPhysical;

    @Alias("核定航区")
    @ApiModelProperty("核定航区")
    private String shipArea;

    @Alias("完工日期")
    @ApiModelProperty("完工日期")
    private LocalDate endDate;

    @Alias("救生设备")
    @ApiModelProperty("救生设备")
    private String surEquip;

    @Alias("消防设备")
    @ApiModelProperty("消防设备")
    private String firEquip;

    @Alias("其他设备")
    @ApiModelProperty("其他设备")
    private String othEquip;

    @Alias("渔船检验证书编号")
    @ApiModelProperty("渔船检验证书编号")
    private String insId;

    @Alias("国籍证书编号")
    @ApiModelProperty("国籍证书编号")
    private String natId;

    @Alias("所有权登记证书编号")
    @ApiModelProperty("所有权登记证书编号")
    private String ownRgsid;

    @Alias("所有权取得日期")
    @ApiModelProperty("所有权取得日期")
    private LocalDate ownDate;

    @Alias("渔业捕捞证许可号")
    @ApiModelProperty("渔业捕捞证许可号")
    private Integer licenseId;

    @Alias("渔具规格")
    @ApiModelProperty("渔具规格")
    private String fishGg;

    @Alias("渔具数量")
    @ApiModelProperty("渔具数量")
    private Integer fishNum;

    @Alias("作业类型")
    @ApiModelProperty("作业类型")
    private String workType;

    @Alias("作业场所")
    @ApiModelProperty("作业场所")
    private String workSpace;

    @Alias("所有人姓名")
    @ApiModelProperty("所有人姓名")
    private String ownName;

    @Alias("所有人股份")
    @ApiModelProperty("所有人股份")
    private String ownShares;

    @Alias("所有人地址")
    @ApiModelProperty("所有人地址")
    private String ownAddress;

    @Alias("所有人ID")
    @ApiModelProperty("所有人ID")
    private String ownId;

    @Alias("所有人联系电话")
    @ApiModelProperty("所有人联系电话")
    private String ownPhone;

    @Alias("身份证")
    @ApiModelProperty("身份证")
    private String mutalInfor;

    @Alias("船只状态")
    @ApiModelProperty("船只状态")
    private Integer shipState;

    @Alias("文件")
    @ApiModelProperty("文件")
    private String files;

    @Alias("注册人ID")
    @ApiModelProperty("注册人ID")
    private Integer reUserId;

    @Alias("注册时间")
    @ApiModelProperty("注册时间")
    private LocalDateTime regDate;

    @Alias("申请时间")
    @ApiModelProperty("申请时间")
    private LocalDate aprovDate;

    @Alias("文件状态")
    @ApiModelProperty("文件状态(0/1)")
    private Integer fileFlag;

    @Alias("证书颁发日期")
    @ApiModelProperty("证书颁发日期")
    private LocalDate certDate;

    @Alias("有效期")
    @ApiModelProperty("有效期")
    private LocalDate valid;

    @Alias("是否删除")
    @ApiModelProperty("逻辑删除")
    private Boolean isDelete;



}
