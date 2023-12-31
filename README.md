# Galaxy Merchant Trading Guide

This project is a guide for merchants in the galaxy where the numbers are represented in Roman numerals.

## Problem Statement
You decided to give up on earth after the latest financial collapse left 99.99% of the earth's population with 0.01% of the wealth. Luckily, with the scant sum of money that is left in your account, you are able to afford to rent a spaceship, leave earth, and fly all over the galaxy to sell common metals and dirt (which apparently is worth a lot). Buying and selling over the galaxy requires you to convert numbers and units, and you decided to write a program to help you.The numbers used for intergalactic transactions follows similar convention to the roman numerals and you have painstakingly collected the appropriate translation between them. Roman numerals are based on seven symbols:

<table>
<tr>
<th>Symbol</th> 
<th>Value</th>
</tr>
<tr>
<td>I</td><td>1</td>
</tr>
<tr>
<td>V</td><td>5</td>
</tr>
<tr>
<td>X</td><td>10</td>
</tr>
<tr>
<td>L</td><td>50</td>
</tr>
<tr>
<td>C</td><td>100</td>
</tr>
<tr>
<td>D</td><td>500</td>
</tr>
<tr>
<td>M</td><td>1,000</td>
</tr>
</table>

Numbers are formed by combining symbols together and adding the values. For example, MMVI is 1000 + 1000 + 5 + 1 = 2006. Generally, symbols are placed in order of value, starting with the largest values. When smaller values precede larger values, the smaller values are subtracted from the larger values, and the result is added to the total. For example MCMXLIV = 1000 + (1000 − 100)+(50−10)+(5−1)=1944.

The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more. (They may appear four times if the third and fourth are separated by a smaller value, such as XXXIX.)
"D", "L", and "V" can never be repeated.

"I" can be subtracted from "V" and "X" only. "X" can be subtracted from "L" and "C" only. "C" can
be subtracted from "D" and "M" only. "V", "L", and "D" can never be subtracted.
Only one small-value symbol may be subtracted from any large-value symbol.

A number written in Arabic numerals can be broken into digits. For example, 1903 is composed of
1, 9, 0, and 3. To write the Roman numeral, each of the non-zero digits should be treated separately. In the above example, 1,000 = M, 900 = CM, and 3 = III. Therefore, 1903 = MCMIII.

-- Source: Wikipedia (http://en.wikipedia.org/wiki/Roman_numerals)"I" can be subtracted from "V" and "X" only. "X" can be subtracted from "L" and "C" only. "C" can
be subtracted from "D" and "M" only. "V", "L", and "D" can never be subtracted.
Only one small-value symbol may be subtracted from any large-value symbol.

A number written in Arabic numerals can be broken into digits. For example, 1903 is composed of
1, 9, 0, and 3. To write the Roman numeral, each of the non-zero digits should be treated separately. In the above example, 1,000 = M, 900 = CM, and 3 = III. Therefore, 1903 = MCMIII.

-- Source: Wikipedia (http://en.wikipedia.org/wiki/Roman_numerals)

### Sample Input
```bash
glob is I
prok is V
pish is X
tegj is L
glob glob Silver is 34 Credits
glob prok Gold is 57800 Credits
pish pish Iron is 3910 Credits
how much is pish tegj glob glob ?
how many Credits is glob prok Silver ?
how many Credits is glob glob Gold ?
how many Credits is glob glob glob glob glob glob Gold ?
how many Credits is pish tegj glob Iron ?
Does pish tegj glob glob Iron has more Credits than glob glob Gold ?
Does glob glob Gold has less Credits than pish tegj glob glob Iron?
Is glob prok larger than pish pish?
Istegj glob glob smaller than glob prok?
how much wood could a woodchuck chuck if a woodchuck could chuck wood ?
```

### Sample Output
```bash
pish tegj glob glob is 42
glob prok Silver is 68 Credits
glob glob Gold is 28900 Credits
Requested number is in invalid format
pish tegj glob Iron is 8015.5 Credits
pish tegj glob glob Iron has less Credits than glob prok Gold
glob glob Gold has more Credits than pish tegj glob glob
glob prok is smaller than pish pish
tegj glob glob is larger than glob prok
I have no idea what you are talking about
```

## System Design

This application is a console-based Java application developed using Maven. The application is designed to convert intergalactic units to Roman numerals and perform arithmetic operations on them.

The application follows a modular design with separate classes handling different responsibilities:

- `Main`: The entry point of the application.
- `InputHandler`: Handles reading input from a file.
- `OutputHandler`: Handles writing output to the console.
- `InputParser`: Parses the input and performs the necessary operations.
- `QueryProcessor`: Processes the queries and returns the result.

## Assumptions

The following nontrivial assumptions were made during the development of this application:

- The input file is always present in the resources directory and is correctly formatted in case of input mode selection is file based.
- The intergalactic units and their corresponding Roman numerals are defined in the input file in case of input mode selection is file based.
- The arithmetic operations are always valid and do not result in any undefined behavior like division by zero.
- The user can give input both using input file and console.
- The user can give output using console.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 8 or higher
- Maven

### Running the project

A step by step series of examples that tell you how to get a development environment running.

1. Clone the repository
```bash
git clone https://github.com/gnanee57/galaxy-merchant.git
```
2. Navigate to the project directory
```bash
cd galaxy-merchant
```
3. Compiling the Application
```bash
mvn clean install
```
4. Run the Application
```bash
mvn exec:java
```
### Running the Tests
To run the tests, run the following command
```bash
mvn clean test
```

### Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Java 8](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) - Programming Language
* [JUnit 5](https://junit.org/junit5/) - Testing Framework

### Authors
* Gnaneswar Sai Kumar Konduru - Initial work - [gnanee57](https://github.com/gnanee57)