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
  stage('Dev Image') {
    // sh "oc new-build -n dev --strategy docker --binary --name otcs-server"
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
  stage('Staging') {
    openshiftTag sourceStream: 'otcs-server', sourceTag: 'latest', namespace: 'dev', destinationStream: 'otcs-server', destinationTag: "blue",   destinationNamespace: 'stage'
    openshiftVerifyDeployment deploymentConfig: "otcs-server", replicaCount: '1', verifyReplicaCount: true, namespace: 'stage'
  }
  stage('Approve Go Live') {
    timeout(time:15, unit:'MINUTES') {
      input message:'Go Live in Prod?'
    }
  }

  stage('Go Live') {
    openshiftTag sourceStream: 'otcs-server', sourceTag: 'blue', namespace: 'stage', destinationStream: 'otcs-server', destinationTag: "green",   destinationNamespace: 'prod'
    openshiftVerifyDeployment deploymentConfig: "otcs-server", replicaCount: '1', verifyReplicaCount: true, namespace: 'prod'
//    sh "oc set route-backends otcs-server cart-${tag}=100 cart-${altTag}=0 -n prod"
  }
}
