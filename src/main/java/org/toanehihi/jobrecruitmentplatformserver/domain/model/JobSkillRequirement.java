package org.toanehihi.jobrecruitmentplatformserver.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.ids.JobSkillRequirementId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"job", "skill"})
@Entity
@Table(name = "job_skill_requirements")
@IdClass(JobSkillRequirementId.class)
public class JobSkillRequirement {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_id")
	private Job job;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "skill_id")
	private Skill skill;

	@Column(name = "level", nullable = false)
	private Integer level;
}


