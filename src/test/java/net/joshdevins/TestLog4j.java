/*  
 * @(#) TestLog4j.java Create on 2014-8-8 下午5:22:50   
 *   
 * Copyright 2014 by yhx.   
 */


package net.joshdevins;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @TestLog4j.java
 * @created at 2014-8-8 下午5:22:50 by zhanghl
 *
 * @desc
 *
 * @author zhanghl({@link 253587517@qq.com})
 * @version $Revision$
 * @update: $Date$
 */
public class TestLog4j {
	Logger logger = LoggerFactory.getLogger(TestLog4j.class);
	
	@Test
	public void testLog(){
		logger.debug("debug");
		logger.info("info");
	}
}
