def call(String ProjectName, String ImageTag, String DockerHubId){
    sh "docker build -t ${DockerHubId}/${ProjectName}:${ImageTag} ."
}