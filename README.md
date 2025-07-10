![](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/sntakirutimana72/ci-e2e-test-automation/refs/heads/api-testing-with-rest-assured/.github/badges/api-testing-ci.json)
[![Test Coverage](https://coveralls.io/repos/github/sntakirutimana72/ci-e2e-test-automation/badge.svg?branch=api-testing-with-rest-assured)](https://coveralls.io/github/sntakirutimana72/ci-e2e-test-automation?branch=api-testing-with-rest-assured)
[![API Test Plan](https://img.shields.io/badge/API%20Test%20Plan-navy)](https://docs.google.com/document/d/1CzGmriueqS-OF-v4HELObDs04K31jUwAr8Ztcf3njcY/edit?tab=t.0)

# API Testing with REST Assured

This project focuses on automating API testing using `REST Assured`, a Java-based framework designed for testing RESTful web services.
The project aims to validate the functionality, performance, and security of APIs by automating test cases for CRUD operations (`Create`, `Read`, `Update`, `Delete`). 
It integrates with TestNG for structured test execution and reporting.

## Objectives

- **Configure `REST Assured`** for automated API testing.
- **Implement TestNG for Test Execution**: Use **TestNG** annotations to structure test cases effectively.
- **Implement Allure for Test Execution Reporting**: Use **Allure** annotations to structure test cases effectively and capture test results.
- **Automate Tests** – Write and execute **API tests**.
- Set up a **Continuous Integration (CI) pipeline** using **GitHub Actions** to automate **REST-Assured tests**.

## Overview:

1. Define a comprehensive `Test Plan` for API testing activities

2. **Setup and configuration**
   - Maven -- `pom.xml`
   - Add `REST Assured`, `TestNG`, `Hamcrest`, and `Allure` dependencies in `pom.xml`
   - Add also logging dependencies `logback`, `slf4j`, `Hamcrest` in `pom.xml`

3. **Implement Endpoint Object Model (EOM)**
   - Create `REST-Assured` based specification classes for each identified `endpoint`.
   - Create separate `endpoint` classes for each identified `endpoint`.
   
4. **Design API Test Cases**
   - Define `data-provider` classes for each identified `endpoint`.
   - Write test case scripts for each `EOM` class.
   
5. **Implement a CI pipeline using GitHub Actions**
   - Create a `.github/workflows/api-testing-ci.yml` configuration for GitHub Actions.
   - Ensure the workflow is triggered on code push event.
   - Set up the CI pipeline to automatically install dependencies and run the tests with GitHub Actions.
   - Set up notifications (Slack) to notify a team of test status (`pass`/`fail`).
   - Ensure GitHub Actions logs include detailed information on test execution.
   - Publish Allure test results with `gh-pages`

## Technologies

- Java -`17+`
- REST Assured -`5.4.0`
- Allure - `2.27.0`
- TestNG - `7.10.2`
- Hamcrest - `3.0`
- Intellij IDE
- Git - `2.43.0`
- GitHub
- GitHub Actions (**CI**)

## How to run locally?

To get started with local setup, do the following:

1. Install **JDK 17**
2. Install **IntelliJ IDEA**
3. Install **git** version control system ([Instructions](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git))
4. Clone `GitHub` project repo
   ```shell
   git clone --branch api-testing-with-rest-assured --single-branch https://github.com/sntakirutimana72/ci-e2e-test-automation
   ```
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
