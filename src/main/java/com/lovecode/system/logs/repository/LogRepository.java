package com.lovecode.system.logs.repository;

import com.lovecode.system.logs.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Integer> {
}
