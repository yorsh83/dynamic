package com.test.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.Formula;

public interface FormulaRepository extends JpaRepository<Formula, Long> {
    List<Formula> findByActivoTrue();
}

