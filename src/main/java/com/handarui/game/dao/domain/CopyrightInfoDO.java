package com.handarui.game.dao.domain;

import javax.persistence.*;

@Table(name = "copyright_info")
public class CopyrightInfoDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 著作权名称
     */
    private String name;

    /**
     * 编号
     */
    private String number;

    /**
     * 类型: 0,作品著作权 1,软件著作权
     */
    private Integer type;

    /**
     * 申请人
     */
    private String applicant;

    /**
     * 提请人
     */
    private String drawer;

    /**
     * 提请部门
     */
    @Column(name = "draw_department")
    private String drawDepartment;

    /**
     * 备注
     */
    private String note;

    /**
     * 分类 0 软件、1动画、2小说、3图案、4其他
     */
    private Integer category;

    /**
     * 状态(提请、申请中、已完成、其他，其他可自行填写内容）
     */
    private String status;

    /**
     * 0：未变更 1：变更
     */
    @Column(name = "is_changed")
    private Boolean isChanged;

    /**
     * 登记号
     */
    @Column(name = "register_number")
    private String registerNumber;

    /**
     * 作品完成时间
     */
    @Column(name = "finished_time")
    private Long finishedTime;

    /**
     * 登记时间
     */
    @Column(name = "register_time")
    private Long registerTime;

    /**
     * 款项及发票
     */
    @Column(name = "payment_invoice")
    private String paymentInvoice;

    /**
     * 作者
     */
    private String author;

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
     * 获取著作权名称
     *
     * @return categoryName - 著作权名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置著作权名称
     *
     * @param name 著作权名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取编号
     *
     * @return number - 编号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置编号
     *
     * @param number 编号
     */
    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    /**
     * 获取类型: 0,作品著作权 1,软件著作权
     *
     * @return type - 类型: 0,作品著作权 1,软件著作权
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型: 0,作品著作权 1,软件著作权
     *
     * @param type 类型: 0,作品著作权 1,软件著作权
     */
    public void setType(Integer type) {
        this.type = type;
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
     * 获取提请人
     *
     * @return drawer - 提请人
     */
    public String getDrawer() {
        return drawer;
    }

    /**
     * 设置提请人
     *
     * @param drawer 提请人
     */
    public void setDrawer(String drawer) {
        this.drawer = drawer == null ? null : drawer.trim();
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
     * 获取分类 0 软件、1动画、2小说、3图案、4其他
     *
     * @return category - 分类 0 软件、1动画、2小说、3图案、4其他
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * 设置分类 0 软件、1动画、2小说、3图案、4其他
     *
     * @param category 分类 0 软件、1动画、2小说、3图案、4其他
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * 获取状态(提请、申请中、已完成、其他，其他可自行填写内容）
     *
     * @return status - 状态(提请、申请中、已完成、其他，其他可自行填写内容）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态(提请、申请中、已完成、其他，其他可自行填写内容）
     *
     * @param status 状态(提请、申请中、已完成、其他，其他可自行填写内容）
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取0：未变更 1：变更
     *
     * @return is_changed - 0：未变更 1：变更
     */
    public Boolean getIsChanged() {
        return isChanged;
    }

    /**
     * 设置0：未变更 1：变更
     *
     * @param isChanged 0：未变更 1：变更
     */
    public void setIsChanged(Boolean isChanged) {
        this.isChanged = isChanged;
    }

    /**
     * 获取登记号
     *
     * @return register_number - 登记号
     */
    public String getRegisterNumber() {
        return registerNumber;
    }

    /**
     * 设置登记号
     *
     * @param registerNumber 登记号
     */
    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber == null ? null : registerNumber.trim();
    }

    /**
     * 获取作品完成时间
     *
     * @return finished_time - 作品完成时间
     */
    public Long getFinishedTime() {
        return finishedTime;
    }

    /**
     * 设置作品完成时间
     *
     * @param finishedTime 作品完成时间
     */
    public void setFinishedTime(Long finishedTime) {
        this.finishedTime = finishedTime;
    }

    /**
     * 获取登记时间
     *
     * @return register_time - 登记时间
     */
    public Long getRegisterTime() {
        return registerTime;
    }

    /**
     * 设置登记时间
     *
     * @param registerTime 登记时间
     */
    public void setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取款项及发票
     *
     * @return payment_invoice - 款项及发票
     */
    public String getPaymentInvoice() {
        return paymentInvoice;
    }

    /**
     * 设置款项及发票
     *
     * @param paymentInvoice 款项及发票
     */
    public void setPaymentInvoice(String paymentInvoice) {
        this.paymentInvoice = paymentInvoice == null ? null : paymentInvoice.trim();
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
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