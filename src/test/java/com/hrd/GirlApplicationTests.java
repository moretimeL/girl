package com.hrd;

import com.hrd.com.hrd.Entity.comment;
import com.hrd.repository.CommentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlApplicationTests {

	@Test
	public void contextLoads() {
	}


	@Autowired
	private CommentRepository commentRepository;

	@Test
	public void pageTest(){


		Page<comment> page1 = commentRepository.findComment("1", PageRequest.of(0,10));

		assertEquals(10,page1.getSize());

		assertEquals(10,page1.getTotalPages());

		assertEquals(100,page1.getTotalElements());


	}
}
