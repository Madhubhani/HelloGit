package com.helloRest.domain;

import java.util.Date;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseDomain {
    
    private String createdBy;

    private Date createdTime;

    private String lastModifiedBy;

    private Date lastModifiedTime;

    private boolean isDeleted = false;
    
    @Transient
    private boolean returnIdonly = false;

    public String getCreatedBy() {
	return createdBy;
    }

    public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
	return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
	this.createdTime = createdTime;
    }

    public String getLastModifiedBy() {
	return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
	this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedTime() {
	return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
	this.lastModifiedTime = lastModifiedTime;
    }

    @JsonIgnore
    public boolean isDeleted() {
	return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
	this.isDeleted = isDeleted;
    }

    @JsonIgnore
    public boolean isReturnIdonly() {
        return returnIdonly;
    }

    @JsonIgnore
    public void setReturnIdonly(boolean returnIdonly) {
        this.returnIdonly = returnIdonly;
    }

}
