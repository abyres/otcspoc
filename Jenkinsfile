node('maven') {
  stage('Build App') {
    git url: "https://github.com/abyres/otcspoc", branch: 'master'
    sh "oc new-build -n dev --strategy docker --binary --name otcs-server"
  }
  stage('Deploy') {
    sh "oc start-build -n dev otcs-server --from-dir . --follow
  }
}
