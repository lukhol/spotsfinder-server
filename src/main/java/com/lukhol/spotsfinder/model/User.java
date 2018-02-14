package com.lukhol.spotsfinder.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties({"places"})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private Long id;
	@Email
	private String email;
	@Column(nullable = true)
	private String facebookId;
	@Column(nullable = true)
	private String googleId;
	private String password;
	private String firstname;
	private String lastname;
	private boolean isActive;
	private String avatarUrl;
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Role> roles;
	
	@OneToMany(mappedBy="owner")
	private List<Place> places;
}