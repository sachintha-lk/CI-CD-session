# Spring Boot CI/CD Demo with DigitalOcean App Platform

This project demonstrates a CI/CD pipeline for a Spring Boot application, deploying to DigitalOcean App Platform using a Docker image.

## Prerequisites

1.  **DigitalOcean Account**: You need an account on DigitalOcean.
2.  **DigitalOcean Container Registry (DOCR)**: Create a registry in your DO account (e.g., `my-registry`).
3.  **GitHub Repository**: Push this code to a GitHub repository.

## Setup Steps

### 1. Configure GitHub Secrets

Go to your GitHub repository settings -> Secrets and variables -> Actions, and add the following secret:

*   `DIGITALOCEAN_ACCESS_TOKEN`: Your DigitalOcean API token (generate one in the DO control panel).

### 2. Update Configuration

*   **`.github/workflows/ci-cd.yml`**: Update the `REGISTRY` environment variable to match your DigitalOcean registry name.
    ```yaml
    env:
      REGISTRY: registry.digitalocean.com/<your-registry-name>
    ```

### 3. Run the Pipeline

Push a change to the `main` branch. This will trigger the GitHub Action, which will:
1.  Build the Docker image (using Maven).
2.  Push the image to your DigitalOcean Container Registry.

### 4. Deploy to App Platform

You can deploy using the `doctl` CLI or the DigitalOcean Control Panel.

**Using Control Panel:**
1.  Create a new App.
2.  Choose "DigitalOcean Container Registry" as the source.
3.  Select the image pushed by your CI/CD pipeline.

**Using App Spec (`.do/app.yaml`):**
1.  Update `.do/app.yaml` with your specific details.
2.  Run: `doctl apps create --spec .do/app.yaml`

### 5. Demo PR Checks

1.  Create a new branch: `git checkout -b feature/new-message`
2.  Change the message in `HelloController.java`.
3.  Push the branch and open a Pull Request.
4.  The `PR Checks` workflow will run:
    *   It executes `mvn test`.
    *   It posts a comment on the PR with the result (✅ or ❌).
5.  **To demo a failure**: Change the message in the controller but *don't* update the test in `HelloControllerTest.java`. The test will fail, and the bot will comment with ❌.

## Local Development

1.  Build locally: `mvn clean package`
2.  Run locally: `java -jar target/demo-0.0.1-SNAPSHOT.jar`
3.  Build Docker image: `docker build -t spring-boot-demo .`
4.  Run Docker container: `docker run -p 8080:8080 spring-boot-demo`
