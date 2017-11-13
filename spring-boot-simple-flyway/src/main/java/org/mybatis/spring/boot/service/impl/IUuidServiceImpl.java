package org.mybatis.spring.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.mybatis.spring.boot.dao.daointerface.IUuidDao;
import org.mybatis.spring.boot.service.svcinterface.IUuidService;

@Service
public class IUuidServiceImpl implements IUuidService {

	@Autowired
	protected IUuidDao uuidDao;

	@Override
	public String get() {
		return uuidDao.get();
	}
	
}
