package com.project.voteProgram.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.voteProgram.model.VoteRecord;

public interface VoteRecordDao extends JpaRepository<VoteRecord, Integer> {

}
