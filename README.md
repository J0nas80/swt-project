# Template repository for the Software Engineering lecture

> [!IMPORTANT]
> Replace this README with a detailed description of your project, your team, and instructions on how to run it.

> [!IMPORTANT]
> If you choose to keep your repository private, make sure to invite all your team members and teaching staff. Ask for their usernames if needed.

Provide a general introduction to your project. Describe the purpose, goals, and the technologies used. Explain the value your project offers.

## Team
List the team members involved in the project:

Team Leader: Boulahoula, Houda

Members: Houda Boulahoula, Ivy Chrystabell, Alexis Moos, Sobadeh Sherzad, Sami Chaker, Yasmin Almadad, Jonas Haschke 



## Quickstart

This section outlines the steps required to get your project up and running quickly:

```bash,ignore
# To build local and run all services
$ docker compose -f compose.dev.yaml up --build 

# to start services build by github actions on main branch
$ docker compose
```

## Prerequisites

Detail all the necessary prerequisites for running your project, such as:

Software: Docker, Java, Maven

Ports: can be configured in docker compose files

## Installation and Setup

Provide step-by-step instructions on how to clone the repository, install the project, and configure it:

1. Clone the repository:
```bash,ignore
$ git clone https://github.com/YourRepository.git
```

2. Navigate to the project directory:
```bash,ignore
$ cd ProjectName
```

3. Adjust configuration files:

Modify configuration files (e.g., `.env`, `application.properties`) as required.


## Running the Project

Explain in detail how to run the project, including:

Starting the database

Initializing data (if needed, via scripts)

Starting the server

```bash,ignore
# Example: Initialize the database
$ ./init-db.sh

# Start the project
$ ./mvnw spring-boot:run
```

## Project structure
Provide an overview of the directory structure to help contributors navigate the project:
```bash,ignore
ProjectName/
├── backend/            # maven project with spring service providing RestApi
├── fake_service/       # simulates a third party service
├── frontend/           # Web Server providing UI view to the client
├── infrastructure/     # database and messagebrocker configuration goes here.
└── README.md           # This file
```
