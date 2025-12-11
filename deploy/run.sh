#!/bin/bash
set -e

TAR_FILE="production-planner.tar" ENV_FILE=".env" IMAGE_NAME="production-planner" IMAGE_TAG="latest"

if [ ! -f "$TAR_FILE" ]; then
  echo "❌ TAR file not found: $TAR_FILE"
  exit 1
fi

if [ ! -f "$ENV_FILE" ]; then
  echo "❌ .env file not found: $ENV_FILE"
  exit 1
fi

echo "=== Step 1: Loading Docker image ==="
docker load -i "$TAR_FILE"

echo "=== Step 2: Removing old container if exists ==="
docker rm -f ${IMAGE_NAME}-container 2>/dev/null || true

echo "=== Step 3: Running container ==="
docker run -d \
  --env-file "$ENV_FILE" \
  --name ${IMAGE_NAME}-container \
  -p 8080:8080 \
  ${IMAGE_NAME}:${IMAGE_TAG}

echo "=== DONE ==="
echo "Container '${IMAGE_NAME}-container' is running!"
