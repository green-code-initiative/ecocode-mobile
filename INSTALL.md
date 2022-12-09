
### Prerequisites

- Docker
- Docker-compose 3.9

## Project structure

Here is a preview of project tree :

```
Ecocode                 # Root directory of "native" linter
|
+--android-plugin       # Android
|
+--codenarc-converter   # codenarc-converter
|
\--docker-compose.yml   # Docker compose file installing available analyzer // TODO
```

You will find more information about the plugins’ architecture in their folders


## Howto build the SonarQube ecoCode plugins

### Prerequisites

- Java >= 11
- Mvn 3


### Preliminary steps (only Android)

The Android plugin uses [CodeNarc](https://codenarc.org/) to scan the gradle files of Android projects. To have more information about CodeNarc: [CodeNarc](/codenarc-converter/CodeNarc/README.md).

CodeNarc must be built separately. Please see the following steps:

Build CodeNarc (Gradle 6.9.2, Java 11), then add this custom-built CodeNarc to Maven dependencies: 

```sh
./tool_prepare-codenarc
```


### Build the code

You can build the project code by running the following command in the `src` directory.
Maven will download the required dependencies.

```sh
./tool_build.sh

# execute `mvn clean package -DskipTests`
```

Each plugin is generated in its own `<plugin>/target` directory, but they are also copied to the `lib` directory.



## Howto install SonarQube dev environment

### Prerequisites

You must have built the plugins (see the steps above).


### Start SonarQube

Run the SonarQube + PostgreSQL stack:

```sh 
./tool_docker-reinit.sh

# execute `docker-compose up --build -d`
```

Check if the containers are up:

```sh 
docker ps
```

You should see two lines (one for sonarqube and one for postgres).
If there is only postgres, check the logs:

```sh 
./tool_docker-logs.sh
```

If you have this error on run: 
`web_1  | [1]: max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]`
you can allocate more virtual memory:

```sh
sudo sysctl -w vm.max_map_count=262144
```

For Windows:

```cmd
wsl -d docker-desktop
sysctl -w vm.max_map_count=262144
```


Go to http://localhost:9000 and use these credentials:

```
login: admin
password: admin
```

When you are connected, generate a new token:

`My Account -> Security -> Generate Tokens`

![img.png](docs/resources/img.png)
![img_1.png](docs/resources/img_1.png)



Start again your services using the token:

```sh
TOKEN=MY_TOKEN docker-compose up --build -d
```

## Howto install Plugin Ecocode

Install dependencies from the root directory:

```sh
./tools_build.sh
# execute `mvn clean package -DskipTests`
```

.jar files (one per plugin) will be moved in `lib` repository after build.

## Howto start or stop service (already installed)

Once you did the installation a first time (and then you did custom configuration like quality gates, quality profiles, ...),
if you only want to start (or stop properly) existing services :

```sh
./tools_start.sh
# execute `docker-compose start`

./tools_stop.sh
# execute `docker-compose stop`
```

## Links

- Java how-to : https://github.com/SonarSource/sonar-java/blob/master/docs/CUSTOM_RULES_101.md
- Python how-to : https://github.com/SonarSource/sonar-custom-rules-examples/tree/master/python-custom-rules
- PHP how-to : https://github.com/SonarSource/sonar-custom-rules-examples/tree/master/php-custom-rules