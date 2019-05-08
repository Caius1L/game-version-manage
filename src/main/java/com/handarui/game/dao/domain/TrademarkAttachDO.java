package com.handarui.game.dao.domain;

import javax.persistence.*;

@Table(name = "trademark_attach")
public class TrademarkAttachDO {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商标id
     */
    @Column(name = "trademark_info_id")
    private Long trademarkInfoId;

    /**
     * 文件oss key
     */
    private String file;

    /**
     * 文件名
     */
    private String name;

    /**
     * 附件类型 0:图样附件 1受理号/注册号附件2状态附件
     */
    private Integer type;

    /**
     * 状态 ：0 对接、1提交申请、2受理、3注册、4驳回、5部分驳回、6复审、7撤三、8异议、9初审公告、10注册公告、11等待实质审查
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
     * 获取商标id
     *
     * @return trademark_info_id - 商标id
     */
    public Long getTrademarkInfoId() {
        return trademarkInfoId;
    }

    /**
     * 设置商标id
     *
     * @param trademarkInfoId 商标id
     */
    public void setTrademarkInfoId(Long trademarkInfoId) {
        this.trademarkInfoId = trademarkInfoId;
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
     * 获取附件类型 0:图样附件 1受理号/注册号附件2状态附件
     *
     * @return type - 附件类型 0:图样附件 1受理号/注册号附件2状态附件
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置附件类型 0:图样附件 1受理号/注册号附件2状态附件
     *
     * @param type 附件类型 0:图样附件 1受理号/注册号附件2状态附件
     */
    public void setType(Integer type) {
        this.type = type;
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