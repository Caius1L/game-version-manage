package com.handarui.game.biz.service.impl

import com.github.pagehelper.PageHelper
import com.handarui.game.biz.bean.*
import com.handarui.game.biz.query.GameQuery
import com.handarui.game.biz.service.GameService
import com.handarui.game.dao.domain.*
import com.handarui.game.dao.mapper.*
import com.handarui.game.util.NumberFormat
import com.zhexinit.ov.common.exception.CommonException
import com.zhexinit.ov.common.query.ListBean
import com.zhexinit.ov.common.query.PagerQuery
import com.zhexinit.ov.common.util.DateUitl
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tk.mybatis.mapper.entity.Example
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*


@Service
class GameServiceImpl : GameService {

    @Autowired
    private lateinit var gameDoMapper: GameDoMapper

    @Autowired
    private lateinit var recordUnitDoMapper: RecordUnitDoMapper

    @Autowired
    private lateinit var attachDoMapper: AttachDoMapper

    @Autowired
    private lateinit var versionMaterialDoMapper: VersionMaterialDoMapper

    @Autowired
    private lateinit var recordMaterialDoMapper: RecordMaterialDoMapper

    private companion object {
        const val ATTACH_COPYRIGHT = 0//计算机软件著作权
        const val ATTACH_APPROVAL = 1//批文
        const val ATTACH_ISBN = 2//isbn
        const val ATTACH_REPLY_OPINION = 3//答复意见
        const val ATTACH_GAME_PACKAGE = 4//游戏包
        @Deprecated("")
        const val ATTACH_VERSION_MATERIAL = 5//版号材料
        const val ATTACH_NOTE = 6//备注
        const val ATTACH_INVOICE = 7//发票
        const val ATTACH_CONTRACT = 8//合同
        const val ATTACH_RECORD_NUMBER = 9//备案号

        const val RECORD_SELF_REPORT = 0
        const val RECORD_USER_PROTOCOL = 1
        const val RECORD_AUTHORIZATION = 2
    }

    private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    /**
     * 查询部门提请的游戏列表
     */
    override fun getGameList(query: PagerQuery<GameQuery>, department: String?): ListBean<BaseGameBean> {
        val page = PageHelper.startPage<BaseGameBean>(query.current, query.pageSize)
        val baseGameList = gameDoMapper.selectBaseGameList(query.data, department)
        return ListBean<BaseGameBean>().apply {
            this.data = baseGameList
            this.total = page.total.toInt()
        }
    }

