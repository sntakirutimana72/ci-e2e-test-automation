![Tests](https://github.com/sntakirutimana72/ci-e2e-test-automation/actions/workflows/ci.yml/badge.svg)
[![Coverage](https://coveralls.io/repos/github/sntakirutimana72/ci-e2e-test-automation/badge.svg?branch=main)](https://coveralls.io/github/sntakirutimana72/ci-e2e-test-automation?branch=main)

# Test Automation with Selenium WebDriver

Building CI pipeline for e2e test automation with Selenium &amp; Java

## Objectives

- **Set Up Selenium WebDriver**:
  Learn how to install and configure **Selenium WebDriver** with **ChromeDriver** for automated browser testing.

- **Implement JUnit 5 for Test Execution**:
  Use **JUnit 5** annotations to structure test cases effectively.

- **Automate a Simple Web Test** – Write and execute a **basic UI test**.
- Set up a **Continuous Integration (CI) pipeline** using **GitHub Actions** to automate **Selenium tests**.
- **PROJECT: Selenium WebDriver Setup and Basic UI Testing**

## Overview:

1. **Setup and configuration**
   - Maven
   - Add Selenium WebDriver and Junit 5 dependencies in the pom.xml

2. **Basic Automated Test**
   - Launch and test using Selenium WebDriver – `Newsletter sign-up form with success message` project

3. **Implement Page Object Model (POM)**
   - Create separate Page Classes for each page.
   - Use locators to identify elements
   - Implement a Page Factory Pattern for better test structure
   
4. **Implement a CI pipeline using GitHub Actions**
   - Create a `.github/workflows/ci.yml` configuration for GitHub Actions.
   - Ensure the workflow is triggered on code push or pull request events.
   - Set up the CI pipeline to automatically install dependencies and run the tests with GitHub Actions.
   - Set up notifications (via email or Slack) to notify a team of build status (pass/fail).
   - Ensure GitHub Actions logs include detailed information on test execution

## Technologies
- Java `17+`
- Selenium `4.25.0`
- Intellij IDE
- Git `2.43.0`
- GitHub
- GitHub Actions (**CI**)

## How to run locally?

To get started with local setup, do the following:

1. Install **JDK 17**
2. Install **IntelliJ IDEA**
3. Clone project repo ([link](https://github.com/sntakirutimana72/ci-e2e-test-automation))
4. Open cloned project with **IntelliJ IDEA**
5. In **IntelliJ IDE** terminal, run 
  ```shell
  mvn test
  ```
