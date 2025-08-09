![](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/sntakirutimana72/ci-e2e-test-automation/refs/heads/selenide-test-with-selenoid-hub/.github/badges/chrome-128.0.json)
![](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/sntakirutimana72/ci-e2e-test-automation/refs/heads/selenide-test-with-selenoid-hub/.github/badges/firefox-125.0.json)

# 🧪 Selenide Test Suite with Selenoid/Selenium Grid

This project is a containerized functional UI test suite built using **Selenide** and designed for scalable execution on **Selenoid** or **Selenium Grid**. It features a Dockerized setup for clean, consistent test runs and includes CI integration via **GitHub Actions**.

---

## 📘 Project Specification

### 📌 Project: Dockerizing Test Automation for Scalable Test Execution

#### 🎯 Overview

This project demonstrates how to containerize and automate a UI test suite using Docker. It isolates the testing environment to eliminate dependency conflicts and ensures consistent execution across platforms.

#### ✅ Objectives

- Understand the purpose and benefits of Docker in QA workflows.
- Learn how to write a Dockerfile to containerize automation projects.
- Gain hands-on experience with building, running, and debugging test containers.
- Integrate containerized tests into CI pipelines.

#### 🧪 Tasks

1. **Working test suite**:
   - UI tests for [Swag Labs](https://www.saucedemo.com/) (Login, Cart, Checkout) using Selenium WebDriver + Selenide.
2. **Dockerize the test environment**:
   - Dockerfile to build the image with all required libraries.
   - Entrypoint runs tests automatically when the container starts.
3. **Build and run the container**:
   - Validate using `docker build` and `docker run`.
4. **Automate with CI**:
   - GitHub Actions pipeline to trigger containerized tests on push.

---

## 📦 Tech Stack

- **Java 17**
- **Selenide** + **Selenium WebDriver**
- **Docker** & **Docker Compose**
- **TestNG**
- **Allure**
- **Maven**
- **Selenium Grid** / **Selenoid**

---

## 🚀 Getting Started

### 1. Clone the Repo

```bash
git clone <your-repo-url>
cd test-with-selenide-with-selenoid-or-selenium-grid
```

### 2. Environment Configuration

The project supports environment files using a custom environment loader. 
However, for better assured execution, continue with the already provided `.env.test` file for test runs.

---

## 🧰 Run tests with `Selenoid`

```bash
  docker compose up -d --build
```

---

## 📂 Project Structure

```
src/
├── main/java
│   ├── com.page            # Page Objects
│   ├── com.dto             # DTOs for test data
│   └── com.util            # Utilities and environment loaders
├── test/java               # Test cases
└── resources               # Logging config, YAML/JSON files
```
---

## 🔄 GitHub Actions CI

[See CI Workflow here](.github/workflows/selenide-tests-with-selenoid.yml)
