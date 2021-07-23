#!/bin/bash
echo 'Stop DNA cloud project'!
echo 'start...'

#------------------------------------------------------------------

selfpath=$(cd "$(dirname "$0")"; pwd) 
cd $selfpath

function exec() {
	tpid=`cat $1/tpid`
	rm -f $1/tpid
	if [ ${tpid} ]; then
		echo "Kill '${tpid}' $1 Process!"
		kill -9 $tpid
	else
		echo "$1 Stop Success!"
	fi
	sleep 5
}

#------------------------------------------------------------------

exec bazl-dna-files
exec bazl-dna-turbine
exec bazl-dna-system
exec bazl-dna-zuul
exec bazl-dna-config
exec bazl-dna-zipkin
exec bazl-dna-eureka
echo 'finish!'