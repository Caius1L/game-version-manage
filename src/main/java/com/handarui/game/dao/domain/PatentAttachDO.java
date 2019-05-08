package com.handarui.game.dao.domain;

import javax.persistence.*;

@Table(name = "patent_attach")
public class PatentAttachDO {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 著作权id
     */
    @Column(name = "patent_id")
    private Long patentId;

    /**
     * 文件oss key
     */
    private String file;

    /**
     * 文件名
     */
    private String name;

    /**
     * 附件类型 0:状态附件 1：本年费缴纳情况 2：备注附件
     */
    private Integer type;

    /**
     * 状态附件类型： 0 撰写交底书、1提交申请、2受理、3实审、4办登、5授权、6驳回、7复审、8异议、9无效、10初步审查、11授权公告、12 行政复议
     */
    private Integer status;

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
     * 获取著作权id
     *
     * @return product_patent_id - 著作权id
     */
    public Long getPatentId() {
        return patentId;
    }

    /**
     * 设置著作权id
     *
     * @param patentId 著作权id
     */
    public void setPatentId(Long patentId) {
        this.patentId = patentId;
    }

    /**
     * 获取文件oss key
     *
     * @return file - 文件oss key
     */
    public String getFile() {
        return file;
    }

    /**
     * 设置文件oss key
     *
     * @param file 文件oss key
     */
    public void setFile(String file) {
        this.file = file == null ? null : file.trim();
    }

    /**
     * 获取文件名
     *
     * @return categoryName - 文件名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置文件名
     *
     * @param name 文件名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取附件类型 0:状态附件 1：本年费缴纳情况 2：备注附件
     *
     * @return type - 附件类型 0:状态附件 1：本年费缴纳情况 2：备注附件
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置附件类型 0:状态附件 1：本年费缴纳情况 2：备注附件
     *
     * @param type 附件类型 0:状态附件 1：本年费缴纳情况 2：备注附件
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取状态附件类型： 0 撰写交底书、1提交申请、2受理、3实审、4办登、5授权、6驳回、7复审、8异议、9无效、10初步审查、11授权公告、12 行政复议
     *
     * @return status - 状态附件类型： 0 撰写交底书、1提交申请、2受理、3实审、4办登、5授权、6驳回、7复审、8异议、9无效、10初步审查、11授权公告、12 行政复议
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态附件类型： 0 撰写交底书、1提交申请、2受理、3实审、4办登、5授权、6驳回、7复审、8异议、9无效、10初步审查、11授权公告、12 行政复议
     *
     * @param status 状态附件类型： 0 撰写交底书、1提交申请、2受理、3实审、4办登、5授权、6驳回、7复审、8异议、9无效、10初步审查、11授权公告、12 行政复议
     */
    public void setStatus(Integer status) {
        this.status = status;
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