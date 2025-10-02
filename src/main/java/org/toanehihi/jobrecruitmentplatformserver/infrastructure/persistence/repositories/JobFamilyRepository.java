package org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.JobFamily;

import java.util.Optional;

@Repository
public interface JobFamilyRepository extends JpaRepository<JobFamily, Long> {
	Optional<JobFamily> findBySlug(String slug);
}


