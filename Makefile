DOCKER_COMPOSE_FILE := docker-compose.yml
DOCKER_COMPOSE_FILE_INFRA := docker-compose-infra.yml

DOCKER_COMPOSE_CMD := docker compose -f $(DOCKER_COMPOSE_FILE)
DOCKER_COMPOSE_INFRA_CMD := docker compose -f $(DOCKER_COMPOSE_FILE_INFRA)

DOCKER_COMPOSE_INFRA_SERVICES := mysql elasticsearch logstash kibana

.PHONY: up
up:
	$(DOCKER_COMPOSE_INFRA_CMD) up -d $(DOCKER_COMPOSE_INFRA_SERVICES) 

.PHONY: down
down:
	$(DOCKER_COMPOSE_INFRA_CMD) down --remove-orphans

.PHONY: up-api
up-api:
	COMPOSE_DOCKER_CLI_BUILD=1 DOCKER_FILE="Dockerfile" $(DOCKER_COMPOSE_CMD) up -d java-product-service
	COMPOSE_DOCKER_CLI_BUILD=1 DOCKER_FILE="Dockerfile" $(DOCKER_COMPOSE_CMD) up -d java-order-service

down-api: down-api
	$(DOCKER_COMPOSE_CMD) stop java-product-service java-order-service
	$(DOCKER_COMPOSE_CMD) rm java-product-service java-order-service
