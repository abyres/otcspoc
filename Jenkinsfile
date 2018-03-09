node('maven') {
  stage('Build App') {
    git url: "https://github.com/abyres/otcspoc", branch: 'master'
    sh "oc new-app -e APP_OPTIONS="Dspring.profiles.active=server" -e APP_SUFFIX=otcs-server xeonn/s2i-java~https://github.com/abyres/otcspoc "
  }
  stage('Deploy') {
    openshiftDeploy depCfg: 'otcs-server'
    openshiftVerifyDeployment depCfg: 'otcs-server', replicaCount: 1, verifyReplicaCount: true
  }
}
