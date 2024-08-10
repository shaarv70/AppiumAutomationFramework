#!/bin/bash

#-------------------------------------------------------------------
#  This script expects the following environment variables:
#     APPIUM_HOST
#     APPIUM_PORT
#     THREAD_COUNT
#     TEST_SUITE
#     DEVICE_PORT
#-------------------------------------------------------------------

# Print the received environment variables
echo "-------------------------------------------"
echo "APPIUM_HOST   : ${APPIUM_HOST:-0.0.0.0}"
echo "APPIUM_PORT   : ${APPIUM_PORT}"
echo "THREAD_COUNT  : ${THREAD_COUNT:-1}"
echo "TEST_SUITE    : ${TEST_SUITE:-master.xml}"
echo "DEVICE_PORT   : ${DEVICE_PORT:-5555}"
echo "-------------------------------------------"

# Check if DEVICE_PORT is set
if [ -z "$DEVICE_PORT" ]; then
    echo "Error: DEVICE_PORT is not set. Please set the DEVICE_PORT environment variable."
    exit 1
fi

# Set ADB authorization keys path
ADB_KEYS_PATH="/root/my-android-keys/adbkey"

# Check if the ADB authorization keys exist
if [ ! -f "$ADB_KEYS_PATH" ]; then
    echo "ADB authorization keys not found at $ADB_KEYS_PATH"
    echo "Please add your adbkey and adbkey.pub files to the correct location."
    exit 1
fi

# Ensure ADB server uses the authorization keys
export ADB_VENDOR_KEYS="$ADB_KEYS_PATH"

# Debugging: Print the DEVICE_PORT value
echo "Connecting to emulator at host.docker.internal:${DEVICE_PORT}"

# Connect to the Android emulator (suppress adb logs)
adb connect host.docker.internal:$DEVICE_PORT > /dev/null

# Check if the emulator is connected (suppress adb logs)
adb devices > /dev/null

# Wait for the emulator connection to be established
echo "Waiting for the emulator to connect..."
MAX_ATTEMPTS=20
ATTEMPT=1
DEVICE_NAME="host.docker.internal:$DEVICE_PORT"
while [ $ATTEMPT -le $MAX_ATTEMPTS ]; do
    DEVICE_STATE=$(adb devices | grep "$DEVICE_NAME" | awk '{print $2}')
    if [ "$DEVICE_STATE" == "device" ]; then
        echo "Emulator connected successfully."
        break
    elif [ "$DEVICE_STATE" == "unauthorized" ]; then
        echo "Emulator is unauthorized. Please authorize the device and press Enter to continue..."
        read -p "Press Enter once authorized..."
    else
        echo "Attempt $ATTEMPT of $MAX_ATTEMPTS: Emulator not connected. Retrying in 10 seconds..."
        sleep 10
    fi
    ((ATTEMPT++))
done

if [ $ATTEMPT -gt $MAX_ATTEMPTS ]; then
    echo "Error: Emulator did not connect in time. Exiting."
    exit 1
fi

# Check if the Appium server is ready
echo "Checking if Appium server is ready..."
count=0
while [ "$(curl -s http://${APPIUM_HOST:-0.0.0.0}:${APPIUM_PORT}/status | jq -r .value.ready)" != "true" ]; do
  count=$((count+1))
  echo "Attempt: ${count}"
  if [ "$count" -ge 90 ]; then
      echo "**** APPIUM SERVER IS NOT READY WITHIN 90 SECONDS ****"
      exit 1
  fi
  sleep 1
done



# Run the test suite with dynamic udid
echo "Appium server is up and running. Running the test..."

java -Dappiumremote=true \
     -DAPPIUM_HOST="${APPIUM_HOST:-0.0.0.0}" \
     -DAPPIUM_PORT="${APPIUM_PORT}" \
     -Dlog4j.configurationFile=logger/log4j2.xml \
     -Dapppath=/home/appium-docker/app/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk \
     -Dudid=host.docker.internal:${DEVICE_PORT} \
     -cp "libs/*" org.testng.TestNG test-suites/${TEST_SUITE}