    /**
     * 查询游戏详情
     */
    @Transactional(readOnly = true)
    override fun getGameDetail(gameId: Long): GameBean {
        val gameDo = gameDoMapper.selectByPrimaryKey(gameId)
        val gameBean = GameBean().apply {
            BeanUtils.copyProperties(gameDo, this)
            this.publishFee = NumberFormat.formatDouble(gameDo.publishFee)
        }
        val example = Example(AttachDo::class.java)
        example.createCriteria().andEqualTo("gameId", gameId).andEqualTo("isDeleted", 0)
        val attachDos = attachDoMapper.selectByExample(example)
        attachDos.forEach {
            val attachBean = AttachBean().apply {
                this.id = it.id
                this.file = it.file
                this.name = it.name
            }
            when (it.type) {
                ATTACH_COPYRIGHT -> {
                    if (gameBean.copyright == null) {
                        gameBean.copyright = mutableListOf()
                    }
                    gameBean.copyright!!.add(attachBean)
                }
                ATTACH_APPROVAL -> gameBean.approval = attachBean
                ATTACH_ISBN -> gameBean.isbn = attachBean
                ATTACH_REPLY_OPINION -> {
                    if (gameBean.replyOpinion == null) {
                        gameBean.replyOpinion = mutableListOf()
                    }
                    gameBean.replyOpinion!!.add(attachBean)
                }
                ATTACH_GAME_PACKAGE -> {
                    if (gameBean.gamePackage == null) {
                        gameBean.gamePackage = mutableListOf()
                    }
                    gameBean.gamePackage!!.add(attachBean)
                }
                ATTACH_NOTE -> {
                    if (gameBean.noteAttach == null) {
                        gameBean.noteAttach = mutableListOf()
                    }
                    gameBean.noteAttach!!.add(attachBean)
                }
                ATTACH_INVOICE -> {
                    if (gameBean.invoice == null) {
                        gameBean.invoice = mutableListOf()
                    }
                    gameBean.invoice!!.add(attachBean)
                }
                ATTACH_CONTRACT -> {
                    if (gameBean.contract == null) {
                        gameBean.contract = mutableListOf()
                    }
                    gameBean.contract!!.add(attachBean)
                }
            }
        }
        //版号材料
        val map = mutableMapOf<Int, MutableList<AttachBean>>()
        val versionMaterialDos = versionMaterialDoMapper.getByGameId(gameBean.id)
        versionMaterialDos.forEach {
            val attach = AttachBean().apply {
                this.id = it.id
                this.file = it.file
                this.name = it.name
            }
            if (map.containsKey(it.type)) {
                map.getValue(it.type).add(attach)
            } else {
                val list = mutableListOf(attach)
                map[it.type] = list
            }
        }
        if (map.isNotEmpty()) {
            gameBean.versionMaterial = map.map {
                VersionMaterialBean().apply {
                    this.type = it.key
                    this.attaches = it.value
                }
            }
        }
        //备案材料
        val recordMaterials = recordMaterialDoMapper.selectByGameId(gameBean.id)
        val recordMaterialMap = mutableMapOf<Long, MutableMap<Int, MutableList<AttachBean>>>()
        recordMaterials.forEach {
            val attach = AttachBean().apply {
                this.id = it.id
                this.file = it.file
                this.name = it.name
            }
            if (recordMaterialMap.containsKey(it.unitId)) {
                val attachMap = recordMaterialMap.getValue(it.unitId)
                if (attachMap.containsKey(it.type)) {
                    attachMap.getValue(it.type).add(attach)
                } else {
                    val attachList = mutableListOf(attach)
                    attachMap[it.type] = attachList
                }
            } else {
                val attachList = mutableListOf(attach)
                val attachMap = mutableMapOf(Pair(it.type, attachList))
                recordMaterialMap[it.unitId] = attachMap
            }
        }
        //备案单位
        val recordUnit = recordUnitDoMapper.selectGameRecordUnit(gameId)
        if (recordUnit != null && recordUnit.isNotEmpty()) {
            gameBean.recordUnit = recordUnit.map {
                RecordUnitBean().apply {
                    this.id = it.id
                    this.unit = it.unit
                    this.status = it.status
                    if (recordMaterialMap.isNotEmpty()&&recordMaterialMap.containsKey(it.id)) {
                        val recordUnitMaterials = recordMaterialMap.getValue(it.id)
                        this.selfReport = recordUnitMaterials[RECORD_SELF_REPORT]
                        this.userProtocol = recordUnitMaterials[RECORD_USER_PROTOCOL]
                        this.authorization = recordUnitMaterials[RECORD_AUTHORIZATION]
                    }
                }
            }
        }
        return gameBean
    }

    /**
     * 首页添加游戏
     */
    @Transactional
    override fun addBaseGame(baseGameBean: BaseGameBean) {
        val gameDo = GameDo().apply {
            BeanUtils.copyProperties(baseGameBean, this)
        }
        gameDoMapper.insertSelective(gameDo)

        val attachList = mutableListOf<AttachDo>()
        val current = DateUitl.getLongTimestamp()
        //批文附件
        if (baseGameBean.approval != null) {
            val approvalAttach = AttachDo().apply {
                this.gameId = gameDo.id
                this.file = baseGameBean.approval!!.file
                this.type = ATTACH_APPROVAL
                this.name = baseGameBean.approval!!.name
                this.createdAt = current
                this.updatedAt = current
                this.isDeleted = 0
            }
            attachList.add(approvalAttach)
        }
        //isbn附件
        if (baseGameBean.isbn != null) {
            val isbnAttach = AttachDo().apply {
                this.gameId = gameDo.id
                this.file = baseGameBean.isbn!!.file
                this.type = ATTACH_ISBN
                this.name = baseGameBean.isbn!!.name
                this.createdAt = current
                this.updatedAt = current
                this.isDeleted = 0
            }
            attachList.add(isbnAttach)
        }
        //软件著作权附件
        if (baseGameBean.copyright != null && baseGameBean.copyright!!.isNotEmpty()) {
            val copyrightAttach = baseGameBean.copyright!!.map {
                AttachDo().apply {
                    this.gameId = gameDo.id
                    this.file = it.file
                    this.type = ATTACH_COPYRIGHT
                    this.name = it.name
                    this.createdAt = current
                    this.updatedAt = current
                    this.isDeleted = 0
                }
            }
            attachList.addAll(copyrightAttach)
        }
        //答复意见附件
        if (baseGameBean.replyOpinion != null && baseGameBean.replyOpinion!!.isNotEmpty()) {
            val replyOpinionAttach = baseGameBean.replyOpinion!!.map {
                AttachDo().apply {
                    this.gameId = gameDo.id
                    this.file = it.file
                    this.type = ATTACH_REPLY_OPINION
                    this.name = it.name
                    this.createdAt = current
                    this.updatedAt = current
                    this.isDeleted = 0
                }
            }
            attachList.addAll(replyOpinionAttach)
        }

        //备案号附件
        if (baseGameBean.recordNumberAttach != null) {
            val isbnAttach = AttachDo().apply {
                this.gameId = gameDo.id
                this.file = baseGameBean.recordNumberAttach!!.file
                this.type = ATTACH_RECORD_NUMBER
                this.name = baseGameBean.recordNumberAttach!!.name
                this.createdAt = current
                this.updatedAt = current
                this.isDeleted = 0
            }
            attachList.add(isbnAttach)
        }

        if (attachList.isNotEmpty()) {
            attachDoMapper.insertList(attachList)
        }
    }

