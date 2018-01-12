package com.alibaba.druid.spring.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.spring.boot.dao.daointerface.IUuidDao;
import com.alibaba.druid.spring.boot.service.svcinterface.IUuidService;

@Service
public class IUuidServiceImpl implements IUuidService {

	@Autowired
	protected IUuidDao uuidDao;

	@Override
	public String get() {
		return uuidDao.get();
	}
	
}
