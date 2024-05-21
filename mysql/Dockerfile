# Use the official MySQL Docker image as the base image
FROM mysql:latest

# Set environment variables for MySQL
ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_DATABASE=management
ENV MYSQL_USER=sa
ENV MYSQL_PASSWORD=password

# Expose MySQL default port
EXPOSE 3306

# Define a health check for MySQL
HEALTHCHECK --interval=10s --timeout=5s --retries=3 \
  CMD mysqladmin ping -h localhost -u root -ppassword || exit 1

# Volume for MySQL data
VOLUME /var/lib/mysql
