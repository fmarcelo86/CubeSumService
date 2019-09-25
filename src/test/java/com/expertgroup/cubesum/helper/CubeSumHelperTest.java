package com.expertgroup.cubesum.helper;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CubeSumHelperTest {
	@InjectMocks
	private CubeSumHelper cubeSumHelper;

	@Before
	public void init() {
		cubeSumHelper = new CubeSumHelper(4);
		cubeSumHelper.update(2, 2, 2, 4);
	}

	@Test
	public void updateTest() {
		cubeSumHelper.update(2, 2, 2, 4);
	}

	@Test
	public void queryTest() {
		long result = cubeSumHelper.query(1, 1, 1, 3, 3, 3);
		assertEquals(result, 4L);
	}
}
