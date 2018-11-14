package com.spo.cloptmize.core;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.spo.cloptimize.core.CleanerOptimizeMain;
class CleanerOptimizeMainTest {

	private CleanerOptimizeMain cleanerOptimize;
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testData001() {
		//fail("Not yet implemented");
		int[] roomsInStructures = {35, 21, 17};
		int ccSenior = 10;
		int ccJunior = 6;
		
		HashMap[] testMapData = new HashMap[3];
		HashMap<String, String> testMap1 = new HashMap<String, String>();
		testMap1.put("Seniors", "3");
		testMap1.put("Juniors", "1");
		HashMap<String, String> testMap2 = new HashMap<String, String>();
		testMap2.put("Seniors", "1");
		testMap2.put("Juniors", "2");
		HashMap<String, String> testMap3 = new HashMap<String, String>();
		testMap3.put("Seniors", "2");
		testMap3.put("Juniors", "0");
		
		testMapData[0] = testMap1;
		testMapData[1] = testMap2;
		testMapData[2] = testMap3;
		
		cleanerOptimize = new CleanerOptimizeMain(3);
		
		cleanerOptimize.calculateOptmizedCleanerCount(roomsInStructures, ccJunior, ccSenior);
		HashMap[] cleanerData = cleanerOptimize.getCleanerData();
		Assert.assertEquals(testMapData, cleanerData);
		
		for(HashMap cleanerMap: cleanerData) {
			//System.out.println("hashMap is : "+cleanerMap);
			cleanerMap.forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));
		}
	}

	@Test
	void testData002() {
		//fail("Not yet implemented");
		int[] roomsInStructures = {24, 28};
		int ccSenior = 11;
		int ccJunior = 6;
		
		HashMap[] testMapData = new HashMap[2];
		HashMap<String, String> testMap1 = new HashMap<String, String>();
		testMap1.put("Seniors", "2");
		testMap1.put("Juniors", "1");
		HashMap<String, String> testMap2 = new HashMap<String, String>();
		testMap2.put("Seniors", "2");
		testMap2.put("Juniors", "1");
		
		testMapData[0] = testMap1;
		testMapData[1] = testMap2;
		
		cleanerOptimize = new CleanerOptimizeMain(2);
		
		cleanerOptimize.calculateOptmizedCleanerCount(roomsInStructures, ccJunior, ccSenior);
		HashMap[] cleanerData = cleanerOptimize.getCleanerData();
		Assert.assertEquals(testMapData, cleanerData);
		
		for(HashMap cleanerMap: cleanerData) {
			//System.out.println("hashMap is : "+cleanerMap);
			cleanerMap.forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));
		}
	}

	
}
