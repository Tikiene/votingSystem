package com.project.voteProgram.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="record")
public class VoteRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="voter", nullable=false)
	private String voter;
	
	//連到Topic的id
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="topic_id")
	private VoteTopic topic;

	
	
	@Override
	public String toString() {
		return "VoteRecord [id=" + id + ", voter=" + voter + ", topic=" + topic + "]";
	}

	public VoteRecord() {
		super();
	}

	public VoteRecord(Integer id, String voter, VoteTopic topic) {
		super();
		this.id = id;
		this.voter = voter;
		this.topic = topic;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVoter() {
		return voter;
	}

	public void setVoter(String voter) {
		this.voter = voter;
	}

	public VoteTopic getTopic() {
		return topic;
	}

	public void setTopic(VoteTopic topic) {
		this.topic = topic;
	}
	
	
	

}
