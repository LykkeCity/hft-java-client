String id ="hft-client.properties";

node {
    echo "ID: ${id}";
    stage('Prepare'){

        configFileProvider([configFile(fileId: 'hft-client.properties',variable: 'hftProperties')])  {
            echo "$hftProperties"
            sh "cp $hftProperties src/main/resources/hft-client.properties"
        }
    }

    stage('Validate') {
        runWithMaven("mvn validate")



    }

    stage('Compile') {
        runWithMaven("mvn compile")

    }

    stage('Test') {
        runWithMaven("mvn test")

    }

}


def runWithMaven(String command) {
    withMaven(
            maven: 'M3',
            mavenLocalRepo: '.repository') {
        sh "$command"
    }
}
