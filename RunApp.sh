#!/bin/bash

# Clear ports
lsof -i :8080
kill -9 $(lsof -t -i:8080)
lsof -i :4200
kill -9 $(lsof -t -i:4200)
wait

# Navigate to the backend directory
cd backend/TomasGriffa

# Execute the script to set up the database and maven project
./backend_setup.sh&

# Navigate back to the root directory
cd ../../

# Navigate to the frontend directory
cd frontend/TomasGriffa

# Execute the script to set up the Angular application and run it
./frontend_setup.sh&

# Navigate back to the root directory
cd ../../

