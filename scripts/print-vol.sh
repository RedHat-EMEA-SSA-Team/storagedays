#!/bin/bash 

# get pv name
APP_PROJECT=$1
OCS_PROJECT=ocs
PV=$(oc get pvc -n $APP_PROJECT --no-headers | awk '{print $3}')
# get volume id
VOL=$(oc describe pv $PV | grep 'Path:' | awk '{print $2}')
# get gluster pod name
OCS_POD=$(oc get po -n $OCS_PROJECT -l glusterfs-node=pod --no-headers | head -1 |awk '{print $1}' )
# get volume info
echo 'Displaying volume '$VOL' info thru pod '$OCS_POD
oc rsh -n $OCS_PROJECT $OCS_POD gluster volume info $VOL
