## Kafka API Bootstrap

1 - If some sort of authentication is needed, and isn't solved by a external repo (e.g.: Maven lib/dependency), add the lib to the applications's path (e.g: libs/my-auth-lib.jar).

1.1 - Also, uncomment one of the suggestion in  **build.gradle** so local libs/jars can be inherited into the app (e.g.: fileTree(include: ['*.jar'], dir: 'libs')

2 - $ docker build . -t $APP:$VERSION

3 - Create docker services and set environment variables in **docker-compose.yml**

4 - $ docker-compose up -d

Hope you enjoy!
