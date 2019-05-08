package com.handarui.game.dao.domain;

import javax.persistence.*;

@Table(name = "game")
public class GameDo {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 游戏名称
     */
    private String name;

    /**
     * 出版单位
     */
    @Column(name = "publish_unit")
    private String publishUnit;

    /**
     * 著作权单位
     */
    @Column(name = "copyright_unit")
    private String copyrightUnit;

    /**
     * 运营单位
     */
    @Column(name = "operation_unit")
    private String operationUnit;

    /**
     * 游戏分类0:消除类1:跑酷类2:飞行类3:棋牌类4:解谜类5:体育类6:音乐舞蹈类7:捕鱼类8:涂色类9:弹射类10:放置类11:挖掘类12:简单搭建类13:非简易游戏
     */
    private Integer sort;

    /**
     * 存档编号
     */
    @Column(name = "archive_number")
    private String archiveNumber;

    /**
     * 备案号
     */
    @Column(name = "record_number")
    private String recordNumber;

    /**
     * 软件著作权编号
     */
    @Column(name = "copyright_number")
    private String copyrightNumber;

    /**
     * 提请人
     */
    @Column(name = "submit_user_name")
    private String submitUserName;

    /**
     * 提请部门
     */
    @Column(name = "submit_department")
    private String submitDepartment;

    /**
     * 提请人邮箱
     */
    @Column(name = "submit_user_email")
    private String submitUserEmail;

    /**
     * 提请人手机号
     */
    @Column(name = "submit_user_phone")
    private String submitUserPhone;

    /**
     * 游戏类型0:进口游戏1内部游戏2外部游戏
     */
    private Integer type;

    /**
     * 出版费
     */
    @Column(name = "publish_fee")
    private Double publishFee;

    /**
     * 批文号
     */
    @Column(name = "approval_number")
    private String approvalNumber;

    /**
     * ISBN号
     */
    @Column(name = "isbn_number")
    private String isbnNumber;

    /**
     * 进度0:软著申请1:软著完成2:合同批复3:内部审包4:提交省局5:请示文件6:总局受理7:答复意见8:出文9:退回
     */
    private Integer process;

    /**
     * 批文时间
     */
    @Column(name = "approval_time")
    private Long approvalTime;

    /**
     * 受理时间
     */
    @Column(name = "accept_time")
    private Long acceptTime;

    /**
     * 备注
     */
    private String note;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Long createdAt;

    /**
     * 修改时间
     */
    @Column(name = "updated_at")
    private Long updatedAt;

    /**
     * 是否删除0:否1:是
     */
    @Column(name = "is_deleted")
    private Integer isDeleted;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取游戏名称
     *
     * @return categoryName - 游戏名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置游戏名称
     *
     * @param name 游戏名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取出版单位
     *
     * @return publish_unit - 出版单位
     */
    public String getPublishUnit() {
        return publishUnit;
    }

    /**
     * 设置出版单位
     *
     * @param publishUnit 出版单位
     */
    public void setPublishUnit(String publishUnit) {
        this.publishUnit = publishUnit == null ? null : publishUnit.trim();
    }

    /**
     * 获取著作权单位
     *
     * @return copyright_unit - 著作权单位
     */
    public String getCopyrightUnit() {
        return copyrightUnit;
    }

    /**
     * 设置著作权单位
     *
     * @param copyrightUnit 著作权单位
     */
    public void setCopyrightUnit(String copyrightUnit) {
        this.copyrightUnit = copyrightUnit == null ? null : copyrightUnit.trim();
    }

    /**
     * 获取运营单位
     *
     * @return operation_unit - 运营单位
     */
    public String getOperationUnit() {
        return operationUnit;
    }

    /**
     * 设置运营单位
     *
     * @param operationUnit 运营单位
     */
    public void setOperationUnit(String operationUnit) {
        this.operationUnit = operationUnit == null ? null : operationUnit.trim();
    }

    /**
     * 获取游戏分类0:消除类1:跑酷类2:飞行类3:棋牌类4:解谜类5:体育类6:音乐舞蹈类7:捕鱼类8:涂色类9:弹射类10:放置类11:挖掘类12:简单搭建类13:非简易游戏
     *
     * @return categoryName - 游戏分类0:消除类1:跑酷类2:飞行类3:棋牌类4:解谜类5:体育类6:音乐舞蹈类7:捕鱼类8:涂色类9:弹射类10:放置类11:挖掘类12:简单搭建类13:非简易游戏
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置游戏分类0:消除类1:跑酷类2:飞行类3:棋牌类4:解谜类5:体育类6:音乐舞蹈类7:捕鱼类8:涂色类9:弹射类10:放置类11:挖掘类12:简单搭建类13:非简易游戏
     *
     * @param sort 游戏分类0:消除类1:跑酷类2:飞行类3:棋牌类4:解谜类5:体育类6:音乐舞蹈类7:捕鱼类8:涂色类9:弹射类10:放置类11:挖掘类12:简单搭建类13:非简易游戏
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取存档编号
     *
     * @return archive_number - 存档编号
     */
    public String getArchiveNumber() {
        return archiveNumber;
    }

    /**
     * 设置存档编号
     *
     * @param archiveNumber 存档编号
     */
    public void setArchiveNumber(String archiveNumber) {
        this.archiveNumber = archiveNumber == null ? null : archiveNumber.trim();
    }

    /**
     * 获取备案号
     *
     * @return record_number - 备案号
     */
    public String getRecordNumber() {
        return recordNumber;
    }

