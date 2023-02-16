# octo-happiness-mqtt-processor

1. set amq broker service acceptor name in [`application.properties`](src/main/resources/application.properties) file
2. build & deploy via maven or helm and tekton

### build via maven and deploy directly on openshift:
```sh
./mvnw clean package -Dquarkus.kubernetes.deploy=true
```

### build via tekton:
```sh
helm upgrade -i processor infra/helm
```

