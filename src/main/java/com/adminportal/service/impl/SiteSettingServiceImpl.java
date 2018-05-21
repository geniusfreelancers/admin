package com.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.SiteSetting;
import com.adminportal.repository.SiteSettingRepository;
import com.adminportal.service.SiteSettingService;

@Service
public class SiteSettingServiceImpl implements SiteSettingService{
	@Autowired
	private SiteSettingRepository siteSettingRepository;
	
	public SiteSetting updateSiteSetting(SiteSetting siteSetting) {
		return siteSettingRepository.save(siteSetting);	
	}
	
	public SiteSetting findOne(Long id) {
		return siteSettingRepository.findOne(id);	
	}
	
	public List<SiteSetting> findAll() {
		return (List<SiteSetting>) siteSettingRepository.findAll();	
	}

	public void save(SiteSetting siteSettings) {
		siteSettingRepository.save(siteSettings);
		
	}
}
