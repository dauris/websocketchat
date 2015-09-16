package com.techzguyz.samplesocketproject.other;

/**
 * Created by DL5411 on 9/16/2015.
 */
public class Msg {

    private String FromWho, message;
    private boolean isSelf;

    public Msg() {

    }

    public Msg(String fromWho, String message, boolean isSelf) {
        this.FromWho = fromWho;
        this.message = message;
        this.isSelf=isSelf;
    }

    public String getFromWho(){
        return FromWho;
    }

    public void setFromWho(String FromWho) {
        this.FromWho = FromWho;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message=message;
    }

    public boolean isSelf(){
        return isSelf;
    }

    public void setSelf(boolean isSelf) {
        this.isSelf = isSelf;
    }
}
