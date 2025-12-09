#!/bin/bash
set -e

IMAGE_NAME="production-planner"
IMAGE_TAG="latest"
TAR_FILE="production-planner.tar"

echo "=== Step 1: Building Docker image ==="
docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ../

echo "=== Step 2: Saving Docker image to TAR ==="
docker save -o ${TAR_FILE} ${IMAGE_NAME}:${IMAGE_TAG}

echo "=== FINISHED ==="
echo "Docker image saved as: ${TAR_FILE}"
