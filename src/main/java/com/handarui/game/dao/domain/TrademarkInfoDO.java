package com.handarui.game.dao.domain;

import javax.persistence.*;

@Table(name = "trademark_info")
public class TrademarkInfoDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商标名称
     */
    private String name;

    /**
     * 类别 ：1-45
     */
    private Integer category;

    /**
     * 0文字1 图案 2复合
     */
    private Integer type;

    /**
     * 受理/注册号
     */
    @Column(name = "accept_registration_number")
    private String acceptRegistrationNumber;

    /**
     * 申请日
     */
    @Column(name = "apply_time")
    private Long applyTime;

    /**
     * 状态 ：0 对接、1提交申请、2受理、3注册、4驳回、5部分驳回、6复审、7撤三、8异议、9初审公告、10注册公告、11等待实质审查
     */
    private Integer status;

    /**
     * 注册日
     */
    @Column(name = "registration_time")
    private Long registrationTime;

    /**
     * 有效期
     */
    @Column(name = "validity_period")
    private Long validityPeriod;

    /**
     * 申请人
     */
    private String applicant;

    /**
     * 商品/服务列表
     */
    @Column(name = "product_service_list")
    private String productServiceList;

    /**
     * 提请部门
     */
    @Column(name = "draw_department")
    private String drawDepartment;

    /**
     * 代理所
     */
    private String agency;

    /**
     * 到期提醒（时间）
     */
    @Column(name = "expiration_reminder_time")
    private Long expirationReminderTime;

    /**
     * 资助领取情况
     */
    @Column(name = "funding_receipt")
    private String fundingReceipt;

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
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取商标名称
     *
     * @return categoryName - 商标名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商标名称
     *
     * @param name 商标名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取类别 ：1-45
     *
     * @return category - 类别 ：1-45
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * 设置类别 ：1-45
     *
     * @param category 类别 ：1-45
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * 获取0文字1 图案 2复合
     *
     * @return type - 0文字1 图案 2复合
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置0文字1 图案 2复合
     *
     * @param type 0文字1 图案 2复合
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取受理/注册号
     *
     * @return accept_registration_number - 受理/注册号
     */
    public String getAcceptRegistrationNumber() {
        return acceptRegistrationNumber;
    }

    /**
     * 设置受理/注册号
     *
     * @param acceptRegistrationNumber 受理/注册号
     */
    public void setAcceptRegistrationNumber(String acceptRegistrationNumber) {
        this.acceptRegistrationNumber = acceptRegistrationNumber == null ? null : acceptRegistrationNumber.trim();
    }

    /**
     * 获取申请日
     *
     * @return apply_time - 申请日
     */
    public Long getApplyTime() {
        return applyTime;
    }

    /**
     * 设置申请日
     *
     * @param applyTime 申请日
     */
    public void setApplyTime(Long applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * 获取状态 ：0 对接、1提交申请、2受理、3注册、4驳回、5部分驳回、6复审、7撤三、8异议、9初审公告、10注册公告、11等待实质审查
     *
     * @return status - 状态 ：0 对接、1提交申请、2受理、3注册、4驳回、5部分驳回、6复审、7撤三、8异议、9初审公告、10注册公告、11等待实质审查
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 ：0 对接、1提交申请、2受理、3注册、4驳回、5部分驳回、6复审、7撤三、8异议、9初审公告、10注册公告、11等待实质审查
     *
     * @param status 状态 ：0 对接、1提交申请、2受理、3注册、4驳回、5部分驳回、6复审、7撤三、8异议、9初审公告、10注册公告、11等待实质审查
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取注册日
     *
     * @return registration_time - 注册日
     */
    public Long getRegistrationTime() {
        return registrationTime;
    }

    /**
     * 设置注册日
     *
     * @param registrationTime 注册日
     */
    public void setRegistrationTime(Long registrationTime) {
        this.registrationTime = registrationTime;
    }

    /**
     * 获取有效期
     *
     * @return validity_period - 有效期
     */
    public Long getValidityPeriod() {
        return validityPeriod;
    }

    /**
     * 设置有效期
     *
     * @param validityPeriod 有效期
     */
    public void setValidityPeriod(Long validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    /**
     * 获取申请人
     *
     * @return applicant - 申请人
     */
    public String getApplicant() {
        return applicant;
    }

    /**
     * 设置申请人
     *
     * @param applicant 申请人
     */
    public void setApplicant(String applicant) {
        this.applicant = applicant == null ? null : applicant.trim();
    }

    /**
     * 获取商品/服务列表
     *
     * @return product_service_list - 商品/服务列表
     */
    public String getProductServiceList() {
        return productServiceList;
    }

    /**
     * 设置商品/服务列表
     *
     * @param productServiceList 商品/服务列表
     */
    public void setProductServiceList(String productServiceList) {
        this.productServiceList = productServiceList == null ? null : productServiceList.trim();
    }

    /**
     * 获取提请部门
     *
     * @return draw_department - 提请部门
     */
    public String getDrawDepartment() {
        return drawDepartment;
    }

    /**
     * 设置提请部门
     *
     * @param drawDepartment 提请部门
     */
    public void setDrawDepartment(String drawDepartment) {
        this.drawDepartment = drawDepartment == null ? null : drawDepartment.trim();
    }

    /**
     * @return agency
     */
    public String getAgency() {
        return agency;
    }

    /**
     * @param agency
     */
    public void setAgency(String agency) {
        this.agency = agency == null ? null : agency.trim();
    }

    /**
     * 获取到期提醒（时间）
     *
     * @return expiration_reminder_time - 到期提醒（时间）
     */
    public Long getExpirationReminderTime() {
        return expirationReminderTime;
    }

    /**
     * 设置到期提醒（时间）
     *
     * @param expirationReminderTime 到期提醒（时间）
     */
    public void setExpirationReminderTime(Long expirationReminderTime) {
        this.expirationReminderTime = expirationReminderTime;
    }

    /**
     * 获取资助领取情况
     *
     * @return funding_receipt - 资助领取情况
     */
    public String getFundingReceipt() {
        return fundingReceipt;
    }

    /**
     * 设置资助领取情况
     *
     * @param fundingReceipt 资助领取情况
     */
    public void setFundingReceipt(String fundingReceipt) {
        this.fundingReceipt = fundingReceipt == null ? null : fundingReceipt.trim();
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