    /**
     * 添加游戏详情
     */
    @Transactional
    override fun addGameDetail(gameBean: GameBean) {
        val gameDo = gameDoMapper.selectByPrimaryKey(gameBean.id)
        if (gameDo != null) {
            BeanUtils.copyProperties(gameBean, gameDo)
            gameDo.publishFee = gameBean.publishFee?.toDouble()
            gameDoMapper.updateByPrimaryKeySelective(gameDo)

            gameDoMapper.updateNullValue(gameDo)
            //更新游戏备案单位
            val recordUnitExample = Example(RecordUnitDo::class.java)
            recordUnitExample.createCriteria().andEqualTo("gameId", gameBean.id).andEqualTo("isDeleted", 0)
            recordUnitDoMapper.fakeDeleteByExample(recordUnitExample)

            val recordMaterialExample = Example(RecordMaterialDo::class.java)
            recordMaterialExample.createCriteria().andEqualTo("gameId", gameBean.id).andEqualTo("isDeleted", 0)
            recordMaterialDoMapper.fakeDeleteByExample(recordMaterialExample)

            gameBean.recordUnit?.forEach {
                val record = RecordUnitDo().apply {
                    this.id = it.id
                    this.gameId = gameBean.id
                    this.unit = it.unit
                    this.status = it.status
                    this.isDeleted = 0
                }
                if (record.id != null) {
                    recordUnitDoMapper.updateByPrimaryKeySelective(record)
                } else {
                    recordUnitDoMapper.insertSelective(record)
                }
                //备案单位材料
                updateRecordMaterial(it.selfReport, gameBean.id!!, record.id, RECORD_SELF_REPORT)
                updateRecordMaterial(it.userProtocol, gameBean.id!!, record.id, RECORD_USER_PROTOCOL)
                updateRecordMaterial(it.authorization, gameBean.id!!, record.id, RECORD_AUTHORIZATION)
            }
            //删除附件
            val deleteType = mutableListOf(ATTACH_GAME_PACKAGE, ATTACH_NOTE, ATTACH_INVOICE, ATTACH_CONTRACT)
            val example = Example(AttachDo::class.java)
            example.createCriteria().andEqualTo("gameId", gameBean.id).andIn("type", deleteType).andEqualTo("isDeleted", 0)
            attachDoMapper.fakeDeleteByExample(example)
            //更新游戏包
            updateAttach(gameBean.id!!, gameBean.gamePackage, ATTACH_GAME_PACKAGE)
            //更新备注
            updateAttach(gameBean.id!!, gameBean.noteAttach, ATTACH_NOTE)
            //更新发票
            updateAttach(gameBean.id!!, gameBean.invoice, ATTACH_INVOICE)
            //更新合同
            updateAttach(gameBean.id!!, gameBean.contract, ATTACH_CONTRACT)
            //更新版号材料
            val versionMaterialExample = Example(VersionMaterialDo::class.java)
            versionMaterialExample.createCriteria().andEqualTo("gameId", gameBean.id).andEqualTo("isDeleted", 0)
            versionMaterialDoMapper.fakeDeleteByExample(versionMaterialExample)
            if (gameBean.versionMaterial != null) {
                gameBean.versionMaterial!!.forEach {
                    val attaches = it.attaches
                    val type = it.type
                    attaches?.forEach {
                        val data = VersionMaterialDo().apply {
                            this.id = it.id
                            this.gameId = gameBean.id
                            this.type = type
                            this.file = it.file
                            this.name = it.name
                            this.isDeleted = 0
                        }
                        if (data.id != null) {
                            versionMaterialDoMapper.updateByPrimaryKeySelective(data)
                        } else {
                            versionMaterialDoMapper.insertSelective(data)
                        }
                    }
                }
            }
        } else {
            throw CommonException(ResultEnum.GameNotExist)
        }
    }

