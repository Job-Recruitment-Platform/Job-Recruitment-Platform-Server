package org.toanehihi.jobrecruitmentplatformserver.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.ids.CompanyLocationId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"company", "location"})
@Entity
@Table(name = "company_location")
@IdClass(CompanyLocationId.class)
public class CompanyLocation {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id")
	private Location location;

	@Column(name = "is_headquarter")
	private Boolean isHeadquarter;
}


