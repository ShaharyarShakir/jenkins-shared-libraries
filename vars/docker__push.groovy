def call(Map config = [:]) {
    def imageName   = config.imageName ?: error("❌ Image name is required")
    def imageTag    = config.imageTag ?: 'latest'
    def credentials = config.credentials ?: 'dockerHubCred'

    echo "📦 Pushing Docker image: ${imageName}:${imageTag}"

    withCredentials([usernamePassword(
        credentialsId: credentials,
        usernameVariable: 'DOCKER_HUB_USER',
        passwordVariable: 'DOCKER_HUB_PASS'
    )]) {
        sh """
            echo "\$DOCKER_HUB_PASS" | docker login -u "\$DOCKER_HUB_USER" --password-stdin
            docker push ${imageName}:${imageTag}
        """
    }
}
