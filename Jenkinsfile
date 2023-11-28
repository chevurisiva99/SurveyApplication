pipeline {
    agent any
    triggers {
        githubPush()
    }
    environment {
        TIMESTAMP = "${new Date().format('yyyyMMddHHmmss', TimeZone.getTimeZone('UTC'))}"
        KUBECONFIG = '/var/lib/jenkins/.kube/config.yaml'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t sivachevuri22/springapp:${TIMESTAMP} ."
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh "docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}"
                    }
                    sh "docker push sivachevuri22/springapp:${TIMESTAMP}"
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    sh "kubectl --context=hw2-rancher-cluster -n default set image deployment/workload1 container-0=sivachevuri22/springapp:${TIMESTAMP}"
                }
            }
        }
    }
}