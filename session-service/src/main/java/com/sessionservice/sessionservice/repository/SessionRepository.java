package com.sessionservice.sessionservice.repository;

import com.sessionservice.sessionservice.entity.Session;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SessionRepository extends CassandraRepository<Session, String> {
}
