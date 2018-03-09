node('maven') {
  stage('Build App') {
    # Ensure Jenkins has been configured with latest Oracle JDK 8
    jdk = tool name: "OracleJDK8"
    env.JAVA_HOME = "${jdk}"
    git url: "https://github.com/abyres/otcspoc", branch: 'master'

    echo "jdk installation path is: ${jdk}"
    sh "mvn package"
    stash name:"jar", includes:"server/target/otcs-server.jar"
  }
  stage('Build Image') {
    unstash name:"jar"
    sh "oc start-build otcs-server --from-file=server/target/otcs-server.jar --follow"
  }
  stage('Deploy') {
    openshiftDeploy depCfg: 'otcs-server'
    openshiftVerifyDeployment depCfg: 'otcs-server', replicaCount: 1, verifyReplicaCount: true
  }
}
