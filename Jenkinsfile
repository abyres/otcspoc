node('maven') {
  stage('Build App') {
    jdk = tool name: "OracleJDK8"
    env.JAVA_HOME = "${jdk}"
    git url: "https://github.com/abyres/otcspoc", branch: 'master'

    echo "jdk installation path is: ${jdk}"
    sh "mvn package"
  }
  stage('Build Image') {
    sh "tree"
    sh "oc start-build otcs --from-file=server/target/otcs-server.jar --follow"
  }
  stage('Deploy') {
    openshiftDeploy depCfg: 'otcs'
    openshiftVerifyDeployment depCfg: 'otcs', replicaCount: 1, verifyReplicaCount: true
  }

  def tag="blue"
  def altTag="green"

  stage('Promote') {
    sh "oc get route otcs -n prod -o jsonpath='{ .spec.to.name }' > activeservice"
    activeService = readFile('activeservice').trim()
    if (activeService == "otcs-blue") {
      tag = "green"
      altTag = "blue"
    }
    openshiftTag sourceStream: 'otcs', sourceTag: 'latest', namespace: 'myproject', destinationStream: 'otcs', destinationTag: "prod-${tag}", destinationNamespace: 'prod'
    openshiftVerifyDeployment deploymentConfig: "otcs-${tag}", replicaCount: '1', verifyReplicaCount: true, namespace: 'prod'
  }

  stage('End-To-End Test') {

  }

  stage('Approve Go Live') {
    timeout(time:15, unit:'MINUTES') {
      input message:'Go Live in Prod?'
    }
  }

  stage('Go Live') {
    sh "oc set route-backends otcs otcs-${tag}=100 otcs-${altTag}=0 -n prod"
  }
}
