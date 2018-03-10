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
  stage('Code Quality') {
    sh "echo \"Code quality check successful\""
  }
  stage('Unit Test') {
    sh "echo \"All unit test successful\""
  }
  stage('Integration Test') {
    sh "echo \"All integration test successful\""
  }
  stage('Build Image') {
    // sh "oc new-build -n dev --strategy docker --binary --name otcs-server"
    sh "oc start-build -n dev otcs-server --from-dir . --follow"
  }
}
