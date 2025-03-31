pipeline {
  agent { label 'docker-agent' }
  environment {
    REGISTRY = "https://localhost:5001"
    REGISTRY_HOST = "localhost:5001"
    PROJECT_DIR = "Chapter08/sample1"
  }
  stages {
    stage('Checkout code and prepare environment') {
      steps {
        git url: 'https://github.com/alec-coughlin/Continuous-Delivery-with-Docker-and-Jenkins-Second-Edition.git'
        sh """
          set -e
          cd ${PROJECT_DIR}
          chmod +x gradlew
        """
      }
    }
    stage('Build') {
      steps {
        sh """
          set -e
          cd ${PROJECT_DIR}
          ./gradlew build
        """
      }
    }
    stage('Run Tests') {
      steps {
        script {
          if (env.BRANCH_NAME == 'master') {
            sh """
              set -e
              cd ${PROJECT_DIR}
              ./gradlew test checkstyleMain checkstyleTest
              ./gradlew jacocoTestReport
              ./gradlew jacocoTestCoverageVerification
            """
          } else if (env.BRANCH_NAME.contains('feature')) {
            sh """
              set -e
              cd ${PROJECT_DIR}
              ./gradlew test checkstyleMain checkstyleTest
              ./gradlew jacocoTestReport
            """
          } else if (env.BRANCH_NAME.contains('playground')) {
            sh """
              set -e
              cd ${PROJECT_DIR}
              ./gradlew test checkstyleMain checkstyleTest
              ./gradlew jacocoTestReport
            """
          }
        }
      }
    }
    stage('Build Container') {
      when {
        expression {
          return (env.BRANCH_NAME == 'master' || env.BRANCH_NAME.contains('feature'))
        }
      }
      steps {
        script {
          def imageName = ""
          def imageTag = ""
          if (env.BRANCH_NAME == 'master') {
            imageName = "calculator"
            imageTag = "1.0"
          } else if (env.BRANCH_NAME.contains('feature')) {
            imageName = "calculator-feature"
            imageTag = "0.1"
          }
          withCredentials([usernamePassword(credentialsId: 'docker-registry', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
            sh """
              set -e
              cd ${PROJECT_DIR}
              echo "\$DOCKER_PASS" | docker login \$REGISTRY -u \$DOCKER_USER --password-stdin
              docker build -t ${imageName} .
              docker tag ${imageName} ${REGISTRY_HOST}/${DOCKER_USER}/${imageName}:${imageTag}
              docker push ${REGISTRY_HOST}/${DOCKER_USER}/${imageName}:${imageTag}
            """
          }
        }
      }
    }
  }
}
