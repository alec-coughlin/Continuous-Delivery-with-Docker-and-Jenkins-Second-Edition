pipeline {
  agent { label 'docker-agent' }
  stages {
    stage('Main Tests') {
      when { branch 'master' }
      steps {
        sh 'cd Chapter08/sample1 && ./gradlew test && ./gradlew jacocoTestCoverageVerification && ./gradlew checkstyleMain checkstyleTest'
      }
    }
    stage('Feature Tests') {
      when {
        expression {
          return env.BRANCH_NAME.contains('feature')
        }
      }
      steps {
        sh 'cd Chapter08/sample1&& ./gradlew test && ./gradlew checkstyleMain checkstyleTest'
      }
    }
    stage('Non Main or Feature Branch Failure') {
      when {
        not { branch 'main' }
      }
      steps {
        error('pipeline failure')
      }
    }
  }
}
