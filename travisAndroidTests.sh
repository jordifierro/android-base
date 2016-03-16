#!/bin/sh

androidTestResult=$(adb shell 'am instrument -w com.jordifierro.androidbase.presentation.test/com.jordifierro.androidbase.presentation.unittest.TestMockRunner ; printf "$?"')
printf "$androidTestResult\n"
exitCode=$(printf "$androidTestResult" | tail -1)
if [ $exitCode != "0" ]; then
    echo "status code error!"
    exit 1
fi
if echo "$androidTestResult" | grep -q "FAILURE"; then
    echo "failures error!"
    exit 1
fi