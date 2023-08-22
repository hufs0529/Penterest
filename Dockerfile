# Use the official node image as the base image
FROM node:18.14.0-alpine3.17

# Set the working directory
WORKDIR /app

# Copy the package.json and package-lock.json files
COPY package*.json ./

# Install the dependencies
RUN npm install

# Copy the rest of the project files
COPY . .

# Build the React application
RUN npm run build

# Expose the default React port (3000)
EXPOSE 3000

# Start the React application
CMD ["npm", "start"]
