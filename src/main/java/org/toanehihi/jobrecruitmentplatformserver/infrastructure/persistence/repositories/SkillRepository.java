package org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.Skill;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
	Optional<Skill> findByName(String name);

	boolean existsByName(String name);
}
