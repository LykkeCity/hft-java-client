
node {
    stage('Checkout'){
        checkout scm
    }
    stage('Prepare'){

        configFileProvider([configFile(fileId: 'hft-client.properties',variable: 'hftProperties')])  {
            echo "$hftProperties"
        }
    }

    stage('Validate') {
        runWithMaven("mvn validate")
    }

    stage('Compile') {
        runWithMaven("mvn compile")

    }

    stage('Test') {
        runWithMaven("mvn -DHFT-CLIENT-PROPERTIES=${hftProperties} test")

    }

    stage('Package') {
        runWithMaven("mvn package")

    }

    stage('Install') {
        runWithMaven("mvn install")

    }

    stage('Realease') {
        echo "Realse not yet done!"
        //runWithMaven("mvn relase -B")

    }

}


def runWithMaven(String command) {
    withMaven(
            maven: 'M3',
            mavenLocalRepo: '.repository') {
        sh "$command"
    }
}
