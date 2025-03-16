pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'cd Chapter08/sample1 && ./gradlew test'
      }
    }
    stage('Check java Changes') {
      when {
        changeset "**/*.java"
      }
      steps {
        sh 'cd Chapter08/sample1 && ./gradlew jacocoTestCoverageVerification && ./gradlew checkstyleMain checkstyleTest'
      }
    }
  }
  post {
    failure {
      echo 'pipeline failure'
    }
    success {
      echo 'pipeline ran perfectly'
    }
  }
}
