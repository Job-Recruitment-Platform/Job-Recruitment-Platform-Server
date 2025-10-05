package org.toanehihi.jobrecruitmentplatformserver.infrastructure.persistence.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @NonNull
    Page<Job> findAll(@NonNull Pageable pageable);
}
