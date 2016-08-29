package com.zwhkj.todaynews.todaynews.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Tui_Message implements Serializable {

    private String Itype;
    private String msg_content;// 消息内容
    private String warn;// 标题
    private String warnType;
    private String voiceType;

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public String getWarn() {
        return warn;
    }

    public void setWarn(String warn) {
        this.warn = warn;
    }

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public String getVoiceType() {
        return voiceType;
    }

    public void setVoiceType(String voiceType) {
        this.voiceType = voiceType;
    }

    public String getItype() {
        return Itype;
    }

    public void setItype(String itype) {
        Itype = itype;
    }

}
