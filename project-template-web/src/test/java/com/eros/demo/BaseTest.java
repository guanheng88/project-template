package com.eros.demo;

import java.util.Random;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BaseTest {

	protected int randomInt() {
		Random rand = new Random();
		int i = rand.nextInt();
		i = rand.nextInt(10000);
		return i;
	}
}
