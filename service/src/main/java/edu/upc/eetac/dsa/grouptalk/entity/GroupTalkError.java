package edu.upc.eetac.dsa.grouptalk.entity;

/**
 * Created by Alex on 21/10/15.
 */
public class GroupTalkError {

    private int status;
    private String reason;

    public GroupTalkError() {
    }

    public GroupTalkError(int status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
