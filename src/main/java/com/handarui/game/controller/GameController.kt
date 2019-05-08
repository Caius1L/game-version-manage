package com.handarui.game.controller

import com.handarui.auth.base.model.ClassicalUserVo
import com.handarui.game.biz.bean.*
import com.handarui.game.biz.query.GameQuery
import com.handarui.game.biz.service.GameService
import com.handarui.game.util.ExcelUtil
import com.zhexinit.ov.common.bean.RequestBean
import com.zhexinit.ov.common.bean.ResponseBean
import com.zhexinit.ov.common.query.ListBean
import com.zhexinit.ov.common.query.PagerQuery
import com.zhexinit.ov.common.util.ResponseUtil
import org.apache.commons.lang3.StringUtils
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.text.SimpleDateFormat
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("game")
class GameController {

    @Autowired
    private lateinit var gameService: GameService

    private companion object {
        const val DEPARTMENT = "法务部门"
    }

    private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @PostMapping("getGameList")
    fun getGameList(@Valid @RequestBody requestBean: RequestBean<PagerQuery<GameQuery>>, classicalUserVo: ClassicalUserVo): ResponseBean<ListBean<BaseGameBean>> {
        val gameList = if (classicalUserVo.roles.any { it.name == DEPARTMENT }) {
            gameService.getGameList(requestBean.param)
        } else {
            gameService.getGameList(requestBean.param, classicalUserVo.roles[0].name)
        }
//        val gameList = gameService.getGameList(requestBean.param)
        return ResponseUtil.success(gameList)
    }

    @PostMapping("getGameDetail")
    fun getGameDetail(@RequestBody requestBean: RequestBean<Long>): ResponseBean<GameBean> {
        return ResponseUtil.success(gameService.getGameDetail(requestBean.param))
    }

    @PostMapping("addBaseGame")
    fun addBaseGame(@Valid @RequestBody requestBean: RequestBean<BaseGameBean>): ResponseBean<Void> {
        gameService.addBaseGame(requestBean.param)
        return ResponseUtil.success()
    }

    @PostMapping("addGameDetail")
    fun addGameDetail(@RequestBody requestBean: RequestBean<GameBean>): ResponseBean<Void> {
        gameService.addGameDetail(requestBean.param)
        return ResponseUtil.success()
    }

    @PostMapping("getBaseGameById")
    fun getBaseGameById(@RequestBody requestBean: RequestBean<Long>): ResponseBean<BaseGameBean> {
        val baseGameBean = gameService.getBaseGameById(requestBean.param)
        return ResponseUtil.success(baseGameBean)
    }

    @PostMapping("updateBaseGame")
    fun updateBaseGame(@Valid @RequestBody requestBean: RequestBean<BaseGameBean>): ResponseBean<Void> {
        gameService.updateBaseGame(requestBean.param)
        return ResponseUtil.success()
    }

    @PostMapping("batchDelete")
    fun batchDeleteGame(@RequestBody requestBean: RequestBean<List<Long>>): ResponseBean<Void> {
        gameService.batchDeleteGame(requestBean.param)
        return ResponseUtil.success()
    }

    @PostMapping("import")
    fun importGame(@RequestPart("file") file: MultipartFile): ResponseBean<Void> {
        val games = parseGameList(file)
        if (games != null && !games.isEmpty()) {
            gameService.addGameBatch(games)
        }
        return ResponseUtil.success()
    }

    @PostMapping("export", produces = ["application/octet-stream;charset=UTF-8"])
    fun exportGame(@Valid @RequestBody requestBean: RequestBean<GameQuery?>, response: HttpServletResponse,classicalUserVo: ClassicalUserVo) {
        val department = if (classicalUserVo.roles.any { it.name == DEPARTMENT }) {
            null
        } else {
            classicalUserVo.roles[0].name
        }
        gameService.exportGame(requestBean.param, department,response.outputStream)
    }

    private fun parseGameList(file: MultipartFile): List<GameBean>? {
        val wb = XSSFWorkbook(file.inputStream)
        val sheet = wb.getSheetAt(0)
        if (sheet != null) {
            val games = mutableListOf<GameBean>()
            for (rowNum in 1..sheet.physicalNumberOfRows) {
                val row = sheet.getRow(rowNum)
                if (row != null) {
                    if (row.getCell(1) == null || StringUtils.isEmpty(ExcelUtil.getXValue(row.getCell(1)))) {
                        continue
                    }
                    val iterator = row.cellIterator()
                    while (iterator.hasNext()) {
                        iterator.next().setCellType(CellType.STRING)
                    }
                    val baseGameBean = GameBean().apply {
                        this.archiveNumber = ExcelUtil.getXValue(row.getCell(0))
                        this.name = ExcelUtil.getXValue(row.getCell(1))
                        this.publishUnit = ExcelUtil.getXValue(row.getCell(2))
                        this.copyrightUnit = ExcelUtil.getXValue(row.getCell(3))
                        this.operationUnit = ExcelUtil.getXValue(row.getCell(4))
                        this.type = GameTypeEnum.getByTypeName(ExcelUtil.getXValue(row.getCell(5))!!).code
                        this.sort = GameSortEnum.getSortByName(ExcelUtil.getXValue(row.getCell(6))!!).code
                        this.copyrightNumber = ExcelUtil.getXValue(row.getCell(7))
                        val approvalTime = ExcelUtil.getXValue(row.getCell(8))
                        if (StringUtils.isNotEmpty(approvalTime)) {
                            this.approvalTime = sdf.parse(approvalTime).time / 1000
                        }
                        this.approvalNumber = ExcelUtil.getXValue(row.getCell(9))
                        this.isbnNumber = ExcelUtil.getXValue(row.getCell(10))
                        this.process = ProcessEnum.getByProcessName(ExcelUtil.getXValue(row.getCell(11))!!).code
                        val acceptTime = ExcelUtil.getXValue(row.getCell(12))
                        if (StringUtils.isNotEmpty(acceptTime)) {
                            this.acceptTime = sdf.parse(acceptTime).time / 1000
                        }
                        this.publishFee = ExcelUtil.getXValue(row.getCell(13)) ?: "0.00"
                        val recordName = ExcelUtil.getXValue(row.getCell(14))
                        if(StringUtils.isNotEmpty(recordName)){
                            val recordNames = recordName?.split(",")
                            if (recordNames != null && !recordNames.isEmpty()) {
                                this.recordUnit = recordNames.map {
                                    RecordUnitBean().apply {
                                        this.unit = it
                                    }
                                }
                            }
                        }
                        this.submitUserName = ExcelUtil.getXValue(row.getCell(15))
                        this.submitDepartment = ExcelUtil.getXValue(row.getCell(16))
                        this.submitUserPhone = ExcelUtil.getXValue(row.getCell(17))
                        this.submitUserEmail = ExcelUtil.getXValue(row.getCell(18))
                        this.recordNumber = ExcelUtil.getXValue(row.getCell(19))
                    }
                    games.add(baseGameBean)
                }
            }
            return games
        }
        return null
    }
}