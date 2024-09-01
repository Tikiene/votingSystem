package com.project.voteProgram.controller;

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.voteProgram.dao.VoteTopicDao;
import com.project.voteProgram.model.VoteTopic;

@Controller
@RequestMapping("/topic")
public class VoteTopicController {
	
	@Autowired
	private VoteTopicDao voteTopicDao;
	
	@GetMapping("all")
	public ResponseEntity<List<VoteTopic>> getVoteTopics(){
		List<VoteTopic> res = new ArrayList<>();
		res = voteTopicDao.findAll();
		return ResponseEntity.ok().body(res);
	}
	
	@GetMapping("countAll")
	public ResponseEntity<List<Map<String, Object>>> getCountVoteTopics(){
		List<Map<String, Object>> res = new ArrayList<>();
		res = voteTopicDao.getCountVoteTopics();
		return ResponseEntity.ok().body(res);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<VoteTopic> getVoteTopicBySeqno(@PathVariable Integer id) {
		Optional<VoteTopic> optional = voteTopicDao.findById(id);
		return optional.map(res -> ResponseEntity.ok().body(res)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("edit")
	public ResponseEntity<VoteTopic> updateVoteTopic(@RequestBody VoteTopic voteTopic) {
		VoteTopic res = voteTopicDao.save(voteTopic);
		return ResponseEntity.ok().body(res);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteVoteTopicBySeqno(@PathVariable Integer id) {
		voteTopicDao.getDeleteTopicRecord(id);
		return ResponseEntity.ok().build();
	}
}
