package org.mybatis.spring.boot.dao.daointerface;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUuidDao {

	String get();
	
	
}
