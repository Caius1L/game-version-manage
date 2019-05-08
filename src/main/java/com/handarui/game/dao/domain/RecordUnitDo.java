package com.handarui.game.dao.domain;

import javax.persistence.*;

@Table(name = "record_unit")
public class RecordUnitDo {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 游戏id
     */
    @Column(name = "game_id")
    private Long gameId;

    /**
     * 备案单位
     */
    private String unit;

    /**
     * 备案状态0:提交1:受理2:完成
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
     * 获取游戏id
     *
     * @return game_id - 游戏id
     */
    public Long getGameId() {
        return gameId;
    }

    /**
     * 设置游戏id
     *
     * @param gameId 游戏id
     */
    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    /**
     * 获取备案单位
     *
     * @return unit - 备案单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置备案单位
     *
     * @param unit 备案单位
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    /**
     * 获取备案状态0:提交1:受理2:完成
     *
     * @return status - 备案状态0:提交1:受理2:完成
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置备案状态0:提交1:受理2:完成
     *
     * @param status 备案状态0:提交1:受理2:完成
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