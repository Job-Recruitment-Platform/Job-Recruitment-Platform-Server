package org.toanehihi.jobrecruitmentplatformserver.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.enums.AccountStatus;
import org.toanehihi.jobrecruitmentplatformserver.domain.model.enums.AuthProvider;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "accounts")
public class Account implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password")
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, columnDefinition = "account_status")
	@Builder.Default
	private AccountStatus status = AccountStatus.ACTIVE;

	@Enumerated(EnumType.STRING)
	@Column(name = "provider", nullable = false, columnDefinition = "auth_provider")
	private AuthProvider provider;

	@Column(name = "verified_at")
	private OffsetDateTime verifiedAt;

	@CreationTimestamp
	@Column(name = "date_created", nullable = false)
	private OffsetDateTime dateCreated;

	@UpdateTimestamp
	@Column(name = "date_updated", nullable = false)
	private OffsetDateTime dateUpdated;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + role.getName()));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return status != AccountStatus.SUSPENDED;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return status == AccountStatus.ACTIVE;
	}
}
