#!/bin/bash 
OCS_PROJECT=ocs
TOKEN=$(oc get secret heketi-ocs-admin-secret -n $OCS_PROJECT -o yaml | grep 'key:' | awk '{print $2}' | base64 -d)
oc rsh -n $OCS_PROJECT dc/heketi-ocs heketi-cli --user admin --secret $TOKEN topology info
