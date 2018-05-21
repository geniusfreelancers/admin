package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.SiteSetting;

public interface SiteSettingService {
	SiteSetting updateSiteSetting(SiteSetting siteSetting);
	SiteSetting findOne(Long id);
	List<SiteSetting> findAll();
	void save(SiteSetting siteSettings);
}
