




node {
    stage('Checkout'){
        checkout scm
        def pom = readMavenPom file: 'pom.xml'
        echo "POM current version: $pom.version"
        def currentDevelopmentVersion  = pom.version;
        def releaseVersion = currentDevelopmentVersion.substring(0, currentDevelopmentVersion.indexOf('-'));
        echo "Release version: $releaseVersion"

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
        //runWithMaven("mvn release -B -P sign-artifacts -B release:prepare release:perform ")

    }

}


def runWithMaven(String command) {
    withMaven(
            maven: 'M3',
            mavenLocalRepo: '.repository') {
        sh "$command"
    }
}
