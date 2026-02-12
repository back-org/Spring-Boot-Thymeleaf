package com.patdimby.simplerest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Collection;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "roles")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "first_name", nullable = false)
	@NotBlank(message = "Name is mandatory.")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "username")
	private String username;

	@Email(message = "Invalid email address")
	@NotBlank(message = "Email is mandatory")
	@Column(nullable = false, unique = true)
	private String email;

	@NotBlank(message = "Password cannot be blank")
	@Column(nullable = false)
	private String password;

	@Transient
	private String confirmpassword;

	@NotNull(message = "Role is mandatory")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role;

	@Enumerated(EnumType.STRING)
	private Sex sex;

	@ManyToMany(fetch = FetchType.EAGER,
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
	)
	private Collection<Role> roles;
}
