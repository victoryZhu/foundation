package com.yourhealth.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yourhealth.security.domain.SysLog;

public interface SysLogDao extends JpaRepository<SysLog, Integer>,
		JpaSpecificationExecutor<SysLog> {

}
