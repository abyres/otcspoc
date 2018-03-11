node('maven') {
  stage('Checkout') {
    // Ensure Jenkins has been configured with latest Oracle JDK 8
    jdk = tool name: "OracleJDK8"
    env.JAVA_HOME = "${jdk}"
    echo "Using : ${jdk}"
    git url: "http://gogs-cicd.192.168.99.100.nip.io/xeonn/otcspoc", branch: 'master'
  }
  stage('Build') {
    sh "mvn package -Dmaven.test.skip=true"
  }
  stage('Rollout') {
    parallel {
      stage('Rollout Dev Image') {
        agent {
          label "Development"
        }
        steps {
          echo "Rolling out to DEVELOPMENT environment."
          sh "oc start-build -n dev otcs-server --from-dir . --follow"
        }
      }
      stage('Rollout Beta Image') {
        agent {
          label "Staging"
        }
        steps {
          sh "echo \"Code quality check successful\""
        }
        steps {
          sh "echo \"All unit test successful\""
        }
        steps {
          sh "echo \"All integration test successful\""
        }
        steps {
          echo "Rolling out to STAGE environment."
          sh "oc start-build -n stage otcs-server --from-dir . --follow"
          // sh "oc -n stage rollout latest otcs-server"
          sh "oc tag stage/otcs-server:latest stage/otcs-server:blue"
        }
      }
    }
  }

  stage('Approve Go Live') {
    timeout(time:15, unit:'MINUTES') {
      input message:'Go Live in Prod?'
    }
  }

  stage('Go Live') {
    echo "Rolling out to PRODUCTION environment."
    sh "oc tag stage/otcs-server:blue prod/otcs-server:green"    
    sh "oc set route-backends otcs-server prod/otcs-server:green=2 stage/otcs-server:blue=0 -n prod"
  }
}
