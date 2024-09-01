package com.project.voteProgram.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.project.voteProgram.model.VoteTopic;

import jakarta.transaction.Transactional;

public interface VoteTopicDao extends JpaRepository<VoteTopic, Integer>{
	
	@Query(value = "select  topic.id, topic.name , count(record.*) as voteCount from topic "
			+ "left join record on topic.id = record.topic_id "
			+ "group by topic.id, topic.name", nativeQuery = true)
	public List<Map<String, Object>> getCountVoteTopics();
	
	//@Query(value = "call sp_delete_topic_record(?)", nativeQuery = true)
	@Modifying
	@Transactional
	@Procedure(procedureName = "sp_delete_topic_record")
	public void getDeleteTopicRecord(Integer id);

}
