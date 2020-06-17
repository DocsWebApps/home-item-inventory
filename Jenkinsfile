#!/usr/bin/env groovy

node {
    stage('checkout code') {
        checkout scm
    }

    stage('set java version') {
        env.JAVA_HOME="${tool 'Java11'}"
        env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
        sh "java -version"
    }

    stage('set H2 DB') {
        sh "cp /root/HomeItemInventory/application.properties ./src/main/resources"
    }

    stage('maven clean') {
        sh "chmod +x mvnw"
        sh "./mvnw -s /opt/maven/mvn3/conf/settings.xml clean"
    }

    stage('maven test') {
        try {
            sh "./mvnw -s /opt/maven/mvn3/conf/settings.xml test"
        } catch(err) {
            throw err
        } finally {
            junit '**/target/surefire-reports/TEST-*.xml'
        }
    }

    stage('maven verify') {
        sh "./mvnw -s /opt/maven/mvn3/conf/settings.xml verify -DskipTests"
        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
    }

    stage('sonarqube analysis') {
        sh "cp /var/lib/jenkins/sonar_scripts/homeinv_sonar_dev.bash ."
        sh "./homeinv_sonar_dev.bash"
    }

    stage('build docker image') {
        sh "cp /root/HomeItemInventory/Dockerfile ./target"
        sh "docker build -t docswebapps/home-item-inventory ./target"
    }
//
//    stage('restart containers') {
//        sh "docker-compose -f /root/ApplicationSupportDashboard/docker-compose.yml down"
//        sh "docker-compose -f /root/ApplicationSupportDashboard/docker-compose.yml up -d"
//    }
}
