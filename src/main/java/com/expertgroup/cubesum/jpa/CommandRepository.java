package com.expertgroup.cubesum.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expertgroup.cubesum.entity.Command;

public interface CommandRepository extends JpaRepository<Command, Long> {

}
