package com.handarui.game.biz.service

import com.handarui.game.biz.bean.PatentBean
import com.handarui.game.biz.bean.PatentListBean
import com.handarui.game.biz.query.AddPatentQuery
import com.handarui.game.biz.query.ModifyProductPatentQuery
import com.handarui.game.biz.query.PatentListQuery
import com.zhexinit.ov.common.query.ListBean
import com.zhexinit.ov.common.query.PagerQuery
import com.zhexinit.ov.common.query.TitledSortPagerQuery
import org.springframework.web.multipart.MultipartFile

/**
 * 专利Service
 * @Author: xiebaobiao
 * @Date: 2019/2/16 0016 11:46
 */

interface PatentService {
    /**
     * 添加专利
     */
    fun addProductPatent(addPatentQuery: AddPatentQuery?)

    /**
     * 批量删除专利
     */
    fun batchDeletePatent(ids: List<Long>?)

    /**
     * 修改专利
     */
    fun modifyPatent(modifyProductPatentQuery: ModifyProductPatentQuery?)

    /**
     * 通过id获取专利
     */
    fun getPatentById(id: Long?, department: String?): PatentBean?

    /**
     * 获取专利列表
     */
    fun getPatentList(patentListQuery: PagerQuery<PatentListQuery>, department: String?): ListBean<PatentListBean>?

    /**
     * 导入
     */
    fun import(file: MultipartFile)

    /**
     * 获取导出专利列表
     */
    fun getExportPatentList(titledSortPagerQuery: TitledSortPagerQuery<PatentListQuery>?, department: String?): ListBean<PatentListBean>?

}