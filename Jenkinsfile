pipeline {
  agent { label 'docker-agent' }
  stages {
    stage('Main Tests') {
      when { branch 'master' }
      steps {
        sh 'cd Chapter08/sample1 && ./gradlew test checkstyleMain checkstyleTest && ./gradlew jacocoTestCoverageVerification && ./gradlew jacocoTestReport'
      }
    }
    stage('Non Main Branch') {
      when {
        not {
          branch 'master'
        }
      }
      steps {
        sh 'cd Chapter08/sample1 && ./gradlew test checkstyleMain checkstyleTest && ./gradlew jacocoTestReport'
      }
    }
  }
  post {
    success {
      echo 'tests pass!'
    }
    failure {
      echo 'tests fail!'
    }
    always {
      publishHTML([
        reportDir: 'Chapter08/sample1/build/reports/jacoco/test/html',
        reportFiles: 'index.html',
        reportName: 'JaCoCo Report',
        keepAll: true,
        alwaysLinkToLastBuild: true,
        allowMissing: false
      ])
    }
  }
}
