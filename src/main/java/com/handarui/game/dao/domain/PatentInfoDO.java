package com.handarui.game.dao.domain;

import javax.persistence.*;

@Table(name = "patent_info")
public class PatentInfoDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 专利名称
     */
    private String name;

    /**
     * 编号
     */
    private String number;

    /**
     * 类型： 0 发明、1 实用新型、2 外观
     */
    private Integer type;

    /**
     * 申请号
     */
    @Column(name = "apply_number")
    private String applyNumber;

    /**
     * 申请日
     */
    @Column(name = "apply_time")
    private Long applyTime;

    /**
     * 状态： 0 撰写交底书、1提交申请、2受理、3实审、4办登、5授权、6驳回、7复审、8异议、9无效、10初步审查、11授权公告、12 行政复议
     */
    private Integer status;

    /**
     * 发明设计人
     */
    @Column(name = "invention_designer")
    private String inventionDesigner;

    /**
     * 申请人
     */
    private String applicant;

    /**
     * 代理单位
     */
    @Column(name = "agent_unit")
    private String agentUnit;

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
     * 本年费缴纳情况 0，未缴  1，已缴
     */
    @Column(name = "current_year_payment")
    private Boolean currentYearPayment;

    /**
     * 当前年费年度
     */
    @Column(name = "current_annual_fee_year")
    private String currentAnnualFeeYear;

    /**
     * 下一年度年费缴纳时间
     */
    @Column(name = "next_year_annual_fee_payment_time")
    private Long nextYearAnnualFeePaymentTime;

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
     * 技术特征
     */
    @Column(name = "technical_feature")
    private String technicalFeature;

    /**
     * 公告日
     */
    @Column(name = "announcement_day")
    private Long announcementDay;

    /**
     * 公告号
     */
    @Column(name = "announcement_number")
    private String announcementNumber;

    /**
     * 奖金发放
     */
    @Column(name = "bonus_payment")
    private String bonusPayment;

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
     * 获取专利名称
     *
     * @return name - 专利名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置专利名称
     *
     * @param name 专利名称
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
     * 获取类型： 0 发明、1 实用新型、2 外观
     *
     * @return type - 类型： 0 发明、1 实用新型、2 外观
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型： 0 发明、1 实用新型、2 外观
     *
     * @param type 类型： 0 发明、1 实用新型、2 外观
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取申请号
     *
     * @return apply_number - 申请号
     */
    public String getApplyNumber() {
        return applyNumber;
    }

    /**
     * 设置申请号
     *
     * @param applyNumber 申请号
     */
    public void setApplyNumber(String applyNumber) {
        this.applyNumber = applyNumber == null ? null : applyNumber.trim();
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
     * 获取状态： 0 撰写交底书、1提交申请、2受理、3实审、4办登、5授权、6驳回、7复审、8异议、9无效、10初步审查、11授权公告、12 行政复议
     *
     * @return status - 状态： 0 撰写交底书、1提交申请、2受理、3实审、4办登、5授权、6驳回、7复审、8异议、9无效、10初步审查、11授权公告、12 行政复议
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态： 0 撰写交底书、1提交申请、2受理、3实审、4办登、5授权、6驳回、7复审、8异议、9无效、10初步审查、11授权公告、12 行政复议
     *
     * @param status 状态： 0 撰写交底书、1提交申请、2受理、3实审、4办登、5授权、6驳回、7复审、8异议、9无效、10初步审查、11授权公告、12 行政复议
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取发明设计人
     *
     * @return invention_designer - 发明设计人
     */
    public String getInventionDesigner() {
        return inventionDesigner;
    }

    /**
     * 设置发明设计人
     *
     * @param inventionDesigner 发明设计人
     */
    public void setInventionDesigner(String inventionDesigner) {
        this.inventionDesigner = inventionDesigner == null ? null : inventionDesigner.trim();
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
     * 获取代理单位
     *
     * @return agent_unit - 代理单位
     */
    public String getAgentUnit() {
        return agentUnit;
    }

    /**
     * 设置代理单位
     *
     * @param agentUnit 代理单位
     */
    public void setAgentUnit(String agentUnit) {
        this.agentUnit = agentUnit == null ? null : agentUnit.trim();
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
     * 获取本年费缴纳情况 0，未缴  1，已缴
     *
     * @return current_year_payment - 本年费缴纳情况 0，未缴  1，已缴
     */
    public Boolean getCurrentYearPayment() {
        return currentYearPayment;
    }

    /**
     * 设置本年费缴纳情况 0，未缴  1，已缴
     *
     * @param currentYearPayment 本年费缴纳情况 0，未缴  1，已缴
     */
    public void setCurrentYearPayment(Boolean currentYearPayment) {
        this.currentYearPayment = currentYearPayment;
    }

    /**
     * 获取当前年费年度
     *
     * @return current_annual_fee_year - 当前年费年度
     */
    public String getCurrentAnnualFeeYear() {
        return currentAnnualFeeYear;
    }

    /**
     * 设置当前年费年度
     *
     * @param currentAnnualFeeYear 当前年费年度
     */
    public void setCurrentAnnualFeeYear(String currentAnnualFeeYear) {
        this.currentAnnualFeeYear = currentAnnualFeeYear == null ? null : currentAnnualFeeYear.trim();
    }

    /**
     * 获取下一年度年费缴纳时间
     *
     * @return next_year_annual_fee_payment_time - 下一年度年费缴纳时间
     */
    public Long getNextYearAnnualFeePaymentTime() {
        return nextYearAnnualFeePaymentTime;
    }

    /**
     * 设置下一年度年费缴纳时间
     *
     * @param nextYearAnnualFeePaymentTime 下一年度年费缴纳时间
     */
    public void setNextYearAnnualFeePaymentTime(Long nextYearAnnualFeePaymentTime) {
        this.nextYearAnnualFeePaymentTime = nextYearAnnualFeePaymentTime;
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
     * 获取技术特征
     *
     * @return technical_feature - 技术特征
     */
    public String getTechnicalFeature() {
        return technicalFeature;
    }

    /**
     * 设置技术特征
     *
     * @param technicalFeature 技术特征
     */
    public void setTechnicalFeature(String technicalFeature) {
        this.technicalFeature = technicalFeature == null ? null : technicalFeature.trim();
    }

    /**
     * 获取公告日
     *
     * @return announcement_day - 公告日
     */
    public Long getAnnouncementDay() {
        return announcementDay;
    }

    /**
     * 设置公告日
     *
     * @param announcementDay 公告日
     */
    public void setAnnouncementDay(Long announcementDay) {
        this.announcementDay = announcementDay;
    }

    /**
     * 获取公告号
     *
     * @return announcement_number - 公告号
     */
    public String getAnnouncementNumber() {
        return announcementNumber;
    }

    /**
     * 设置公告号
     *
     * @param announcementNumber 公告号
     */
    public void setAnnouncementNumber(String announcementNumber) {
        this.announcementNumber = announcementNumber == null ? null : announcementNumber.trim();
    }

    /**
     * 获取奖金发放
     *
     * @return bonus_payment - 奖金发放
     */
    public String getBonusPayment() {
        return bonusPayment;
    }

    /**
     * 设置奖金发放
     *
     * @param bonusPayment 奖金发放
     */
    public void setBonusPayment(String bonusPayment) {
        this.bonusPayment = bonusPayment == null ? null : bonusPayment.trim();
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