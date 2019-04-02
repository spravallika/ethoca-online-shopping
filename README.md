# ethoca-online-shopping
This is a demo automation project that automates the basic flow of selecting a product, registering an account and proceeding through checkout in the application "http://automationpractice.com"

Pre-requisites
==============
JDK 8

Framework/Tech stack
====================
Gradle (Bundled) - Build & Dependency management <br/>
Selenium 3.14.0 - Page Object Model<br/>
Cucumber - BDD framework<br/>
Cucumber Guice - Dependency Injection<br/>
Log4j2 - Logging<br/>
ExtentReports - Reporting<br/>

Project Structure
=================
<b>Modules </b> <br/>
etc-qa-glue  ***** Contains Cucumber features(scenarios) and step definitions <br/>
etc-qa-steps ***** Contains Steps implementation  <br/>
etc-qa-ui    ***** Contains UI Pages, webelements & actions, reporting and logging utils  <br/>

This structure contains one scenario in glue layer - src/test/features/  <br/>

How to execute the test
=======================
<b>Windows </b><br/>
Command Prompt -> Navigate to project path -> gradlew.bat clean build -i <br/>

<b>Mac OS/Linux </b><br/>
Terminal -> Navigate to project path -> ./gradlew clean build -i <br/>

This will trigger the test and execute on Chrome browser by default.  <br/>
Edit the file at etc-qa-glue/src/test/resources/cucumber.properties to modify the OS and browser type  <br/>

Report
======
A Custom report showing the test and steps result would be generated at the location <b> /output/reports/glue/index.html </b>  <br/>
Open the html file on browser to view the test results  <br/>

Logging
=======
This project uses log4j2 for application logging. Test step logs are written to ecommere.log file  <br/>

