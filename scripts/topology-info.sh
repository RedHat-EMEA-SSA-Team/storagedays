#!/bin/bash 

TOKEN=$(oc get secret heketi-ocs-admin-secret -n $OCS_PROJECT -o yaml | grep 'key:' | awk '{print $2}' | base64 -d)
OCS_PROJECT=ocs
oc rsh -n $OCS_PROJECT dc/heketi-ocs heketi-cli --user admin --secret $TOKEN topology info
