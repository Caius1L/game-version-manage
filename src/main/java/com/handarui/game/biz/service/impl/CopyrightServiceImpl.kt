package com.handarui.game.biz.service.impl

import com.github.pagehelper.PageHelper
import com.handarui.game.biz.bean.*
import com.handarui.game.biz.query.AddCopyrightQuery
import com.handarui.game.biz.query.CopyrightListQuery
import com.handarui.game.biz.query.ModifyCopyrightQuery
import com.handarui.game.biz.service.CopyrightService
import com.handarui.game.dao.domain.CopyrightAttachDO
import com.handarui.game.dao.domain.CopyrightInfoDO
import com.handarui.game.dao.mapper.CopyrightAttachDOMapper
import com.handarui.game.dao.mapper.CopyrightDOMapper
import com.handarui.game.util.Constant
import com.handarui.game.util.ExcelUtil
import com.zhexinit.ov.common.query.ListBean
import com.zhexinit.ov.common.query.SortPagerQuery
import com.zhexinit.ov.common.query.TitledSortPagerQuery
import org.apache.commons.lang3.StringUtils
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.joda.time.DateTimeUtils
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import tk.mybatis.mapper.entity.Example

@Service
class CopyrightServiceImpl : CopyrightService {
    @Autowired
    private lateinit var copyrightDOMapper: CopyrightDOMapper

    @Autowired
    private lateinit var copyrightAttachDOMapper: CopyrightAttachDOMapper

    @Transactional
    override fun addCopyright(addCopyrightQuery: AddCopyrightQuery?) {
        val copyrightDo = CopyrightInfoDO().apply {
            this.isChanged = addCopyrightQuery!!.changed == 1
        }
        BeanUtils.copyProperties(addCopyrightQuery, copyrightDo)
        copyrightDOMapper.insertSelective(copyrightDo)

        //添加附件
        val petitionMaterials = addCopyrightQuery!!.petitionMaterials  //申请材料
        val registerNumberMaterials = addCopyrightQuery.registerNumberMaterials  //登记号材料材料
        val paymentInvoiceMaterial = addCopyrightQuery.paymentInvoiceMaterial  //款项及发票材料
        val paymentInvoiceAttachDOList = mutableListOf<AttachBean>()
        if (paymentInvoiceMaterial != null) paymentInvoiceAttachDOList.add(paymentInvoiceMaterial)

        addAttach(copyrightDo.id!!, petitionMaterials, Constant.COPYRIGHT_ATTACH_PETITION)
        addAttach(copyrightDo.id!!, registerNumberMaterials, Constant.COPYRIGHT_ATTACH_REGISTER_NUMBER)
        addAttach(copyrightDo.id!!, paymentInvoiceAttachDOList, Constant.COPYRIGHT_ATTACH_PAYMENT_INVOICE)
    }

    @Transactional
    override fun deleteCopyright(ids: List<Long>?) {
        val current = DateTimeUtils.currentTimeMillis() / 1000
        ids!!.forEach {
            val copyrightDo = CopyrightInfoDO().apply {
                this.id = it
                this.isDeleted = 1
                this.createdAt = current
                this.updatedAt = current
            }
            copyrightDOMapper.updateByPrimaryKeySelective(copyrightDo)
        }
    }

    override fun getCopyrightById(id: Long?, department: String?): CopyrightBean? {
        val example = Example(CopyrightInfoDO::class.java)
        val createCriteria = example.createCriteria()
        createCriteria.andEqualTo("id", id)
        if (department != null) {
            createCriteria.andEqualTo("drawDepartment", department)
        }

        val copyrightDo = copyrightDOMapper.selectByExample(example)[0]
        val copyrightBean = CopyrightBean().apply {
            this.changed = copyrightDo.isChanged
        }
        BeanUtils.copyProperties(copyrightDo, copyrightBean)

        val copyrightAttachExample = Example(CopyrightAttachDO::class.java)
        val copyrightAttachCriteria = copyrightAttachExample.createCriteria()
        copyrightAttachCriteria.andEqualTo("copyrightId", id)
        copyrightAttachCriteria.andEqualTo("isDeleted", 0)

        val copyrightAttachDoList = copyrightAttachDOMapper.selectByExample(copyrightAttachExample)
        copyrightAttachDoList.forEach {
            val attachBean = AttachBean()
            BeanUtils.copyProperties(it, attachBean)
            when (it.type) {
                Constant.COPYRIGHT_ATTACH_PETITION -> {   //申请材料类型
                    copyrightBean.petitionMaterials!!.add(attachBean)
                }
                Constant.COPYRIGHT_ATTACH_REGISTER_NUMBER -> {  //登记号材料类型
                    copyrightBean.registerNumberMaterials!!.add(attachBean)
                }
                Constant.COPYRIGHT_ATTACH_PAYMENT_INVOICE -> {  //款项及发票材料类型(单个附件)
                    copyrightBean.paymentInvoiceMaterial = attachBean
                }
            }
        }
        return copyrightBean
    }

