#!/bin/bash
while true
do
  PROJECT_NAME=$(cat /dev/urandom | tr -dc 'a-z0-9' | fold -w 5 | head -n 1)
  oc new-project $PROJECT_NAME
  oc label namespace $PROJECT_NAME delete=me
  oc new-app mongodb-persistent -p VOLUME_CAPACITY=2Gi

  while true
  do
    echo "Waiting PV to get provisioned"
    RESULT=$(oc get pvc --no-headers)
    if (oc get pvc --no-headers | grep -q 'Bound'); then
      echo "PV provisioned"
      oc get pvc --no-headers
      break;
    fi
    sleep 1
  done

  while true
  do
    echo "Waiting MongoDB to get started"
    if (oc get po --no-headers -l name=mongodb | grep '1/1'); then
      echo "MongoDB started"
      oc get po -o wide --no-headers
      break;
    fi
    sleep 1
  done
  echo 'Project '$PROJECT_NAME' provisioned.'
  oc delete project $PROJECT_NAME
  echo 'Project '$PROJECT_NAME' deleted.'
  sleep 3
done
