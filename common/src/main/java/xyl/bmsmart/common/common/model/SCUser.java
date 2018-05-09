package xyl.bmsmart.common.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @param
 * @author XiaYaLing
 * @date 2018/4/23
 * @return
 */
public class SCUser implements Serializable {

    private static final long serialVersionUID = -1667300831610353589L;

    /**
     * @Field @userId : TODO(用户ID)
     */
    private String userId;
    /**
     * @Field @loginUser : TODO(登录名)
     */
    private String loginName;
    /**
     * @Field @password : TODO(登录密码，保存时使用MD5加密)
     */
    private String password;
    /**
     * @Field @name : TODO(用户名)
     */
    private String name;
    /**
     * @Field @email : TODO(用户邮箱)
     */
    private String email;
    /**
     * @Field @mobile : TODO(用户手机)
     */
    private String mobile;

    /**
     * 用户头像url
     */
    private String headImg;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String delFlag;

    /**
     * 业务拓展
     * "0" 代表业务创建者
     * "1" 代表本人
     * "2" 代表其它人
     * "3" 代表既是本人也是创建者
     */
    private String type = "2";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SCUser{" +
                "userId='" + userId + '\'' +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", headImg='" + headImg + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", delFlag='" + delFlag + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
