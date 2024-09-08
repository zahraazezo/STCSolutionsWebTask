# STC Banking Automation Project

## Overview

This project automates testing for the **STC Banking Application** using Selenium WebDriver, TestNG, REST-assured, and Allure reporting. The test framework is built using Java with Maven as the build tool. It also includes integration with WebDriverManager for browser driver management, Log4j for logging, and OpenCSV for handling CSV files.

## Project Structure


- `src/main/java`: Contains main application code (if any).
- `src/test/java`: Contains test classes, step definitions, and test automation logic.
- `src/test/resources`: Contains test-related configurations such as TestNG XML, log configuration, and test data.
- `pom.xml`: Maven configuration and dependencies.

## Features

- **API Testing**: Automated API tests using REST-assured.
- **Web UI Testing**: Selenium WebDriver for automating browser-based UI testing.
- **TestNG Framework**: Used to manage test execution and reporting.
- **Logging**: Integrated with Log4j for detailed logging.
- **Allure Reporting**: Generates detailed and attractive test reports.
- **WebDriverManager**: Automatically manages browser drivers, so no manual driver setup is needed.
- **OpenCSV**: Provides CSV handling capabilities.

## Technologies Used

- **Java 11**
- **Selenium 4.20.0**
- **TestNG 7.7.1**
- **REST-assured**
- **Log4j 2.17.2**
- **WebDriverManager 5.9.2**
- **Allure TestNG 2.13.9**
- **OpenCSV 5.5.2**

## Prerequisites

- **Java 11** or higher
- **Maven 3.6.0** or higher
- **Allure** for reporting (optional, but recommended)

## Installation and Setup

1. Clone the repository:
   git clone https://github.com/your-repo/stc-banking-automation.git
Navigate to the project directory:

cd stc-banking-automation
Install dependencies and build the project:


mvn clean install
Running the Tests
To execute the tests, use the following Maven command:

mvn test
This will trigger the TestNG test suite defined in TestNG.xml.

## Generating Allure Report
Run the tests:

mvn test

## Generate the Allure report:

mvn allure:serve

The Allure report will automatically open in your browser.

## Logging
Logs are generated using Log4j and are stored in the logs directory. You can configure the log level and log location in the log4j2.xml configuration file.
