package com.project.voteProgram.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="topic")
public class VoteTopic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name="name",nullable=false)
	private String name;

	@OneToMany(mappedBy = "topic",cascade = CascadeType.ALL)
	@JsonManagedReference
	Set<VoteRecord> records = new HashSet<>();
		

	@Override
	public String toString() {
		return "VoteTopic [id=" + id + ", name=" + name + ", records=" + records + "]";
	}


	public VoteTopic() {
		super();
	}


	public VoteTopic(Integer id, String name, Set<VoteRecord> records) {
		super();
		this.id = id;
		this.name = name;
		this.records = records;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<VoteRecord> getRecords() {
		return records;
	}

	public void setRecords(Set<VoteRecord> records) {
		this.records = records;
	}
	

}
