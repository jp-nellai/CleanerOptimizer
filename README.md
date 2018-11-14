# CleanerOptimizer
This is the project that allows to automatically calculate the number of senior and junior cleaners for the required structures each having number of rooms to be cleaned. 

# Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

# Prerequisites
JDK 1.8, Maven

# Setup

Install jdk 1.8
Install Maven
run mvn clean package

CleanersOptimization-0.0.1-SNAPSHOT.jar file will be created under the target folder of the project

Run the application with the following command
java -jar CleanersOptimization-0.0.1-SNAPSHOT.jar

# Running the tests
Once the application is called, it will prompt for number of structures, number of rooms in each structures and capacity of seniors and juniors.

Output will be printed after the inputs

java -jar CleanersOptimization-0.0.1-SNAPSHOT.jar
Enter the number of structures:
2
Enter the number of rooms in Structure 1:
24
Enter the number of rooms in Structure 2:
28
Enter the capacity of a Senior Cleaner:
11
Enter the capacity of a junior Cleaner:
6

# Test
unit test code have detailed coverage of sample test cases
CleanerOptimizeMainTest.java

# Authors
Jeyaprakash Ganesan
