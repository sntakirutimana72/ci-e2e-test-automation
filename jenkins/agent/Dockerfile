FROM jenkins/inbound-agent:latest

USER root

# Install prerequisites
RUN apt-get update && apt-get install -y \
    ca-certificates \
    curl \
    git \
    jq \
    apt-transport-https \
    gnupg \
    lsb-release \
  && rm -rf /var/lib/apt/lists/*

# Add Docker's official GPG key
RUN mkdir -p /etc/apt/keyrings && \
    curl -fsSL https://download.docker.com/linux/debian/gpg | gpg --dearmor -o /etc/apt/keyrings/docker.gpg

# Set up the Docker repo for Debian (not Ubuntu)
RUN echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/debian \
  $(lsb_release -cs) stable" | tee /etc/apt/sources.list.d/docker.list > /dev/null

# Install docker-cli
RUN apt-get update && apt-get install -y docker-ce-cli && \
    rm -rf /var/lib/apt/lists/*

USER jenkins
WORKDIR /home/jenkins