    private fun updateRecordMaterial(recordMaterials: List<AttachBean>?, gameId: Long, recordUnitId: Long, type: Int) {
        recordMaterials?.forEach {
            val recordMaterialDo = RecordMaterialDo().apply {
                this.id = it.id
                this.gameId = gameId
                this.unitId = recordUnitId
                this.type = type
                this.file = it.file
                this.name = it.name
                this.isDeleted = 0
            }
            if (recordMaterialDo.id != null) {
                recordMaterialDoMapper.updateByPrimaryKeySelective(recordMaterialDo)
            } else {
                recordMaterialDoMapper.insertSelective(recordMaterialDo)
            }
        }
    }

    private fun updateAttach(gameId: Long, attaches: List<AttachBean>?, attachType: Int) {
        attaches?.forEach {
            val attach = AttachDo().apply {
                this.id = it.id
                this.gameId = gameId
                this.file = it.file
                this.name = it.name
                this.type = attachType
                this.isDeleted = 0
            }
            if (attach.id != null) {
                attachDoMapper.updateByPrimaryKeySelective(attach)
            } else {
                attachDoMapper.insertSelective(attach)
            }
        }
    }

    /**
     * 根据id获取首页游戏详情
     */
    @Transactional(readOnly = true)
    override fun getBaseGameById(id: Long): BaseGameBean {
        val baseGameBean = gameDoMapper.selectBaseGameById(id)
        val example = Example(AttachDo::class.java)
        val type =  mutableListOf(ATTACH_APPROVAL, ATTACH_COPYRIGHT, ATTACH_ISBN, ATTACH_REPLY_OPINION, ATTACH_RECORD_NUMBER)
        example.createCriteria().andEqualTo("gameId", id).andIn("type",type).andEqualTo("isDeleted", 0)
        val attachDos = attachDoMapper.selectByExample(example)
        attachDos.forEach {
            val attachBean = AttachBean().apply {
                this.id = it.id
                this.file = it.file
                this.name = it.name
            }
            when (it.type) {
                ATTACH_COPYRIGHT -> {
                    if (baseGameBean.copyright == null) {
                        baseGameBean.copyright = mutableListOf()
                    }
                    baseGameBean.copyright!!.add(attachBean)
                }
                ATTACH_APPROVAL -> baseGameBean.approval = attachBean
                ATTACH_ISBN -> baseGameBean.isbn = attachBean
                ATTACH_REPLY_OPINION -> {
                    if (baseGameBean.replyOpinion == null) {
                        baseGameBean.replyOpinion = mutableListOf()
                    }
                    baseGameBean.replyOpinion!!.add(attachBean)
                }
                ATTACH_RECORD_NUMBER -> baseGameBean.recordNumberAttach = attachBean
            }
        }
        return baseGameBean
    }

