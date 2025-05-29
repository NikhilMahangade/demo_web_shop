## 🚀 Project Overview
 
- Implements **Behavior Driven Development** using Gherkin syntax.
- Uses **Page Object Model (POM)** for code reusability and readability.
- Test data is managed through Excel files using `ExcelReader`.
- Logs are generated using **Log4j2**.
- Cucumber HTML Reports for test summaries.
- Designed for easy scaling and maintainability.
 
---
 
## 🧰 Tech Stack
 
- Java SE 1.8
- Maven
- Selenium WebDriver
- Cucumber (Gherkin)
- TestNG
- Log4j2
- Apache POI (for Excel)
- Cucumber Reporting Plugin (for HTML reports)
 
---
 
## 📁 Project Structure
 
```plaintext
Demo Web Shop 
├── pom.xml                      # Maven configuration
├── JRE System Library [Java SE 1.8]
├── Maven Dependencies
├── logs/
│   └── app.log                  # Log4j2 logs
├── TestData/
│   └── LoginTest.xlsx           # Excel file for test data
├── test-output/
│   └── index.html               # TestNG default report
├── target/
│   └── cucumber-reports.html    # Cucumber HTML Report
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
│       ├── java/
│       │   ├── Pages/           # Page Object classes
│ │ │ ├── LoginPage.java
│ │ │ ├── RegistrationPage.java
│ │ │ └── DashboardPage.java
│       │   ├── Stepdefs/        # Step definition classes
│ │ │ ├── LoginPageStepdef.java
│ │ │ ├── RegistrationPageStepdef.java
│ │ │ └── DashboardPageStepdef.java
│       │   ├── Runner/          # Cucumber runner class
│ │ │ └── TestRunner.java
│       │   └── Utils/           # Utility classes
│ │ ├── ExcelReader.java
│ │ └── TestContext.java
│       └── resources/
│           ├── Features/        # Gherkin feature files
│           │   ├── Loginpage.feature
│           │   ├── Registration.feature
│           │   └── DashBoard.feature
│           ├── log4j2.xml       # Logging configuration
│           └── Testing.xml      # TestNG XML suite

````
🧪 How to Run the Tests
🛠 Prerequisites
	- Java 8+
 	- Maven installed and configured 
	- IntelliJ IDEA / Eclipse
 
▶️ Run Using Maven
	- mvn test
	
📜 Sample Feature (Gherkin)
	

   Feature: Login Functionality
 
  	 Scenario: Valid login with correct credentials
    	Given I launch the NopCommerce application
    	When I enter valid username and password
    	And I click on the login button
    	Then I should be redirected to the dashboard page
    	
📋 Reports
	- 📄Cucumber HTML Report: target/cucumber-reports.html
 	- 📄TestNG Default Report: test-output/index.html
    - 🧾Log File: logs/app.log
 
📦 Test Data
	- TestData/LoginTest.xlsx
	```Handled by: ExcelReader.java utility.```
	
 
📌 Utilities Included
Utility 					Description
ExcelReader 				Reads input test data from Excel
TestContext				 	Stores data across steps in scenarios
Log4j2 						Logging of events and test execution
 
🗃 Features Automated
	✅Login Page – Valid and invalid login tests
 	✅Registration Page – New user registration
 	✅Dashboard – UI element verification post-login
 
🧱 Design Patterns
	✅Page Object Model (POM)
	✅Behavior Driven Development (Cucumber)
	✅Data-Driven Testing (via Excel)
	✅Modular and Reusable Design
	
👨‍💻 Authors
Nikhil Mahangade | Anup Mahto | Nishad Kurutkar
📍 Based in Pune, BE in Computer Science
💼 Experienced in Java, BDD, Selenium, and API Testing


