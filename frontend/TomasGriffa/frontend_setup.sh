# Check if Node.js is installed
if command -v node >/dev/null 2>&1; then
    echo "Node.js found, setting up the Angular application..."
else
    echo "Node.js not found, please install it first."
    exit 1
fi

# Check if the current directory contains an Angular project
if [ -f package.json ]; then
    echo "package.json found, installing dependencies..."
    npm install
else
    echo "package.json not found, please navigate to an Angular project directory."

fi

echo "Angular setup complete."

# Check if Angular CLI is installed
if command -v ng > /dev/null 2>&1; then
  # Install Angular dependencies and run the Angular application
  npm install
  # Check if port 4200 is in use
  if lsof -i:4200; then
    echo "Port 4200 is in use, killing the process..."
    # Kill the process using port 4200
    kill $(lsof -t -i:4200)
  fi
  ng serve --port 4200
else
  echo "Angular CLI not found, please install it first."
  exit 1
fi
