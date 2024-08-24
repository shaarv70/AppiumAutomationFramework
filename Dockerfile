# Use Liberica OpenJDK 17 on Alpine as the base image
FROM bellsoft/liberica-openjdk-alpine:17.0.8

# Install Node.js, npm, dos2unix, curl, jq, bash, and unzip
RUN apk add --no-cache \
    nodejs \
    npm \
    dos2unix \
    curl \
    jq \
    bash \
    unzip

# Install Appium v2.5.4
RUN npm install -g appium@2.5.4

# Install Appium Drivers
RUN appium driver install uiautomator2

# Install Android SDK Command Line Tools
RUN mkdir -p /opt/android-sdk/cmdline-tools && \
    curl -o /opt/android-sdk/cmdline-tools/cmdline-tools.zip https://dl.google.com/android/repository/commandlinetools-linux-8512546_latest.zip && \
    unzip /opt/android-sdk/cmdline-tools/cmdline-tools.zip -d /opt/android-sdk/cmdline-tools && \
    rm /opt/android-sdk/cmdline-tools/cmdline-tools.zip && \
    mv /opt/android-sdk/cmdline-tools/cmdline-tools /opt/android-sdk/cmdline-tools/latest

# Set environment variables for Android SDK
ENV ANDROID_HOME=/opt/android-sdk
ENV PATH="${PATH}:${ANDROID_HOME}/cmdline-tools/latest/bin:${ANDROID_HOME}/platform-tools:${ANDROID_HOME}/build-tools/30.0.3"

# Accept licenses
RUN yes | /opt/android-sdk/cmdline-tools/latest/bin/sdkmanager --sdk_root=${ANDROID_HOME} --licenses || \
    (echo "Retrying license acceptance..." && sleep 20 && yes | /opt/android-sdk/cmdline-tools/latest/bin/sdkmanager --sdk_root=${ANDROID_HOME} --licenses)

# Install platform-tools and build-tools
RUN /opt/android-sdk/cmdline-tools/latest/bin/sdkmanager --sdk_root=${ANDROID_HOME} --install "platform-tools" "build-tools;30.0.3" || \
    (echo "Retrying platform-tools and build-tools installation..." && sleep 30 && /opt/android-sdk/cmdline-tools/latest/bin/sdkmanager --sdk_root=${ANDROID_HOME} --install "platform-tools" "build-tools;30.0.3")

# Verify installation
RUN sleep 30 && \
    ls ${ANDROID_HOME}/platform-tools && \
    ls ${ANDROID_HOME}/cmdline-tools/latest/bin && \
    ls ${ANDROID_HOME}/build-tools/30.0.3

# Copy the ADB keys to the container
ADD Android-Keys /root/my-android-keys

# Set the ADB_VENDOR_KEYS environment variable
ENV ADB_VENDOR_KEYS=/root/my-android-keys/adbkey

# Set the working directory
WORKDIR /home/appium-docker

# Create RemoteServerLog directory
RUN mkdir -p /home/appium-docker/RemoteServerLog

# Copy application resources and runner script
ADD target/appium-resources ./
ADD runner.sh runner.sh
ADD start_appium.sh start_appium.sh

# Expose the Appium server port
EXPOSE 4723
EXPOSE 4724

# Convert runner.sh to Unix format and make it executable
RUN dos2unix start_appium.sh runner.sh
RUN chmod +x start_appium.sh

# Start Appium server and then run the runner.sh script
ENTRYPOINT sh start_appium.sh
 



