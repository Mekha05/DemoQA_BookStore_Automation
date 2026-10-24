\# DemoQA Book Store Application – Testing Project



\## 1. Project Overview



This project covers manual testing and automation testing of the DemoQA Book Store Application. The objective is to verify the application's main features, validate user workflows, identify defects, and automate selected test cases.



\## 2. Application Details



\- \*\*Application Name:\*\* DemoQA Book Store Application

\- \*\*Application URL:\*\* https://demoqa.com/books

\- \*\*GitHub Repository:\*\* https://github.com/Mekha05/DemoQA\_BookStore\_Automation



\## 3. Scope of Testing



The project focuses on the following features:



\- Viewing and searching for books

\- Navigating to book details

\- Login functionality and validation

\- New-user registration and validation

\- Managing books in the user's collection

\- Removing books and logging out



Testing is limited to the Book Store Application and its related workflows.



\## 4. Tools and Technologies



\- Java 21

\- Selenium WebDriver

\- TestNG

\- Maven

\- Apache POI for Excel test data

\- Eclipse IDE

\- Git and GitHub



\## 5. Automation Framework



The automation framework follows the Page Object Model (POM).



\- \*\*Base:\*\* Browser setup and common test configuration

\- \*\*Pages:\*\* Page Object classes for application pages

\- \*\*Utils:\*\* Reusable utilities, including Excel handling

\- \*\*Test Cases:\*\* TestNG classes for automated workflows

\- \*\*TestNG XML:\*\* Test suite configuration



\## 6. Manual Testing Documentation



The `Documentation` folder contains the Excel workbook with the project's manual testing materials, including test scenarios, test cases, RTM, bug reports, and test execution details.



\## 7. How to Run the Automation Tests



1\. Clone or download the repository.

2\. Import the project into Eclipse as an existing Maven project.

3\. Configure JDK 21.

4\. Allow Maven to download the project dependencies.

5\. Review the test data and ensure that no real credentials are exposed.

6\. Run the required TestNG test class or suite XML file.

7\. Review the execution output and generated reports.



\## 8. Test Results



The manual testing workbook records 47 executed test cases: 43 passed and 4 failed.



Automation results are recorded separately in the project workbook. Results may vary when tests are executed again because of application behavior, test data, browser differences, or verification requirements.