    /**
     * 修改游戏基本信息
     */
    @Transactional
    override fun updateBaseGame(baseGameBean: BaseGameBean) {
        val existData = gameDoMapper.selectBaseGameById(baseGameBean.id!!)
        if (existData != null) {
            val baseGameDo = GameDo().apply {
                this.id = baseGameBean.id
                this.name = baseGameBean.name
                this.type = baseGameBean.type
                this.copyrightUnit = baseGameBean.copyrightUnit
                this.operationUnit = baseGameBean.operationUnit
                this.process = baseGameBean.process
                this.sort = baseGameBean.sort
//                this.submitDepartment = baseGameBean.submitDepartment
                this.recordNumber = baseGameBean.recordNumber
                this.approvalNumber = baseGameBean.approvalNumber
                this.isbnNumber = baseGameBean.isbnNumber
                this.copyrightNumber = baseGameBean.copyrightNumber
            }
            gameDoMapper.updateByPrimaryKeySelective(baseGameDo)
            //删除原附件
            val deleteType = mutableListOf(ATTACH_APPROVAL, ATTACH_COPYRIGHT, ATTACH_ISBN, ATTACH_REPLY_OPINION, ATTACH_RECORD_NUMBER)
            val example = Example(AttachDo::class.java)
            example.createCriteria().andEqualTo("gameId", baseGameBean.id).andIn("type", deleteType).andEqualTo("isDeleted", 0)
            attachDoMapper.fakeDeleteByExample(example)

            //批文附件
            if (baseGameBean.approval != null) {
                val approvalAttach = AttachDo().apply {
                    this.id = baseGameBean.approval!!.id
                    this.gameId = baseGameBean.id
                    this.file = baseGameBean.approval!!.file
                    this.type = ATTACH_APPROVAL
                    this.name = baseGameBean.approval!!.name
                    this.isDeleted = 0
                }
                if (approvalAttach.id != null) {
                    attachDoMapper.updateByPrimaryKeySelective(approvalAttach)
                } else {
                    attachDoMapper.insertSelective(approvalAttach)
                }
            }
            //isbn附件
            if (baseGameBean.isbn != null) {
                val isbnAttach = AttachDo().apply {
                    this.id = baseGameBean.isbn!!.id
                    this.gameId = baseGameBean.id
                    this.file = baseGameBean.isbn!!.file
                    this.type = ATTACH_ISBN
                    this.name = baseGameBean.isbn!!.name
                    this.isDeleted = 0
                }
                if (isbnAttach.id != null) {
                    attachDoMapper.updateByPrimaryKeySelective(isbnAttach)
                } else {
                    attachDoMapper.insertSelective(isbnAttach)
                }
            }
            //备案号附件
            if (baseGameBean.recordNumberAttach != null) {
                val recordNumberAttach = AttachDo().apply {
                    this.id = baseGameBean.recordNumberAttach!!.id
                    this.gameId = baseGameBean.id
                    this.file = baseGameBean.recordNumberAttach!!.file
                    this.type = ATTACH_RECORD_NUMBER
                    this.name = baseGameBean.recordNumberAttach!!.name
                    this.isDeleted = 0
                }
                if (recordNumberAttach.id != null) {
                    attachDoMapper.updateByPrimaryKeySelective(recordNumberAttach)
                } else {
                    attachDoMapper.insertSelective(recordNumberAttach)
                }
            }
            //软件著作权附件
            updateAttach(baseGameBean.id!!, baseGameBean.copyright, ATTACH_COPYRIGHT)
            //答复意见附件
            updateAttach(baseGameBean.id!!, baseGameBean.replyOpinion, ATTACH_REPLY_OPINION)

        } else throw CommonException(ResultEnum.GameNotExist)
    }

    /**
     * 批量删除游戏
     */
    override fun batchDeleteGame(ids: List<Long>) {
        if(ids.isNotEmpty()){
            gameDoMapper.batchDeleteGame(ids)
        }
    }

    /**
     * 批量添加游戏
     */
    @Transactional
    override fun addGameBatch(games: List<GameBean>) {
        games.forEach { it ->
            val gameDo = GameDo().apply {
                this.archiveNumber = it.archiveNumber
                this.name = it.name
                this.publishUnit = it.publishUnit
                this.copyrightUnit = it.copyrightUnit
                this.operationUnit = it.operationUnit
                this.type = it.type
                this.sort = it.sort
                this.copyrightNumber = it.copyrightNumber
                this.approvalTime = it.approvalTime
                this.approvalNumber = it.approvalNumber
                this.isbnNumber = it.isbnNumber
                this.process = it.process
                this.acceptTime = it.acceptTime
                this.publishFee = it.publishFee?.toDouble()
                this.submitUserName = it.submitUserName
                this.submitDepartment = it.submitDepartment
                this.recordNumber = it.recordNumber
                this.submitUserPhone = it.submitUserPhone
                this.submitUserEmail = it.submitUserEmail
            }
            gameDoMapper.insertSelective(gameDo)
            val current = DateUitl.getLongTimestamp()
            if (it.recordUnit != null && it.recordUnit!!.isNotEmpty()) {
                val recordUnitDos = it.recordUnit!!.map {
                    RecordUnitDo().apply {
                        this.gameId = gameDo.id
                        this.unit = it.unit
                        this.status = it.status
                        this.createdAt = current
                        this.updatedAt = current
                        this.isDeleted = 0
                    }
                }
                recordUnitDoMapper.insertList(recordUnitDos)
            }
        }
    }

