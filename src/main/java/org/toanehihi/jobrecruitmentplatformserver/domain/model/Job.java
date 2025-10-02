package org.toanehihi.jobrecruitmentplatformserver.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.enums.JobStatus;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.enums.SeniorityLevel;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.enums.WorkMode;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
 

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "jobs")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;

	@Column(name = "title")
	private String title;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_role_id")
	private JobRole jobRole;

	@Enumerated(EnumType.STRING)
	@Column(name = "seniority")
	private SeniorityLevel seniority;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id")
	private Location location;

	@Enumerated(EnumType.STRING)
	@Column(name = "work_mode")
	private WorkMode workMode;

	@Column(name = "salary_min")
	private Integer salaryMin;

	@Column(name = "salary_max")
	private Integer salaryMax;

	@Column(name = "currency")
	private String currency;

	@Column(name = "description", columnDefinition = "text")
	private String description;

	@Column(name = "date_posted")
	private OffsetDateTime datePosted;

	@Column(name = "date_expires")
	private OffsetDateTime dateExpires;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private JobStatus status;

	@OneToMany(mappedBy = "job")
	@Builder.Default
	private Set<JobSkillRequirement> skillRequirements = new HashSet<>();
}


