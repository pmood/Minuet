package com.utility;

import java.util.Random;

public class RandomNumberCustom {

	public static int getRandomNumber() {
		Random rand = new Random(); //instance of random class
	      int upperbound = 99999999;
	        //generate random values from 0-24
	      int int_random = rand.nextInt(upperbound); 
	      System.out.println(int_random);
		return int_random;
	      
	}

}
