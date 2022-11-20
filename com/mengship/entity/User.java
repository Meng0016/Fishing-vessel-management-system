package com.mengship.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("ID")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("用户名")
      private String username;

      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("真实姓名")
      private String realname;

      @ApiModelProperty("邮箱")
      private String email;

      @ApiModelProperty("使用状态")
      private Integer status;

      @ApiModelProperty("通过状态")
      private Integer access;

      @ApiModelProperty("登录次数")
      private Integer loginCount;

      @ApiModelProperty("最后登录")
      private Integer lastVisit;

      @ApiModelProperty("创建日期")
      private LocalDateTime dateCreated;

      @ApiModelProperty("Smtp")
      private String smtp;

      @ApiModelProperty("SSL")
      private Boolean ssl;

      @ApiModelProperty("港口")
      private String port;

      @ApiModelProperty("电话")
      private String phone;

      @ApiModelProperty("是否删除")
      private Boolean isDelete;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getUsername() {
        return username;
    }

      public void setUsername(String username) {
          this.username = username;
      }
    
    public String getPassword() {
        return password;
    }

      public void setPassword(String password) {
          this.password = password;
      }
    
    public String getRealname() {
        return realname;
    }

      public void setRealname(String realname) {
          this.realname = realname;
      }
    
    public String getEmail() {
        return email;
    }

      public void setEmail(String email) {
          this.email = email;
      }
    
    public Integer getStatus() {
        return status;
    }

      public void setStatus(Integer status) {
          this.status = status;
      }
    
    public Integer getAccess() {
        return access;
    }

      public void setAccess(Integer access) {
          this.access = access;
      }
    
    public Integer getLoginCount() {
        return loginCount;
    }

      public void setLoginCount(Integer loginCount) {
          this.loginCount = loginCount;
      }
    
    public Integer getLastVisit() {
        return lastVisit;
    }

      public void setLastVisit(Integer lastVisit) {
          this.lastVisit = lastVisit;
      }
    
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

      public void setDateCreated(LocalDateTime dateCreated) {
          this.dateCreated = dateCreated;
      }
    
    public String getSmtp() {
        return smtp;
    }

      public void setSmtp(String smtp) {
          this.smtp = smtp;
      }
    
    public Boolean getSsl() {
        return ssl;
    }

      public void setSsl(Boolean ssl) {
          this.ssl = ssl;
      }
    
    public String getPort() {
        return port;
    }

      public void setPort(String port) {
          this.port = port;
      }
    
    public String getPhone() {
        return phone;
    }

      public void setPhone(String phone) {
          this.phone = phone;
      }
    
    public Boolean getIsDelete() {
        return isDelete;
    }

      public void setIsDelete(Boolean isDelete) {
          this.isDelete = isDelete;
      }

    @Override
    public String toString() {
        return "User{" +
              "id=" + id +
                  ", username=" + username +
                  ", password=" + password +
                  ", realname=" + realname +
                  ", email=" + email +
                  ", status=" + status +
                  ", access=" + access +
                  ", loginCount=" + loginCount +
                  ", lastVisit=" + lastVisit +
                  ", dateCreated=" + dateCreated +
                  ", smtp=" + smtp +
                  ", ssl=" + ssl +
                  ", port=" + port +
                  ", phone=" + phone +
                  ", isDelete=" + isDelete +
              "}";
    }
}
