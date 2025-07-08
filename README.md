![Chrome Tests](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/sntakirutimana72/ci-e2e-test-automation/badges/xyz-bank-ci-chrome.json)
![Edge Tests](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/sntakirutimana72/ci-e2e-test-automation/badges/xyz-bank-ci-edge.json)
![Firefox Tests](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/sntakirutimana72/ci-e2e-test-automation/badges/xyz-bank-ci-firefox.json)
[![Coverage](https://coveralls.io/repos/github/sntakirutimana72/ci-e2e-test-automation/badge.svg?branch=xyz-bank-app)](https://coveralls.io/github/sntakirutimana72/ci-e2e-test-automation?branch=xyz-bank-app)
[![Test Plan](https://img.shields.io/badge/Test%20Plan-blue)](https://docs.google.com/document/d/1eiAHBsRM5-_U27YXNVEwNpt8n-Yd1MGtNuDMV2GEbf0/edit?tab=t.0)

# Test Automation with Selenium WebDriver for `XYZ Bank App`

Building CI pipeline for e2e test automation with Selenium &amp; Java

## Objectives

- **Configure Selenium for Cross-Browser Parallel Testing**:
  Learn how to leverage **Selenium** various browser drivers for automated cross-browser testing.

- **Implement TestNG for Test Execution**:
  Use **TestNG** annotations to structure test cases effectively.

- **Implement Allure for Test Execution Reporting**:
  Use **Allure** annotations to structure test cases effectively and capture test results.

- **Automate Web Test** – Write and execute **UI tests**.
- Set up a **Continuous Integration (CI) pipeline** using **GitHub Actions** to automate **Selenium tests**.
- **PROJECT: Selenium WebDriver Setup and cross-browser UI Testing**

## Overview:

1. **Setup and configuration**
   - Maven
   - Add Selenium WebDriver, TestNG, and Allure dependencies in the pom.xml

2. **Parallel Cross-Browser Automated Testing**
   - Launch and test using Selenium WebDriver – `XYZ Bank` project

3. **Implement Page Object Model (POM)**
   - Create separate Page Classes for each page.
   - Use locators to identify elements
   - Implement a Page Factory Pattern for better test structure
   
4. **Implement a CI pipeline using GitHub Actions**
   - Create a `.github/workflows/ci.yml` configuration for GitHub Actions.
   - Ensure the workflow is triggered on code push or pull request events.
   - Set up the CI pipeline to automatically install dependencies and run the tests with GitHub Actions.
   - Set up notifications (Slack) to notify a team of build status (pass/fail).
   - Ensure GitHub Actions logs include detailed information on test execution
   - Publish Allure test results with `gh-pages`

## Technologies
- Java `17+`
- Selenium `4.33.0`
- Intellij IDE
- Git `2.43.0`
- GitHub
- GitHub Actions (**CI**)

## How to run locally?

To get started with local setup, do the following:

1. Install **JDK 17**
2. Install all required browsers: `Chrome`, `Firefox`, `Edge`
3. Install **IntelliJ IDEA**
4. Clone project repo ([link](https://github.com/sntakirutimana72/ci-e2e-test-automation))
5. Open cloned project with **IntelliJ IDEA**
6. In **IntelliJ IDE** terminal, run 
  ```shell
  mvn clean test
  ```

  Generate an Allure report locally:
  ```shell
  mvn allure:report
  ```

  Serve an Allure report locally:
  ```shell
  mvn allure:serve
  ```
