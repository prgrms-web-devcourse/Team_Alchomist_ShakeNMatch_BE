package com.shake_match.alchomist.group.repository;

import com.shake_match.alchomist.group.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findByName(String name);
}
