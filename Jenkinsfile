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

    stage('set H2 DB for test') {
        sh "cp /root/HomeItemInventory/application.properties.h2 ./src/main/resources/application.properties"
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

    stage('set mariaDB DB for build') {
        sh "cp /root/HomeItemInventory/application.properties.mariadb ./src/main/resources/application.properties"
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
        sh "docker build -t home-item-inventory ./target"
    }

    stage('start/restart containers') {
        sh "docker-compose -f /root/HomeItemInventory/docker-compose.yml down"
        sh "docker-compose -f /root/HomeItemInventory/docker-compose.yml up -d"
    }
}
