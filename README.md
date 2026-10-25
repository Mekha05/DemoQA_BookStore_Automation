# DemoQA Book Store – Manual and Automation Testing

**Submitted by:** Mekha S Murali | **Batch:** S25
**Application under test:** https://demoqa.com/books (DemoQA Book Store Application)

## 1. Project Overview

This capstone project covers manual testing and automation testing of the DemoQA Book Store Application. The manual part focuses on understanding the application, writing test scenarios and test cases, executing them and reporting defects. The automation part covers the most important repeatable business workflow using Selenium WebDriver, TestNG and Maven.

## 2. Scope of Testing

- Viewing and searching for books
- Navigating to book details
- Login functionality and validation
- New user registration and validation
- Adding books to the user's collection and removing them
- Logout

DemoQA has no cart or checkout, so the equivalent business flow tested is:
Login > Search > Book Details > Add to Collection > Profile > Delete > Logout.

## 3. Manual Testing

All manual testing documents are in the `Documentation` folder (Excel workbook).

| Sheet | Content |
|---|---|
| Features List, Test Plan | Application features and test plan |
| Test Scenarios | 18 test scenarios (TS000 to TS017) |
| Page Features, Search, Verification, Login Page, Registration, Profile | 47 test cases with steps, expected and actual results, status |
| RTM | Requirements mapped to test cases, with status |
| Bug Report | Defects with steps, severity, priority and video proof |
| Test Execution Report | Execution summary |
| Automation | Automation results |

**Manual execution summary:** 47 test cases executed, 43 passed, 4 failed (pass rate 91.5%).

**Defects found**

| Bug ID | Test Case | Title | Severity |
|---|---|---|---|
| BUG-001 | TC045 | Whitespace in the username is not validated on the Login page | Minor |
| BUG-002 | TC046 | Delete All Books does not update the collection after confirmation | Major |
| BUG-003 | TC048 | Book cover images do not match titles and are repeated | Minor |
| BUG-004 | TC047 | Book cover differs between the Book Store and the user collection | Minor |

## 4. Automation Testing

**Technologies:** Java 21, Selenium WebDriver 4, Maven, TestNG, Page Object Model, Apache POI (Excel), Eclipse IDE, Git and GitHub.

**Project structure**

```
DemoQA_BookStore_Automation
├── pom.xml
├── testng.xml                       run all tests on Chrome
├── testng-crossbrowser.xml          run all tests on Chrome and Firefox
├── src/main/java
│   ├── base       BaseTest (browser start and close)
│   ├── pages      LoginPage, RegisterPage, BookstorePage, BookDetailsPage
│   └── utils      ExcelUtils (reads Excel using Apache POI)
├── src/test/java/testcases
│   ├── LoginDataDrivenTest          data-driven login (positive and negative)
│   ├── RegistrationDataDrivenTest   data-driven registration
│   ├── BookStoreTest                book list, search, negative search
│   ├── BookDetailsTest              title and author verification
│   └── BookStoreEndToEndTest        full business flow
└── src/test/resources/testdata
    └── DataTable.xlsx               LoginData and Registration sheets
```

**Framework features**
- Page Object Model: one class per page, tests contain only steps and assertions
- Explicit waits (`WebDriverWait` with `ExpectedConditions`)
- Assertions with TestNG `Assert`
- Data-driven testing with `@DataProvider` and Excel (Apache POI)
- Cross-browser execution using `@Parameters` and `testng-crossbrowser.xml`
- Test data kept separate from the scripts

**Automated scenarios:** login (valid, invalid, blank), registration, book list, search (positive and negative), book details verification, add to collection, remove from collection, logout and the end-to-end flow.

## 5. How to Run the Tests

1. Install JDK 21, Maven, Eclipse, Google Chrome and Firefox.
2. Clone this repository and import it into Eclipse as an existing Maven project.
3. Let Maven download the dependencies.
4. Right-click `testng.xml` and choose Run As > TestNG Suite.
5. For cross-browser execution, run `testng-crossbrowser.xml` the same way.
6. Results are shown in the Eclipse TestNG panel and in the `test-output` folder.

The test account in the Excel data is a demo account created only for this project.

## 6. Automation Results

| Suite | Total | Passed | Failed | Skipped |
|---|---|---|---|---|
| testng.xml (Chrome) | 18 | 15 | 0 | 3 |
| testng-crossbrowser.xml (Chrome and Firefox) | 36 | 31 | 2 | 3 |


**Known limitation:** the three registration data rows are skipped in Chrome because DemoQA shows reCAPTCHA ("Please verify reCaptcha to register!"), which needs manual verification. Results in Firefox can differ because the site responds differently there.
