package com.spo.cloptimize.core;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author jeyaprakash Ganesan
 * This class calculates the number of senior and junior cleaners required 
 * for the given number of structures each having a given number of rooms
 *
 */
public class CleanerOptimizeMain {

	private static HashMap[] cleanerData;

	public CleanerOptimizeMain(int structures) {
		cleanerData = new HashMap[structures];
	}

	public static void main(String[] args) {
		System.out.println("Enter the number of structures: ");
		Scanner scanner = new Scanner(System.in);
		try {
			
			int structures = scanner.nextInt();
			int[] roomsInStructures = new int[structures];

			for (int i = 0; i < structures; i++) {
				System.out.println("Enter the number of rooms in Structure " + (i + 1) + ": ");
				roomsInStructures[i] = scanner.nextInt();
			}

			System.out.println("Enter the capacity of a Senior Cleaner: ");
			int ccSenior = scanner.nextInt();
			System.out.println("Enter the capacity of a junior Cleaner: ");
			int ccJunior = scanner.nextInt();

			CleanerOptimizeMain com = new CleanerOptimizeMain(structures);
			com.calculateOptmizedCleanerCount(roomsInStructures, ccJunior, ccSenior);
		} finally {
			scanner.close();
		}
	}

	/**
	 * This method is the method that contains the business logic for the optimizer
	 * @param roomsInStructures array of numbers of rooms in each structures
	 * @param ccOfJuniors work capacity of juniors
	 * @param ccOfSeniors work capacity of seniors
	 */
	public void calculateOptmizedCleanerCount(int[] roomsInStructures, int ccOfJuniors, int ccOfSeniors) {
		for (int i = 0; i < roomsInStructures.length; i++) {
			int roomsInEachStructure = roomsInStructures[i];
			if (roomsInEachStructure > ccOfSeniors) {
				int roomCount = roomsInEachStructure - ccOfSeniors;
				HashMap<String, String> cleanerMap = optimizeAndUpdateCount(roomCount, ccOfJuniors, ccOfSeniors);
				cleanerData[i] = cleanerMap;
			}
		}
	}

	/**
	 * @param roomCount number of rooms 
	 * @param ccOfJuniors work capacity of juniors
	 * @param ccOfSeniors work capacity of seniors
	 * @return hashmap of the count of seniors and juniors
	 */
	private HashMap<String, String> optimizeAndUpdateCount(int roomCount, int ccOfJuniors, int ccOfSeniors) {
		HashMap<String, String> cleanerMap = new HashMap<String, String>();

		float localSeniorsCount = 1;
		float localJuniorsCount = 0;
		if (roomCount > ccOfJuniors) {
			if (roomCount > ccOfSeniors) {
				int balanceRooms = roomCount % ccOfSeniors;
				int modJuniors = roomCount % ccOfJuniors;
				int quotSeniors = roomCount / ccOfSeniors;
				if (balanceRooms > 0) {
					if (modJuniors > 0) {
						if (balanceRooms < ccOfSeniors / 2) {
							localSeniorsCount += (float) roomCount / ccOfSeniors;
						} else {
							localSeniorsCount = (float) roomCount / ccOfSeniors;
						}
						localJuniorsCount++;
						if (modJuniors > ccOfJuniors / 2 && quotSeniors >= 1 && balanceRooms < ccOfJuniors/2) {
							localJuniorsCount = (float) roomCount / ccOfJuniors;
							localSeniorsCount--;
						}
					}
				} else {
					localJuniorsCount += (float) roomCount % ccOfSeniors / ccOfJuniors;
				}
			} else {
				localSeniorsCount++;
			}
		} else {
			localJuniorsCount++;
		}

		cleanerMap.put("Juniors", String.valueOf(Math.round(localJuniorsCount)));
		cleanerMap.put("Seniors", String.valueOf(Math.round(localSeniorsCount)));
		System.out.println("Number of Seniors and Juniors for the structure with rooms "+(roomCount+ccOfSeniors));
		cleanerMap.forEach((k, v) -> System.out.println(k + " Count : " + v));
		return cleanerMap;
	}

	/**
	 * @return array of hashmap of seniors and juniors for each structures
	 */
	public static HashMap[] getCleanerData() {
		return cleanerData;
	}

}
