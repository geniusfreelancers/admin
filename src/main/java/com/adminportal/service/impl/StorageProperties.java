package com.adminportal.service.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
  /*  private String location = "templates";*/
	//  private String location = "upload-dir";
	  private String location = "src\\main\\resources\\templates\\emails";
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
