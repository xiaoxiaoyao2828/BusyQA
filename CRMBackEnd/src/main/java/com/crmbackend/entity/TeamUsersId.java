package com.crmbackend.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TeamUsersId implements Serializable {
	public TeamUsersId(Integer id, Integer id2) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "team_id")
	private Integer teamId;

	@Column(name = "user_id")
	private Integer userId;
}