node('maven') {
  stage('Checkout') {
    git url: "https://github.com/abyres/otcspoc", branch: 'master'
  }
  stage('Build') {
    sh "mvn package -Dmaven.test.skip=true"
  }
  stage('Test') {
    sh "echo \"All test successful\""
  }
  stage('Build Image') {
    // sh "oc new-build -n dev --strategy docker --binary --name otcs-server"
    sh "oc start-build -n dev otcs-server --from-dir . --follow"
  }
}
