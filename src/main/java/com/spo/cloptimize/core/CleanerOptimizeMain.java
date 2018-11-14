package com.spo.cloptimize.core;

import java.util.HashMap;
import java.util.Scanner;

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
//						if(roomCount % ccOfSeniors - ccOfJuniors == 0) {
//							localSeniorsCount = (float)roomCount / ccOfSeniors;
//							localJuniorsCount = 
//						}
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
		cleanerMap.forEach((k, v) -> System.out.println("Item : " + k + " Count : " + v));
		return cleanerMap;
	}

	public static HashMap[] getCleanerData() {
		return cleanerData;
	}

}
