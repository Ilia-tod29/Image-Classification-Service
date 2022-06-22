package com.talentboost.ics.dto.datafromapi;

public class ResultDto {
    private Result result;
    private Status status;
    public void setResult(Result result) {
        this.result = result;
    }
    public Result getResult() {
        return result;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public Status getStatus() {
        return status;
    }
}
