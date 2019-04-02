# ethoca-online-shopping
This is a demo automation project that automates the basic flow of selecting a product, registering an account and proceeding through checkout in the application "http://automationpractice.com"

Framework/Tech stack
====================
Gradle - Build & Dependency management
Java 8
Selenium 3.14.0 - Page Object Model
Cucumber - BDD framework
Cucumber Guice - Dependency Injection
Log4j2 - Logging
ExtentReports - Reporting

Project Structure
=================
Modules:
etc-qa-glue  ***** Contains Cucumber features(scenarios) and step definitions
etc-qa-steps ***** Contains Steps implementation
etc-qa-ui    ***** Contains UI Pages, webelements & actions, reporting and logging utils

This structure contains one scenario in glue layer - src/test/features/

How to execute the test
=======================
Command line -> Navigate to project path -> ./gradlew build
This will trigger the test and execute on Mac OS & Chrome browser by default.
Edit the file at etc-qa-glue/src/test/resources/cucumber.properties to modify the OS and browser type

Report
======
A Custom report showing the test and steps result would be generated at the location /output/reports/glue/index.html
Open the html file on browser to view the test results

Logging
=======
Test step logs would be generated after test execution and can be logged into ecommere.log file

