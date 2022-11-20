package com.mengship.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author meng
 * @since 2022-11-18
 */
@ApiModel(value = "Ship对象", description = "")
public class Ship implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("ID")
        @TableId(value = "ship_id", type = IdType.AUTO)
      private Integer shipId;

      @ApiModelProperty("船只名称")
      private String shipName;

      @ApiModelProperty("船只编号")
      private String shipCode;

      @ApiModelProperty("船籍港")
      private String shipPort;

      @ApiModelProperty("船只类型")
      private String shipType;

      @ApiModelProperty("船只宽度")
      private BigDecimal shipWidth;

      @ApiModelProperty("船只长度")
      private BigDecimal shipLength;

      @ApiModelProperty("型深")
      private BigDecimal typeDeep;

      @ApiModelProperty("总吨数")
      private Double totalTons;

      @ApiModelProperty("主机型号")
      private String hostModel;

      @ApiModelProperty("主机数量")
      private Double hostMun;

      @ApiModelProperty("主机编号")
      private String hostId;

      @ApiModelProperty("主机功率")
      private BigDecimal hostPower;

      @ApiModelProperty("建造厂商")
      private String shipFactory;

      @ApiModelProperty("检验登记号")
      private String shipRegNum;

      @ApiModelProperty("船只材质")
      private String shipPhysical;

      @ApiModelProperty("核定航区")
      private String shipArea;

      @ApiModelProperty("完工日期")
      private LocalDate endDate;

      @ApiModelProperty("救生设备")
      private String surEquip;

      @ApiModelProperty("消防设备")
      private String firEquip;

      @ApiModelProperty("其他设备")
      private String othEquip;

      @ApiModelProperty("渔船检验证书编号")
      private String insId;

      @ApiModelProperty("国籍证书编号")
      private String natId;

      @ApiModelProperty("所有权登记证书编号")
      private String ownRgsid;

      @ApiModelProperty("所有权取得日期")
      private LocalDate ownDate;

      @ApiModelProperty("渔业捕捞证许可号")
      private Integer licenseId;

      @ApiModelProperty("渔具规格")
      private String fishGg;

      @ApiModelProperty("渔具数量")
      private Integer fishNum;

      @ApiModelProperty("作业类型")
      private String workType;

      @ApiModelProperty("作业场所")
      private String workSpace;

      @ApiModelProperty("所有人姓名")
      private String ownName;

      @ApiModelProperty("所有人股份")
      private String ownShares;

      @ApiModelProperty("所有人地址")
      private String ownAddress;

      @ApiModelProperty("所有人ID")
      private String ownId;

      @ApiModelProperty("所有人联系电话")
      private String ownPhone;

      @ApiModelProperty("身份证")
      private String mutalInfor;

      @ApiModelProperty("船只状态")
      private Integer shipState;

      @ApiModelProperty("文件")
      private String files;

      @ApiModelProperty("注册人ID")
      private Integer reUserId;

      @ApiModelProperty("注册时间")
      private LocalDateTime regDate;

      @ApiModelProperty("申请时间")
      private LocalDate aprovDate;

      @ApiModelProperty("文件状态(0/1)")
      private Integer fileFlag;

      @ApiModelProperty("证书颁发日期")
      private LocalDate certDate;

      @ApiModelProperty("有效期")
      private LocalDate valid;

    
    public Integer getShipId() {
        return shipId;
    }

      public void setShipId(Integer shipId) {
          this.shipId = shipId;
      }
    
    public String getShipName() {
        return shipName;
    }

      public void setShipName(String shipName) {
          this.shipName = shipName;
      }
    
    public String getShipCode() {
        return shipCode;
    }

      public void setShipCode(String shipCode) {
          this.shipCode = shipCode;
      }
    
    public String getShipPort() {
        return shipPort;
    }

      public void setShipPort(String shipPort) {
          this.shipPort = shipPort;
      }
    
    public String getShipType() {
        return shipType;
    }

      public void setShipType(String shipType) {
          this.shipType = shipType;
      }
    
    public BigDecimal getShipWidth() {
        return shipWidth;
    }

      public void setShipWidth(BigDecimal shipWidth) {
          this.shipWidth = shipWidth;
      }
    
    public BigDecimal getShipLength() {
        return shipLength;
    }

      public void setShipLength(BigDecimal shipLength) {
          this.shipLength = shipLength;
      }
    
    public BigDecimal getTypeDeep() {
        return typeDeep;
    }

      public void setTypeDeep(BigDecimal typeDeep) {
          this.typeDeep = typeDeep;
      }
    
    public Double getTotalTons() {
        return totalTons;
    }

      public void setTotalTons(Double totalTons) {
          this.totalTons = totalTons;
      }
    
    public String getHostModel() {
        return hostModel;
    }

      public void setHostModel(String hostModel) {
          this.hostModel = hostModel;
      }
    
    public Double getHostMun() {
        return hostMun;
    }

      public void setHostMun(Double hostMun) {
          this.hostMun = hostMun;
      }
    
    public String getHostId() {
        return hostId;
    }

      public void setHostId(String hostId) {
          this.hostId = hostId;
      }
    
    public BigDecimal getHostPower() {
        return hostPower;
    }

      public void setHostPower(BigDecimal hostPower) {
          this.hostPower = hostPower;
      }
    
    public String getShipFactory() {
        return shipFactory;
    }

      public void setShipFactory(String shipFactory) {
          this.shipFactory = shipFactory;
      }
    
    public String getShipRegNum() {
        return shipRegNum;
    }

      public void setShipRegNum(String shipRegNum) {
          this.shipRegNum = shipRegNum;
      }
    
    public String getShipPhysical() {
        return shipPhysical;
    }

      public void setShipPhysical(String shipPhysical) {
          this.shipPhysical = shipPhysical;
      }
    
    public String getShipArea() {
        return shipArea;
    }

      public void setShipArea(String shipArea) {
          this.shipArea = shipArea;
      }
    
    public LocalDate getEndDate() {
        return endDate;
    }

      public void setEndDate(LocalDate endDate) {
          this.endDate = endDate;
      }
    
    public String getSurEquip() {
        return surEquip;
    }

      public void setSurEquip(String surEquip) {
          this.surEquip = surEquip;
      }
    
    public String getFirEquip() {
        return firEquip;
    }

      public void setFirEquip(String firEquip) {
          this.firEquip = firEquip;
      }
    
    public String getOthEquip() {
        return othEquip;
    }

      public void setOthEquip(String othEquip) {
          this.othEquip = othEquip;
      }
    
    public String getInsId() {
        return insId;
    }

      public void setInsId(String insId) {
          this.insId = insId;
      }
    
    public String getNatId() {
        return natId;
    }

      public void setNatId(String natId) {
          this.natId = natId;
      }
    
    public String getOwnRgsid() {
        return ownRgsid;
    }

      public void setOwnRgsid(String ownRgsid) {
          this.ownRgsid = ownRgsid;
      }
    
    public LocalDate getOwnDate() {
        return ownDate;
    }

      public void setOwnDate(LocalDate ownDate) {
          this.ownDate = ownDate;
      }
    
    public Integer getLicenseId() {
        return licenseId;
    }

      public void setLicenseId(Integer licenseId) {
          this.licenseId = licenseId;
      }
    
    public String getFishGg() {
        return fishGg;
    }

      public void setFishGg(String fishGg) {
          this.fishGg = fishGg;
      }
    
    public Integer getFishNum() {
        return fishNum;
    }

      public void setFishNum(Integer fishNum) {
          this.fishNum = fishNum;
      }
    
    public String getWorkType() {
        return workType;
    }

      public void setWorkType(String workType) {
          this.workType = workType;
      }
    
    public String getWorkSpace() {
        return workSpace;
    }

      public void setWorkSpace(String workSpace) {
          this.workSpace = workSpace;
      }
    
    public String getOwnName() {
        return ownName;
    }

      public void setOwnName(String ownName) {
          this.ownName = ownName;
      }
    
    public String getOwnShares() {
        return ownShares;
    }

      public void setOwnShares(String ownShares) {
          this.ownShares = ownShares;
      }
    
    public String getOwnAddress() {
        return ownAddress;
    }

      public void setOwnAddress(String ownAddress) {
          this.ownAddress = ownAddress;
      }
    
    public String getOwnId() {
        return ownId;
    }

      public void setOwnId(String ownId) {
          this.ownId = ownId;
      }
    
    public String getOwnPhone() {
        return ownPhone;
    }

      public void setOwnPhone(String ownPhone) {
          this.ownPhone = ownPhone;
      }
    
    public String getMutalInfor() {
        return mutalInfor;
    }

      public void setMutalInfor(String mutalInfor) {
          this.mutalInfor = mutalInfor;
      }
    
    public Integer getShipState() {
        return shipState;
    }

      public void setShipState(Integer shipState) {
          this.shipState = shipState;
      }
    
    public String getFiles() {
        return files;
    }

      public void setFiles(String files) {
          this.files = files;
      }
    
    public Integer getReUserId() {
        return reUserId;
    }

      public void setReUserId(Integer reUserId) {
          this.reUserId = reUserId;
      }
    
    public LocalDateTime getRegDate() {
        return regDate;
    }

      public void setRegDate(LocalDateTime regDate) {
          this.regDate = regDate;
      }
    
    public LocalDate getAprovDate() {
        return aprovDate;
    }

      public void setAprovDate(LocalDate aprovDate) {
          this.aprovDate = aprovDate;
      }
    
    public Integer getFileFlag() {
        return fileFlag;
    }

      public void setFileFlag(Integer fileFlag) {
          this.fileFlag = fileFlag;
      }
    
    public LocalDate getCertDate() {
        return certDate;
    }

      public void setCertDate(LocalDate certDate) {
          this.certDate = certDate;
      }
    
    public LocalDate getValid() {
        return valid;
    }

      public void setValid(LocalDate valid) {
          this.valid = valid;
      }

    @Override
    public String toString() {
        return "Ship{" +
              "shipId=" + shipId +
                  ", shipName=" + shipName +
                  ", shipCode=" + shipCode +
                  ", shipPort=" + shipPort +
                  ", shipType=" + shipType +
                  ", shipWidth=" + shipWidth +
                  ", shipLength=" + shipLength +
                  ", typeDeep=" + typeDeep +
                  ", totalTons=" + totalTons +
                  ", hostModel=" + hostModel +
                  ", hostMun=" + hostMun +
                  ", hostId=" + hostId +
                  ", hostPower=" + hostPower +
                  ", shipFactory=" + shipFactory +
                  ", shipRegNum=" + shipRegNum +
                  ", shipPhysical=" + shipPhysical +
                  ", shipArea=" + shipArea +
                  ", endDate=" + endDate +
                  ", surEquip=" + surEquip +
                  ", firEquip=" + firEquip +
                  ", othEquip=" + othEquip +
                  ", insId=" + insId +
                  ", natId=" + natId +
                  ", ownRgsid=" + ownRgsid +
                  ", ownDate=" + ownDate +
                  ", licenseId=" + licenseId +
                  ", fishGg=" + fishGg +
                  ", fishNum=" + fishNum +
                  ", workType=" + workType +
                  ", workSpace=" + workSpace +
                  ", ownName=" + ownName +
                  ", ownShares=" + ownShares +
                  ", ownAddress=" + ownAddress +
                  ", ownId=" + ownId +
                  ", ownPhone=" + ownPhone +
                  ", mutalInfor=" + mutalInfor +
                  ", shipState=" + shipState +
                  ", files=" + files +
                  ", reUserId=" + reUserId +
                  ", regDate=" + regDate +
                  ", aprovDate=" + aprovDate +
                  ", fileFlag=" + fileFlag +
                  ", certDate=" + certDate +
                  ", valid=" + valid +
              "}";
    }
}
