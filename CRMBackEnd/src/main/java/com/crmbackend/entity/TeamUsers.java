package com.crmbackend.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "team_users")

public class TeamUsers {
	public enum Status {
		ACTIVE, INACTIVE, UNKNOWN
	}

	@EmbeddedId
	private TeamUsersId id;

	@ManyToOne
	@MapsId("teamId")
	@JoinColumn(name = "team_id")
	@JsonIgnoreProperties("team_users")

	private Team team;

	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties("team_users")

	private User user;

	@Column(name = "active")
	@Enumerated(EnumType.STRING)
	private Status active;

	public TeamUsers(Team team, User user, Status active) {
		super();
		this.id = new TeamUsersId(team.getId(), user.getId());
		this.team = team;
		this.user = user;
		this.active = active;
	}

	public TeamUsersId getId() {
		return id;
	}

	public void setId(TeamUsersId id) {
		this.id = id;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getActive() {
		return active;
	}

	public void setActive(Status active) {
		this.active = active;
	}

}