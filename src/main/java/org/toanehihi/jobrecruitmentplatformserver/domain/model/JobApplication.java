package org.toanehihi.jobrecruitmentplatformserver.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.enums.ApplicationStatus;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "job_applications")
public class JobApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_id")
	private Job job;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Builder.Default
    private ApplicationStatus status = ApplicationStatus.SUBMITTED;

	@Column(name = "cv_resource_id")
	private Long cvResourceId;

	@CreationTimestamp
	@Column(name = "applied_at")
	private OffsetDateTime appliedAt;
}


