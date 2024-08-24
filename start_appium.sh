#!/bin/bash

BL='\033[0;34m'
G='\033[0;32m'
RED='\033[0;31m'
YE='\033[1;33m'
NC='\033[0m' # No Color

# Create log directory if it does not exist
LOG_DIR="RemoteServerLog"
mkdir -p "$LOG_DIR"

function start_appium () {
    if [ -z "$APPIUM_PORT" ] || [ "$APPIUM_PORT" == "null" ]; then
        printf "${G}==>  ${YE}No port provided, instance will run on 4723 ${G}<==${NC}\n"
        sleep 0.5
        # Start Appium and redirect all output to a log file
        appium > "$LOG_DIR/appium_$(date +"%Y%m%d_%H%M%S").log" 2>&1 &
        APPIUM_PID=$!
    else
        printf "${G}==>  ${BL}Instance will run on ${YE}${APPIUM_PORT} ${G}<==${NC}\n"
        sleep 0.5
        # Start Appium with nohup and redirect all output to a log file
        appium -p $APPIUM_PORT > "$LOG_DIR/appium_$(date +"%Y%m%d_%H%M%S").log" 2>&1 &
        APPIUM_PID=$!
        
    fi
}

# Start the Appium server in the background
start_appium

# Execute your runner.sh script and show its logs on the console
/home/appium-docker/runner.sh

# After runner.sh completes, stop the Appium server
printf "${G}==>  ${RED}Stopping Appium server...${G}<==${NC}\n"
kill $APPIUM_PID
