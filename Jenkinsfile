
node {
    stage('Checkout'){
        checkout scm
    }


    stage('Validate') {
        runWithMaven("mvn validate")
    }

    stage('Compile') {
        runWithMaven("mvn compile")

    }

    stage('Test') {
        configFileProvider([configFile(fileId: 'hft-client.properties',variable: 'hftProperties')])  {
            echo "$hftProperties"

            runWithMaven("mvn -DHFT-CLIENT-PROPERTIES=${hftProperties} test")
        }
    }

    stage('Package') {
        runWithMaven("mvn package")

    }

    stage('Install') {
        runWithMaven("mvn install")

    }

    stage('Realease') {
        echo "Realse not yet done!"
        //runWithMaven("mvn release -B prepare perform ")

    }

}


def runWithMaven(String command) {
    withMaven(
            maven: 'M3',
            mavenLocalRepo: '.repository') {
        sh "$command"
    }
}
