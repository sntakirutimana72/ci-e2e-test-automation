pipeline {
  agent any

  environment {
    ALLURE_VERSION = '2.27.0'
    GH_REPO = 'ci-e2e-test-automation'
    GH_CURRENT_BRANCH = 'api-testing-with-jenkins-ci'
  }

  triggers {
    githubPush()   // listens to GitHub webhook
  }

  tools {
    maven 'Maven 3.8.6'
    jdk 'JDK 17'
  }

  options {
    timeout(time: 30, unit: 'MINUTES')
    skipDefaultCheckout(true)
  }

  stages {
    stage('Checkout') {
      steps {
        withCredentials([string(credentialsId: 'gh-user', variable: 'GH_USER')]) {
          retry(3) {
            git url: "https://github.com/${GH_USER}/${GH_REPO}.git", branch: "${GH_CURRENT_BRANCH}"
          }
        }
      }
    }

    stage('Cache Maven Dependencies') {
      steps {
        script {
          // Create cache directory if it doesn't exist
          sh 'mkdir -p ${JENKINS_HOME}/maven-cache'

          // Check if cache exists and restore it
          if (fileExists("${env.JENKINS_HOME}/maven-cache/.m2-cache.tar.gz")) {
            echo "Restoring Maven dependencies from cache..."
            sh '''
              cd $HOME
              tar -xzf ${JENKINS_HOME}/maven-cache/.m2-cache.tar.gz || echo "Cache extraction failed, will download dependencies"
            '''
          } else {
            echo "No Maven cache found, will download dependencies"
          }
        }
      }
    }

    stage('Build & Run Tests') {
      steps {
        script {
          try {
            sh 'mvn clean test'
          } catch (Exception e) {
            currentBuild.result = 'UNSTABLE'
            echo "Tests failed but continuing pipeline: ${e.getMessage()}"
          }
        }
      }

      post {
        always {
          script {
            // Cache Maven dependencies after build
            echo "Caching Maven dependencies..."
            sh '''
              cd $HOME
              if [ -d ".m2/repository" ]; then
                tar -czf ${JENKINS_HOME}/maven-cache/.m2-cache.tar.gz .m2/repository/
                echo "Maven dependencies cached successfully"
              else
                echo "No Maven repository found to cache"
              fi
            '''
          }
        }
      }
    }

    stage('Install Dependencies') {
      steps {
        script {
          sh '''
            # Function to wait for apt lock
            wait_for_apt() {
              echo "Waiting for apt lock to be available..."
              while fuser /var/lib/dpkg/lock-frontend >/dev/null 2>&1 || fuser /var/lib/apt/lists/lock >/dev/null 2>&1; do
                echo "Another package manager is running, waiting..."
                sleep 10
              done
              echo "apt lock is now available"
            }

            # Wait for any existing apt processes to finish
            wait_for_apt

            # Update package list once with retry
            for i in 1 2 3; do
              if apt-get update; then
                break
              else
                echo "apt-get update failed, attempt $i/3"
                wait_for_apt
                sleep 5
              fi
            done

            # Install all packages in one operation with retry
            for i in 1 2 3; do
              if apt-get install -y libxml2-utils default-jre curl unzip; then
                break
              else
                echo "Package installation failed, attempt $i/3"
                wait_for_apt
                sleep 5
              fi
            done
          '''

          // Install Allure CLI separately (doesn't need apt)
          sh '''
            if [ ! -f "/usr/bin/allure" ]; then
              echo "Installing Allure CLI..."
              curl -o allure.zip -sL "https://github.com/allure-framework/allure2/releases/download/${ALLURE_VERSION}/allure-${ALLURE_VERSION}.zip"

              # Check if unzip is available, if not use alternative extraction
              if command -v unzip >/dev/null 2>&1; then
                unzip -q allure.zip
              else
                echo "unzip not available, trying alternative extraction..."
                # Try using jar (comes with JDK) or python
                if command -v jar >/dev/null 2>&1; then
                  jar -xf allure.zip
                elif command -v python3 >/dev/null 2>&1; then
                  python3 -m zipfile -e allure.zip .
                elif command -v python >/dev/null 2>&1; then
                  python -m zipfile -e allure.zip .
                else
                  echo "No extraction tool available, installing unzip..."
                  apt-get install -y unzip
                  unzip -q allure.zip
                fi
              fi

              mv "allure-${ALLURE_VERSION}" /opt/allure
              ln -s /opt/allure/bin/allure /usr/bin/allure
              rm -f allure.zip
              echo "Allure CLI installed successfully"
            else
              echo "Allure CLI already installed"
            fi
          '''
        }
      }
    }

    stage('Generate Test Badge') {
      steps {
        sh '''
          mkdir -p ".jenkins/badges"

          TOTAL=0
          FAILS=0

          # Check if test reports exist
          if [ ! -d "target/surefire-reports" ] || [ -z "$(ls -A target/surefire-reports/*.xml 2>/dev/null)" ]; then
            echo "No test reports found"
            echo "{\\"schemaVersion\\":1,\\"label\\":\\"fakestoreapi.com Tests\\",\\"message\\":\\"No Tests\\",\\"color\\":\\"lightgrey\\"}" > ".jenkins/badges/jenkins-ci.json"
            exit 0
          fi

          for file in target/surefire-reports/*.xml; do
            if [ -f "$file" ]; then
              t=$(xmllint --xpath "count(//testcase)" "$file" 2>/dev/null || echo "0")
              t=${t%.*}
              TOTAL=$((TOTAL + t))

              f=$(xmllint --xpath "count(//testcase[failure or error])" "$file" 2>/dev/null || echo "0")
              f=${f%.*}
              FAILS=$((FAILS + f))
            fi
          done

          PASSED=$((TOTAL - FAILS))

          if [ "$TOTAL" -gt 0 ]; then
            PERCENT=$((100 * PASSED / TOTAL))
          else
            PERCENT=0
          fi

          # Determine badge color
          if [ "$PERCENT" -ge 85 ]; then
            COLOR="brightgreen"
          elif [ "$PERCENT" -ge 65 ]; then
            COLOR="yellow"
          else
            COLOR="red"
          fi

          echo "Test Results: $PASSED/$TOTAL ($PERCENT%)"
          echo "{\\"schemaVersion\\":1,\\"label\\":\\"fakestoreapi.com Tests\\",\\"message\\":\\"$PASSED/$TOTAL ($PERCENT%)\\",\\"color\\":\\"$COLOR\\"}" > ".jenkins/badges/jenkins-ci.json"
        '''
      }
    }

    stage('Publish Test Badge') {
      when {
        not { changeRequest() }
      }
      steps {
        withCredentials([usernamePassword(credentialsId: 'github-token', usernameVariable: 'GH_USER', passwordVariable: 'GH_TOKEN')]) {
          script {
            try {
              sh '''
                git remote set-url origin "https://${GH_USER}:${GH_TOKEN}@github.com/${GH_USER}/${GH_REPO}.git"
                git add .jenkins/badges/*.json

                if git diff --cached --quiet; then
                  echo "No badge changes to commit."
                else
                  git commit -m "Update test badges"
                  git push origin ${GH_CURRENT_BRANCH}
                fi
              '''
            } catch (Exception e) {
              echo "Failed to publish badge: ${e.getMessage()}"
              currentBuild.result = 'UNSTABLE'
            }
          }
        }
      }
    }

    stage('Fetch Allure Report History') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'github-token', usernameVariable: 'GH_USER', passwordVariable: 'GH_TOKEN')]) {
          script {
            try {
              // Clone gh-pages branch if it exists
              sh '''
                rm -rf gh-pages allure-history
                git clone --depth 1 --branch gh-pages "https://${GH_USER}:${GH_TOKEN}@github.com/${GH_USER}/${GH_REPO}.git" gh-pages 2>/dev/null || {
                  echo "No existing gh-pages branch found, creating new report"
                }

                mkdir -p allure-history

                # Copy existing report history into allure-history if gh-pages exists
                if [ -d gh-pages ]; then
                  cp -r gh-pages/* allure-history/ 2>/dev/null || echo "No previous reports to copy"
                fi
              '''
            } catch (Exception e) {
              echo "Failed to fetch Allure history: ${e.getMessage()}"
              // Ensure folder exists even if fetch fails
              sh 'mkdir -p allure-history'
            }
          }
        }
      }
    }

    stage('Generate Allure Report') {
      steps {
        script {
          try {
            sh '''
              if [ -d "target/allure-results" ] && [ "$(ls -A target/allure-results)" ]; then
                allure generate target/allure-results --clean -o allure-history
              else
                echo "No Allure results found, skipping report generation"
              fi
            '''
          } catch (Exception e) {
            echo "Failed to generate Allure report: ${e.getMessage()}"
            currentBuild.result = 'UNSTABLE'
          }
        }
      }
    }

    stage('Deploy Allure Report to gh-pages') {
      when {
        allOf {
          not { changeRequest() }
          expression { fileExists('allure-history/index.html') }
        }
      }
      steps {
        withCredentials([usernamePassword(credentialsId: 'github-token', usernameVariable: 'GH_USER', passwordVariable: 'GH_TOKEN')]) {
          script {
            try {
              sh '''
                cd allure-history
                git init
                git remote add origin "https://${GH_USER}:${GH_TOKEN}@github.com/${GH_USER}/${GH_REPO}.git"
                git checkout -b gh-pages
                git add .

                if git diff --cached --quiet; then
                  echo "No changes to deploy"
                else
                  git commit -m "Update Allure report - Build #${BUILD_NUMBER}"
                  git push -f origin gh-pages
                fi
              '''
            } catch (Exception e) {
              echo "Failed to deploy Allure report: ${e.getMessage()}"
              currentBuild.result = 'UNSTABLE'
            }
          }
        }
      }
    }
  }

  post {
    always {
      script {
        // Clean up temporary files
        sh '''
          rm -rf gh-pages allure-history allure.zip allure-${ALLURE_VERSION} 2>/dev/null || true
        '''
      }

      // Archive test results using junit step
      junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true

      // Archive Allure results if they exist
      script {
        if (fileExists('allure-results')) {
          archiveArtifacts artifacts: 'allure-results/**', allowEmptyArchive: true
        }
        if (fileExists('.jenkins/badges/jenkins-ci.json')) {
          archiveArtifacts artifacts: '.jenkins/badges/*.json', allowEmptyArchive: true
        }
      }
    }

    success {
      script {
        def testResults = ""
        if (fileExists('.jenkins/badges/jenkins-ci.json')) {
          try {
            def badge = readJSON file: '.jenkins/badges/jenkins-ci.json'
            testResults = " | Tests: ${badge.message ?: 'N/A'}"
          } catch (e) {
            echo "Failed to parse badge JSON: ${e.message}"
          }
        }

        slackSend(
          color: 'good',
          message: "✅ Build #${env.BUILD_NUMBER} passed: ${env.JOB_NAME}${testResults} (<${env.BUILD_URL}|Open>)"
        )
      }
    }

    failure {
      script {
        slackSend(
          color: 'danger',
          message: "❌ Build #${env.BUILD_NUMBER} failed: ${env.JOB_NAME} (<${env.BUILD_URL}|Open>) - Check logs for details"
        )
      }
    }

    unstable {
      script {
        slackSend(
          color: 'warning',
          message: "⚠️ Build #${env.BUILD_NUMBER} unstable: ${env.JOB_NAME} (<${env.BUILD_URL}|Open>) - Some steps failed but pipeline continued"
        )
      }
    }

    cleanup {
      script {
        // Clean workspace but preserve cache
        sh '''
          # Clean git workspace
          git clean -fdx || true

          # Clean temporary files but keep maven cache
          rm -rf gh-pages allure-history allure.zip allure-${ALLURE_VERSION} 2>/dev/null || true
        '''
      }
    }
  }
}