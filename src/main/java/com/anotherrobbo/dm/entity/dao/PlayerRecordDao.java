package com.anotherrobbo.dm.entity.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import com.anotherrobbo.dm.entity.PlayerRecord;
import com.google.inject.persist.Transactional;

public class PlayerRecordDao {
	
	@Inject
	Provider<EntityManager> em;
	
	public List<PlayerRecord> findAll() {
		TypedQuery<PlayerRecord> query = em.get().createNamedQuery("PlayerRecord.findAll", PlayerRecord.class);
		List<PlayerRecord> resultList = query.getResultList();
		return resultList;
	}

	public PlayerRecord findPlayer(String system, String name) {
		TypedQuery<PlayerRecord> query = em.get().createNamedQuery("PlayerRecord.findBySystemName", PlayerRecord.class);
		query.setParameter("system", StringUtils.upperCase(system));
		query.setParameter("name", StringUtils.upperCase(name));
		List<PlayerRecord> resultList = query.getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}
	
	@Transactional
	public void save(PlayerRecord pr) {
		em.get().merge(pr);
	}

}
