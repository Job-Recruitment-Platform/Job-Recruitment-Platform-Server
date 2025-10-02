package org.toanehihi.jobrecruitmentplatformserver.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "locations")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "city")
	private String city;

	@Column(name = "country")
	private String country;

	@Column(name = "lat")
	private java.math.BigDecimal lat;

	@Column(name = "lng")
	private BigDecimal lng;

	@Column(name = "unique_key", length = 240, unique = true)
	private String uniqueKey;
}


