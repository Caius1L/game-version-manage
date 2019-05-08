package com.handarui.game.biz.bean;

import com.zhexinit.ov.common.model.IResultEnum;

public enum ResultEnum implements IResultEnum {

    SystemError(999,"系统错误"),
    GameNotExist(100,"游戏不存在"),
    oldPasswordError(101,"原密码错误"),
    passwordEqual(102,"原密码和新密码不能相同"),
    patentNotExist(103,"专利不存在")
    ;

    private int code;

    private String message;

    ResultEnum(int code,String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
