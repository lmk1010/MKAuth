package com.mk.auth.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @Author liumingkang
 * @Date 2019-09-06 20:26
 * @Destcription TODO 错误码实体类
 * @Version 1.0
 **/
public class ErrorCode implements Serializable
{

    // 错误码 0-8位
    private String code;
    // 错误信息
    private String errorMsg;
    // 解决方案
    private String solution;
    // 原因
    private String cause;
    // 类型
    @JsonIgnore
    private String lang;
    // 嵌套错误信息
    @JsonIgnore
    private ErrorCode causedBy;


    public ErrorCode() {
    }

    public ErrorCode(String code) {
        this.code = code;
    }

    public ErrorCode(String code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public ErrorCode(String code, String errorMsg, String solution) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.solution = solution;
    }

    public ErrorCode(String code, String errorMsg, String solution, String cause) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.solution = solution;
        this.cause = cause;
    }

    public ErrorCode(String code, String errorMsg, String solution, String cause, String lang, ErrorCode causedBy) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.solution = solution;
        this.cause = cause;
        this.lang = lang;
        this.causedBy = causedBy;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public ErrorCode getCausedBy() {
        return causedBy;
    }

    public void setCausedBy(ErrorCode causedBy) {
        this.causedBy = causedBy;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code='" + code + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", solution='" + solution + '\'' +
                ", cause='" + cause + '\'' +
                ", lang='" + lang + '\'' +
                ", causedBy=" + causedBy +
                '}';
    }
}
