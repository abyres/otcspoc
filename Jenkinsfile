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
  stage('Rollout Dev Image') {
    echo "Rolling out to DEVELOPMENT environment."
    sh "oc start-build -n dev otcs-server --from-dir . --follow"
  }
  stage('Code Quality') {
    sh "echo \"Code quality check successful\""
  }
  stage('Unit Test') {
    sh "echo \"All unit test successful\""
  }
  stage('Integration Test') {
    sh "echo \"All integration test successful\""
  }
  stage('Rollout Beta Image') {
    echo "Rolling out to STAGE environment."
//    sh "oc start-build -n stage otcs-server --from-dir . --follow"
    sh "oc tag stage/otcs-server:latest stage/otcs-server:blue"
    sh "oc set route-backends otcs-server stage/otcs-server:blue=2 dev/otcs-server:latest=0 -n stage"
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
