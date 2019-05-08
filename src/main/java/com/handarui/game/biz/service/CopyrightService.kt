package com.handarui.game.biz.service

import com.handarui.game.biz.bean.CopyrightBean
import com.handarui.game.biz.bean.CopyrightListBean
import com.handarui.game.biz.query.AddCopyrightQuery
import com.handarui.game.biz.query.CopyrightListQuery
import com.handarui.game.biz.query.ModifyCopyrightQuery
import com.zhexinit.ov.common.query.ListBean
import com.zhexinit.ov.common.query.SortPagerQuery
import com.zhexinit.ov.common.query.TitledSortPagerQuery
import org.springframework.web.multipart.MultipartFile

/**
 * 著作权
 */
interface CopyrightService {
    /**
     * 添加著作权
     */
    fun addCopyright(addCopyrightQuery: AddCopyrightQuery?)

    /**
     * 删除著作权
     */
    fun deleteCopyright(ids: List<Long>?)

    /**
     * 获取著作权
     */
    fun getCopyrightById(id: Long?, department: String?): CopyrightBean?

    /**
     * 修改著作权
     */
    fun modifyCopyright(modifyCopyrightQuery: ModifyCopyrightQuery?)

    /**
     * 获取著作权列表
     */
    fun getCopyrightList(query: SortPagerQuery<CopyrightListQuery>?, departmentName: String?): ListBean<CopyrightListBean>?

    /**
     * 导入
     */
    fun import(file: MultipartFile)

    /**
     * 获取导出著作权列表
     */
    fun getExportCopyrightList(titledSortPagerQuery: TitledSortPagerQuery<CopyrightListQuery>?, department: String?): ListBean<CopyrightListBean>?

}