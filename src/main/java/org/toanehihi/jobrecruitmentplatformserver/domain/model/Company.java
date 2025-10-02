package org.toanehihi.jobrecruitmentplatformserver.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "companies")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "website")
	private String website;

	@Column(name = "size")
	private String size;

	@Column(name = "logo_resource_id")
	private Long logoResourceId;

    @Column(name = "verified")
    @Builder.Default
    private boolean verified = false;

	@CreationTimestamp
	@Column(name = "date_created")
	private OffsetDateTime dateCreated;

	@OneToOne(mappedBy = "company", fetch = FetchType.LAZY)
	private Recruiter recruiter;

    @OneToMany(mappedBy = "company")
    @Builder.Default
    private Set<CompanyLocation> companyLocations = new HashSet<>();
}


