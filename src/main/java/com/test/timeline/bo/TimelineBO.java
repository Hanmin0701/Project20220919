package com.test.timeline.bo;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.common.fileManagerService;
import com.test.timeline.dao.TimelineDAO;

@Service
public class TimelineBO {
	
	@Autowired
	private TimelineDAO timelineDAO;
	
	@Autowired
	private fileManagerService fileManagerService;
	
	
}
