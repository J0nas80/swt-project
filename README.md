# Template repository for the Software Engineering lecture

## Team

Team Leader: Sami Chaker

Members: Ivy Chrystabell, Alexis Moos, Sobadeh Sherzad, Sami Chaker, Yasmin Almadad, Jonas Haschke 



## Quickstart

```bash,ignore
# clone repository
$ git clone https://github.com/J0nas80/swt-project.git

# build and run all services
$ docker compose up -d --build 
```

## Prerequisites

Software: Docker, git

## Installation and Setup

1. Clone the repository:
```bash,ignore
$ git clone https://github.com/J0nas80/swt-project.git
```

2. Navigate to the project directory:
```bash,ignore
$ cd path/to/swt-project
```

3. Adjust configuration files:
Modify configuration files as required.
- Mosquitto Messagebroker:
  - infrastructure/mosquitto/config/mosquitto.conf
  - infrastructure/mosquitto/mosquitto.env
- PostgreSQL Database
  - infrastructure/postgre/postgres.env 
- Portweiterleitung
  - compose.yaml

Note that mosquitto.env file is used to give connection information to backend and fake_service containers.
Make sure the configuration matches the mosquitto.conf file.

## Running the Project

Explain in detail how to run the project, including:

Starting the database

Initializing data (if needed, via scripts)

Starting the server

```bash,ignore
# Example: Initialize the database
docker compose up -d --build
```

## Project structure
Provide an overview of the directory structure to help contributors navigate the project:
```bash,ignore
ProjectName/
├── backend/            # maven project with spring service providing RestApi
├── fake_service/       # simulates a third party service
├── frontend/           # Web Server providing UI view to the client
├── infrastructure/     # database and messagebrocker configuration goes here.
├── compose.yaml        # compose file to build and start all services
└── README.md           # This file

```
