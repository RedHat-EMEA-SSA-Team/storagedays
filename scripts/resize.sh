#!/bin/bash
APP_PROJECT=$1
PVC_NAME=$2
NEW_SIZE=$3
oc patch pvc $PVC_NAME -n $APP_PROJECT --patch '{"spec":{"resources":{"requests":{"storage": "'$NEW_SIZE'"}}}}'
