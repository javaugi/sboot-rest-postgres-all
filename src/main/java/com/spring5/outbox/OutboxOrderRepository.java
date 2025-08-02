package com.spring5.outbox;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface OutboxOrderRepository extends JpaRepository<OutboxOrder, Long> {
}
