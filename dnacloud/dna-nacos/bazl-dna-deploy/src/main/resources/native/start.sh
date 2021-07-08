#!/bin/bash
echo 'Welcome DNA cloud project'!
echo 'start...'

#------------------------------------------------------------------

selfpath=$(cd "$(dirname "$0")"; pwd) 
cd $selfpath
mkdir logs
VERSION=1.0.0-SNAPSHOT.jar

function execute() {
	./$1/start.sh
	sleep 15
}

#------------------------------------------------------------------

execute bazl-dna-eureka
execute bazl-dna-config
execute bazl-dna-gateway
execute bazl-dna-system
execute bazl-dna-monitor
echo 'finish!'
