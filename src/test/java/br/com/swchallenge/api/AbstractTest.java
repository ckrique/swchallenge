package br.com.swchallenge.api;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SwchallengeApplication.class)
@WebAppConfiguration
public abstract class AbstractTest {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