    /**
     * 设置备案号
     *
     * @param recordNumber 备案号
     */
    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber == null ? null : recordNumber.trim();
    }

    /**
     * 获取软件著作权编号
     *
     * @return copyright_number - 软件著作权编号
     */
    public String getCopyrightNumber() {
        return copyrightNumber;
    }

    /**
     * 设置软件著作权编号
     *
     * @param copyrightNumber 软件著作权编号
     */
    public void setCopyrightNumber(String copyrightNumber) {
        this.copyrightNumber = copyrightNumber == null ? null : copyrightNumber.trim();
    }

    /**
     * 获取提请人
     *
     * @return submit_user_name - 提请人
     */
    public String getSubmitUserName() {
        return submitUserName;
    }

    /**
     * 设置提请人
     *
     * @param submitUserName 提请人
     */
    public void setSubmitUserName(String submitUserName) {
        this.submitUserName = submitUserName == null ? null : submitUserName.trim();
    }

    /**
     * 获取提请部门
     *
     * @return submit_department - 提请部门
     */
    public String getSubmitDepartment() {
        return submitDepartment;
    }

    /**
     * 设置提请部门
     *
     * @param submitDepartment 提请部门
     */
    public void setSubmitDepartment(String submitDepartment) {
        this.submitDepartment = submitDepartment == null ? null : submitDepartment.trim();
    }

    /**
     * 获取提请人邮箱
     *
     * @return submit_user_email - 提请人邮箱
     */
    public String getSubmitUserEmail() {
        return submitUserEmail;
    }

    /**
     * 设置提请人邮箱
     *
     * @param submitUserEmail 提请人邮箱
     */
    public void setSubmitUserEmail(String submitUserEmail) {
        this.submitUserEmail = submitUserEmail == null ? null : submitUserEmail.trim();
    }

    /**
     * 获取提请人手机号
     *
     * @return submit_user_phone - 提请人手机号
     */
    public String getSubmitUserPhone() {
        return submitUserPhone;
    }

    /**
     * 设置提请人手机号
     *
     * @param submitUserPhone 提请人手机号
     */
    public void setSubmitUserPhone(String submitUserPhone) {
        this.submitUserPhone = submitUserPhone == null ? null : submitUserPhone.trim();
    }

    /**
     * 获取游戏类型0:进口游戏1内部游戏2外部游戏
     *
     * @return type - 游戏类型0:进口游戏1内部游戏2外部游戏
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置游戏类型0:进口游戏1内部游戏2外部游戏
     *
     * @param type 游戏类型0:进口游戏1内部游戏2外部游戏
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取出版费
     *
     * @return publish_fee - 出版费
     */
    public Double getPublishFee() {
        return publishFee;
    }

    /**
     * 设置出版费
     *
     * @param publishFee 出版费
     */
    public void setPublishFee(Double publishFee) {
        this.publishFee = publishFee;
    }

    /**
     * 获取批文号
     *
     * @return approval_number - 批文号
     */
    public String getApprovalNumber() {
        return approvalNumber;
    }

    /**
     * 设置批文号
     *
     * @param approvalNumber 批文号
     */
    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber == null ? null : approvalNumber.trim();
    }

    /**
     * 获取ISBN号
     *
     * @return isbn_number - ISBN号
     */
    public String getIsbnNumber() {
        return isbnNumber;
    }

    /**
     * 设置ISBN号
     *
     * @param isbnNumber ISBN号
     */
    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber == null ? null : isbnNumber.trim();
    }

    /**
     * 获取进度0:软著申请1:软著完成2:合同批复3:内部审包4:提交省局5:请示文件6:总局受理7:答复意见8:出文9:退回
     *
     * @return process - 进度0:软著申请1:软著完成2:合同批复3:内部审包4:提交省局5:请示文件6:总局受理7:答复意见8:出文9:退回
     */
    public Integer getProcess() {
        return process;
    }

    /**
     * 设置进度0:软著申请1:软著完成2:合同批复3:内部审包4:提交省局5:请示文件6:总局受理7:答复意见8:出文9:退回
     *
     * @param process 进度0:软著申请1:软著完成2:合同批复3:内部审包4:提交省局5:请示文件6:总局受理7:答复意见8:出文9:退回
     */
    public void setProcess(Integer process) {
        this.process = process;
    }

    /**
     * 获取批文时间
     *
     * @return approval_time - 批文时间
     */
    public Long getApprovalTime() {
        return approvalTime;
    }

    /**
     * 设置批文时间
     *
     * @param approvalTime 批文时间
     */
    public void setApprovalTime(Long approvalTime) {
        this.approvalTime = approvalTime;
    }

    /**
     * 获取受理时间
     *
     * @return accept_time - 受理时间
     */
    public Long getAcceptTime() {
        return acceptTime;
    }

    /**
     * 设置受理时间
     *
     * @param acceptTime 受理时间
     */
    public void setAcceptTime(Long acceptTime) {
        this.acceptTime = acceptTime;
    }

    /**
     * 获取备注
     *
     * @return note - 备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置备注
     *
     * @param note 备注
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    /**
     * 获取创建时间
     *
     * @return created_at - 创建时间
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置创建时间
     *
     * @param createdAt 创建时间
     */
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取修改时间
     *
     * @return updated_at - 修改时间
     */
    public Long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 设置修改时间
     *
     * @param updatedAt 修改时间
     */
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 获取是否删除0:否1:是
     *
     * @return is_deleted - 是否删除0:否1:是
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除0:否1:是
     *
     * @param isDeleted 是否删除0:否1:是
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}