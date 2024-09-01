package com.project.voteProgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.voteProgram.dao.VoteRecordDao;
import com.project.voteProgram.model.VoteRecord;

@Controller
@RequestMapping("/voting")
public class VoteRecordController {
	
	@Autowired
	public VoteRecordDao voteRecordDao;
	
	@PutMapping("edit")
	public ResponseEntity<?> updateVoteRecord(@RequestBody VoteRecord voteRecord) {
		VoteRecord res = voteRecordDao.save(voteRecord);
		return ResponseEntity.ok().body(res);
	}
}
