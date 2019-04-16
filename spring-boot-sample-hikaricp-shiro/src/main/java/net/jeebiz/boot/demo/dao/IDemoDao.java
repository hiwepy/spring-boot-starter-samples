/** 
 * Copyright (C) 2018 Jeebiz (http://jeebiz.net).
 * All Rights Reserved. 
 */
package net.jeebiz.boot.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.jeebiz.boot.api.dao.BaseDao;
import net.jeebiz.boot.demo.dao.entities.DemoModel;

@Mapper
public interface IDemoDao extends BaseDao<DemoModel>{

	/**
	 * 获取指定ID关联的信息，以便进行删除前的逻辑检查
	 * @param list
	 * @return
	 */
	List<Map<String,String>> getDependencies(List<String> list);
	
}
