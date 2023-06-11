package com.robotdr.hotel_api.repository;

import com.robotdr.hotel_api.domain.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    Optional<Visitor> findVisitorByLastNameOrPassport(String lastNameOrPassport);
}
