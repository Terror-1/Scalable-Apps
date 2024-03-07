package com.sessionservice.sessionservice.repository;

import com.sessionservice.sessionservice.entity.Session;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface SessionRepository extends CassandraRepository<Session, String> {
}
