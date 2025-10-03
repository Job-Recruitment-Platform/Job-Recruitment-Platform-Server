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

    @Column(name = "street_address")
    private String streetAddress;    // số nhà, tên đường

    private String ward;             // phường/xã

    private String district;         // quận/huyện/thị xã

    @Column(name = "province_city")
    private String provinceCity;     // tỉnh hoặc thành phố trực thuộc TW

    private String country;          // quốc gia

	@Column(name = "lat")
	private BigDecimal lat;

	@Column(name = "lng")
	private BigDecimal lng;

}


