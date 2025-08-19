1) Unzip the file delivery-app
2) Open cmd prompt and navigate to the file delivery-app

(eg:C:\Users\arulj\OneDrive\Desktop\everestcoding\delivery-app>)

3) To compile 

javac -d out -cp lib\junit-platform-console-standalone-1.10.0.jar src\main\java\*.java -encoding UTF-8  src\test\java\*.java

4) After compilation you will find the .class file in the out directory

5) To run all unit tests

java -jar lib\junit-platform-console-standalone-1.10.0.jar -cp out --scan-class-path

6) To run delivery cost estimation ( problem 1)-(DeliveryCostEstimation)

 java -cp "out;lib\junit-platform-console-standalone-1.10.0.jar" DeliveryCostEstimation

Give enter -> copy paste the below input -> enter
sample input:

100 3
PKG1 5 5 OFR001
PKG2 15 5 OFR002
PKG3 10 100 OFR003

sample output:

PKG1 0 175
PKG2 0 275
PKG3 35 665


6) To run delivery Time estimation ( problem 2)-(DeliveryTimeEstimation)

java -cp "out;lib\junit-platform-console-standalone-1.10.0.jar" DeliveryTimeEstimation

Give enter -> copy paste the below input -> enter

sample input:

100 5
PKG1 50 30 OFR001
PKG2 75 125 OFFR0008
PKG3 175 100 OFFR003
PKG4 110 60 OFFR002
PKG5 155 95 OFFR009
2 70 200

sample output:

PKG1 0 750 3.98
PKG2 0 1475 1.78
PKG3 0 2350 1.42
PKG4 105 1395 0.85
PKG5 0 2125 4.19