    @Transactional
    override fun modifyCopyright(modifyCopyrightQuery: ModifyCopyrightQuery?) {
        val current = DateTimeUtils.currentTimeMillis() / 1000
        val copyrightDo = CopyrightInfoDO().apply {
            this.updatedAt = current
            this.isChanged = modifyCopyrightQuery!!.changed == 1
        }
        BeanUtils.copyProperties(modifyCopyrightQuery, copyrightDo)
        copyrightDOMapper.updateByPrimaryKeySelective(copyrightDo)

        //修改附件
        val petitionMaterials = modifyCopyrightQuery!!.petitionMaterials  //申请材料
        val registerNumberMaterials = modifyCopyrightQuery.registerNumberMaterials  //登记号材料材料
        val paymentInvoiceMaterial = modifyCopyrightQuery.paymentInvoiceMaterial  //款项及发票材料
        val paymentInvoiceMaterialList = mutableListOf<AttachBean>()
        if (paymentInvoiceMaterial != null) paymentInvoiceMaterialList.add(paymentInvoiceMaterial)

        addAttach(modifyCopyrightQuery.id!!, petitionMaterials, Constant.COPYRIGHT_ATTACH_PETITION)
        addAttach(modifyCopyrightQuery.id!!, registerNumberMaterials, Constant.COPYRIGHT_ATTACH_REGISTER_NUMBER)
        addAttach(modifyCopyrightQuery.id!!, paymentInvoiceMaterialList, Constant.COPYRIGHT_ATTACH_PAYMENT_INVOICE)
    }

    override fun getCopyrightList(query: SortPagerQuery<CopyrightListQuery>?, departmentName: String?): ListBean<CopyrightListBean>? {
        val page = PageHelper.startPage<CopyrightBean>(query!!.current, query.pageSize)
        val copyrightList = copyrightDOMapper.selectCopyrightList(query.data, departmentName)
        return ListBean<CopyrightListBean>().apply {
            this.total = page.total.toInt()
            this.data = copyrightList
        }
    }

    override fun getExportCopyrightList(titledSortPagerQuery: TitledSortPagerQuery<CopyrightListQuery>?, department: String?): ListBean<CopyrightListBean>? {
        val copyrightList = copyrightDOMapper.selectCopyrightList(titledSortPagerQuery?.data, department)
        return ListBean<CopyrightListBean>().apply {
            this.total = copyrightList.size
            this.data = copyrightList
        }
    }

    override fun import(file: MultipartFile) {
        val copyrightList = parseCopyrightList(file)
        if (copyrightList != null && !copyrightList.isEmpty()) {
            copyrightDOMapper.insertList(copyrightList)
        }
    }

    /**
     * 添加附件
     */
    private fun addAttach(productPatentId: Long, attachBeanList: List<AttachBean>?, type: Int) {
        if (attachBeanList == null) return
        val current = System.currentTimeMillis() / 1000
        if (attachBeanList.isNotEmpty()) {
            val patentAttachDOList = mutableListOf<CopyrightAttachDO>()
            attachBeanList.forEach {
                val attachDO = CopyrightAttachDO().apply {
                    this.copyrightId = productPatentId
                    this.type = type
                    this.updatedAt = current
                    this.isDeleted = 0
                }
                BeanUtils.copyProperties(it, attachDO)
                if (attachDO.id != null) {
                    copyrightAttachDOMapper.updateByPrimaryKeySelective(attachDO)
                } else {
                    attachDO.createdAt = current
                    patentAttachDOList.add(attachDO)
                }
            }
            if (patentAttachDOList.size > 0) {
                copyrightAttachDOMapper.insertList(patentAttachDOList)
            }
        }
    }

    /**
     * 解析excel
     */
    private fun parseCopyrightList(file: MultipartFile): List<CopyrightInfoDO>? {
        val wb = XSSFWorkbook(file.inputStream)
        val sheet = wb.getSheetAt(0)
        if (sheet != null) {
            val copyrightDOList = mutableListOf<CopyrightInfoDO>()
            for (rowNum in 1..sheet.physicalNumberOfRows) {
                val row = sheet.getRow(rowNum)
                if (row != null) {
                    if (row.getCell(1) == null || StringUtils.isEmpty(ExcelUtil.getXValue(row.getCell(1)))) {
                        continue
                    }
                    val copyrightInfoDO = CopyrightInfoDO().apply {
                        this.number = ExcelUtil.getXValue(row.getCell(0)) ?: ""
                        this.name = ExcelUtil.getXValue(row.getCell(1)) ?: ""
                        this.type = GameTypeEnum.getByTypeName(ExcelUtil.getXValue(row.getCell(2))!!).code
                        this.applicant = ExcelUtil.getXValue(row.getCell(3)) ?: ""
                        this.drawer = ExcelUtil.getXValue(row.getCell(4)) ?: ""
                        this.drawDepartment = ExcelUtil.getXValue(row.getCell(5)) ?: ""
                        this.note = ExcelUtil.getXValue(row.getCell(6)) ?: ""
                        this.category = CopyrightCategoryEnum.getCategoryByName(ExcelUtil.getXValue(row.getCell(7))!!).code
                        this.status = ExcelUtil.getXValue(row.getCell(8)) ?: ""
                        this.registerNumber = ExcelUtil.getXValue(row.getCell(9)) ?: ""
                        val exportFinishedTime = ExcelUtil.getDate(row.getCell(10))
                        this.finishedTime = (exportFinishedTime?.time)?.div(1000) ?: 0
                        val registerTime = ExcelUtil.getDate(row.getCell(11))
                        this.registerTime = registerTime?.time?.div(1000) ?: 0
                        this.paymentInvoice = ExcelUtil.getXValue(row.getCell(12)) ?: ""
                        this.author = ExcelUtil.getXValue(row.getCell(13)) ?: ""
                        val changed = ExcelUtil.getXValue(row.getCell(14))
                        this.isChanged = changed == "是"

                        val current = System.currentTimeMillis() / 1000
                        this.isChanged = false
                        this.createdAt = current
                        this.updatedAt = current
                        this.isDeleted = 0
                    }
                    copyrightDOList.add(copyrightInfoDO)
                }
            }
            return copyrightDOList
        }
        return null
    }
}