    /**
     * 导出游戏
     */
    override fun exportGame(query: GameQuery?,department: String?, outputStream: OutputStream) {
        val titleNames = arrayOf("存档编号", "游戏名称", "出版单位", "著作权单位", "运营单位", "分类", "游戏类型", "计算机软件著作权号", "批文时间", "批文号", "ISBN号", "进度", "总局受理时间", "出版费", "备案单位", "提请人", "提请部门", "提请人手机", "提请人邮箱","备案号 ")
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("游戏列表")
        val title = sheet.createRow(0)
        for (i in 0 until titleNames.size) {
            val cell = title.createCell(i)
            cell.setCellType(CellType.STRING)
            cell.setCellValue(titleNames[i])
        }
        val example = Example(RecordUnitDo::class.java)
        example.createCriteria().andEqualTo("isDeleted",0)
        val recordUnitDos = recordUnitDoMapper.selectByExample(example)
        val recordUnitMap = mutableMapOf<Long,MutableList<String>>()
        recordUnitDos.forEach {
            if (!recordUnitMap.containsKey(it.gameId)){
                recordUnitMap[it.gameId] = mutableListOf()
            }
            recordUnitMap[it.gameId]!!.add(it.unit)
        }
        val gameList = getAllGames(query,department)
        if (gameList != null && gameList.isNotEmpty()) {
            for (i in 0 until gameList.size) {
                val row = sheet.createRow(i + 1)
                val game = gameList[i]
                row.createCell(0, CellType.STRING).setCellValue(game.archiveNumber)
                row.createCell(1, CellType.STRING).setCellValue(game.name)
                row.createCell(2, CellType.STRING).setCellValue(game.publishUnit)
                row.createCell(3, CellType.STRING).setCellValue(game.copyrightUnit)
                row.createCell(4, CellType.STRING).setCellValue(game.operationUnit)
                row.createCell(5, CellType.STRING).setCellValue(GameTypeEnum.getByTypeCode(game.type).type)
                row.createCell(6, CellType.STRING).setCellValue(GameSortEnum.getSortByCode(game.sort).sort)
                row.createCell(7, CellType.STRING).setCellValue(game.copyrightNumber)
                row.createCell(8, CellType.STRING).setCellValue(if (game.approvalTime == null) "-" else sdf.format(Date(game.approvalTime * 1000)))
                row.createCell(9, CellType.STRING).setCellValue(game.approvalNumber)
                row.createCell(10, CellType.STRING).setCellValue(game.isbnNumber)
                row.createCell(11, CellType.STRING).setCellValue(ProcessEnum.getByProcessCode(game.process).process)
                row.createCell(12, CellType.STRING).setCellValue(if (game.acceptTime == null) "-" else sdf.format(Date(game.acceptTime * 1000)))
                row.createCell(13, CellType.STRING).setCellValue(NumberFormat.formatDouble(game.publishFee))
                val recordUnitNameList = recordUnitMap[game.id]
                val recordUnitNames = StringBuilder()
                recordUnitNameList?.forEachIndexed { index, name ->
                    if (index == 0) {
                        recordUnitNames.append(name)
                    } else {
                        recordUnitNames.append(",").append(name)
                    }
                }
                row.createCell(14, CellType.STRING).setCellValue(recordUnitNames.toString())
                row.createCell(15, CellType.STRING).setCellValue(game.submitUserName)
                row.createCell(16, CellType.STRING).setCellValue(game.submitDepartment)
                row.createCell(17, CellType.STRING).setCellValue(game.submitUserPhone)
                row.createCell(18, CellType.STRING).setCellValue(game.submitUserEmail)
                row.createCell(19, CellType.STRING).setCellValue(game.recordNumber)
            }
        }
        workbook.write(outputStream)
    }

    fun getAllGames(query: GameQuery?,department: String?): List<GameDo>? {
        val example = Example(GameDo::class.java)
        val criteria = example.createCriteria()
        if (query != null) {
            if (query.name != null) {
                criteria.andLike("categoryName", "%" + query.name + "%")
            }
            if (query.copyrightUnit != null) {
                criteria.andEqualTo("copyrightUnit", query.copyrightUnit)
            }
            if (query.operationUnit != null) {
                criteria.andEqualTo("operationUnit", query.operationUnit)
            }
            if (query.submitDepartment != null) {
                criteria.andEqualTo("submitDepartment", query.submitDepartment)
            }
            if (department != null) {
                criteria.andEqualTo("submitDepartment", department)
            }
            criteria.andEqualTo("type", query.type)
        }
        criteria.andEqualTo("isDeleted", 0)
        return gameDoMapper.selectByExample(example)
    }
}