package com.handarui.game.controller

import com.handarui.auth.base.model.ClassicalUserVo
import com.handarui.game.biz.bean.CopyrightBean
import com.handarui.game.biz.bean.CopyrightListBean
import com.handarui.game.biz.query.AddCopyrightQuery
import com.handarui.game.biz.query.CopyrightListQuery
import com.handarui.game.biz.query.ModifyCopyrightQuery
import com.handarui.game.biz.service.CopyrightService
import com.zhexinit.ov.common.bean.RequestBean
import com.zhexinit.ov.common.bean.ResponseBean
import com.zhexinit.ov.common.model.ExportItemInfo
import com.zhexinit.ov.common.query.ListBean
import com.zhexinit.ov.common.query.SortPagerQuery
import com.zhexinit.ov.common.query.TitledSortPagerQuery
import com.zhexinit.ov.common.util.ResponseUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

/**
 * 著作权
 */
@RestController
@RequestMapping("copyright")
class CopyrightController {

    private companion object {
        const val DEPARTMENT = "法务部门"
    }

    @Autowired
    private lateinit var copyrightService: CopyrightService

    /**
     * 添加著作权
     */
    @PostMapping("addCopyright")
    fun addCopyright(@RequestBody requestBean: RequestBean<AddCopyrightQuery>): ResponseBean<Void> {
        copyrightService.addCopyright(requestBean.param)
        return ResponseUtil.success()
    }

    /**
     * 删除著作权
     */
    @PostMapping("batchDeleteCopyright")
    fun deleteCopyright(@RequestBody requestBean: RequestBean<List<Long>>): ResponseBean<Void> {
        copyrightService.deleteCopyright(requestBean.param)
        return ResponseUtil.success()
    }

    /**
     * 根据id获取著作权
     */
    @PostMapping("getCopyrightById")
    fun getCopyrightById(@RequestBody requestBean: RequestBean<Long>, classicalUserVo: ClassicalUserVo): ResponseBean<CopyrightBean> {
        val department = if (classicalUserVo.roles.any { it.name == CopyrightController.DEPARTMENT }) {
            null
        } else {
            classicalUserVo.roles[0].name
        }
        return ResponseUtil.success(copyrightService.getCopyrightById(requestBean.param, department))
    }

    /**
     * 获取著作权列表
     */
    @PostMapping("getCopyrightList")
    fun getCopyrightList(@RequestBody requestBean: RequestBean<SortPagerQuery<CopyrightListQuery>>, classicalUserVo: ClassicalUserVo): ResponseBean<ListBean<CopyrightListBean>> {
        val department = if (classicalUserVo.roles.any { it.name == CopyrightController.DEPARTMENT }) {
            null
        } else {
            classicalUserVo.roles[0].name
        }
        return ResponseUtil.success(copyrightService.getCopyrightList(requestBean.param, department))
    }

    /**
     * 修改著作权
     */
    @PostMapping("modifyCopyright")
    fun modifyCopyright(@RequestBody requestBean: RequestBean<ModifyCopyrightQuery>): ResponseBean<Void> {
        copyrightService.modifyCopyright(requestBean.param)
        return ResponseUtil.success()
    }

    /**
     * 导入
     */
    @PostMapping("import")
    fun importCopyright(@RequestPart("file") file: MultipartFile): ResponseBean<Void> {
        copyrightService.import(file)
        return ResponseUtil.success()
    }

    /**
     * 导出
     */
    @PostMapping("export")
    fun exportCopyright(@RequestBody requestBean: RequestBean<TitledSortPagerQuery<CopyrightListQuery>>, classicalUserVo: ClassicalUserVo): ByteArray {
        val department = if (classicalUserVo.roles.any { it.name == CopyrightController.DEPARTMENT }) {
            null
        } else {
            classicalUserVo.roles[0].name
        }
        val copyrightList = copyrightService.getExportCopyrightList(requestBean.param, department)
        copyrightList!!.data.forEach {
            when (it.type) {
                0 -> it.typeExport = "作品著作权"
                1 -> it.categoryExport = "作品著作权"
            }
            when (it.category) {
                0 -> it.typeExport = "软件"
                1 -> it.typeExport = "动画"
                2 -> it.typeExport = "小说"
                3 -> it.typeExport = "图案"
                4 -> it.typeExport = "其他"
            }
            it.changedExport = if (it.changed!!) "是" else "否"
        }
        return ResponseUtil.exportAsBytes(copyrightList, getExportTitles())
    }

    /**
     * 获取导出excel的titles
     */
    private fun getExportTitles(): List<ExportItemInfo> {
        val dateFormat = "yyyy-MM-dd HH:mm:ss"
        val titles = mutableListOf<ExportItemInfo>()
        val numberExport = ExportItemInfo().apply {
            this.title = "著作权编号"
            this.index = "number"
        }
        val nameExport = ExportItemInfo().apply {
            this.title = "著作权名称"
            this.index = "name"
        }
        val typeExport = ExportItemInfo().apply {
            this.title = "类型"
            this.index = "typeExport"
        }
        val applicantExport = ExportItemInfo().apply {
            this.title = "申请人"
            this.index = "applicant"
        }
        val drawerExport = ExportItemInfo().apply {
            this.title = "提请人"
            this.index = "drawer"
        }
        val drawDepartmentExport = ExportItemInfo().apply {
            this.title = "提请部门"
            this.index = "drawDepartment"
        }
        val noteExport = ExportItemInfo().apply {
            this.title = "备注"
            this.index = "note"
        }
        val categoryExport = ExportItemInfo().apply {
            this.title = "分类"
            this.index = "categoryExport"
        }
        val statusExport = ExportItemInfo().apply {
            this.title = "状态"
            this.index = "status"
        }
        val registerNumberExport = ExportItemInfo().apply {
            this.title = "登记号"
            this.index = "registerNumber"
        }
        val finishedTimeExport = ExportItemInfo().apply {
            this.title = "作品完成时间"
            this.index = "finishedTime"
            this.type = dateFormat
        }
        val registerTimeExport = ExportItemInfo().apply {
            this.title = "登记时间"
            this.index = "registerTime"
            this.type = dateFormat
        }
        val paymentInvoiceExport = ExportItemInfo().apply {
            this.title = "款项及发票"
            this.index = "paymentInvoice"
        }
        val authorExport = ExportItemInfo().apply {
            this.title = "作者"
            this.index = "author"
        }
        val changedExport = ExportItemInfo().apply {
            this.title = "是否更改"
            this.index = "changedExport"
        }

        titles.add(numberExport)
        titles.add(nameExport)
        titles.add(typeExport)
        titles.add(applicantExport)
        titles.add(drawerExport)
        titles.add(drawDepartmentExport)
        titles.add(noteExport)
        titles.add(categoryExport)
        titles.add(statusExport)
        titles.add(registerNumberExport)
        titles.add(finishedTimeExport)
        titles.add(registerTimeExport)
        titles.add(paymentInvoiceExport)
        titles.add(authorExport)
        titles.add(changedExport)
        return titles
    }

}