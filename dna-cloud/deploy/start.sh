#!/bin/bash
echo 'Welcome DNA cloud project'!
echo 'start...'

#------------------------------------------------------------------

selfpath=$(cd "$(dirname "$0")"; pwd) 
cd $selfpath
mkdir logs
VERSION=1.0.0-SNAPSHOT.jar

function exec() {
	cd $1
	tpid=`ps -ef|grep $1|grep -v grep|grep -v kill|awk '{print $2}'`
	if [ ${tpid} ]; then
		echo "$1 Stop Process..."
		kill -15 $tpid
	fi
	sleep 5
	
	tpid=`ps -ef|grep $1|grep -v grep|grep -v kill|awk '{print $2}'`
	if [ ${tpid} ]; then
		echo "$1 Kill Process!"
		kill -9 $tpid
	else
		echo "$1 Stop Success!"
	fi
	 
	tpid=`ps -ef|grep $1|grep -v grep|grep -v kill|awk '{print $2}'`
	if [ ${tpid} ]; then
	    echo "$1 is running."
	else
	    echo "$1 is NOT running."
	fi
	 
	rm -f tpid
	nohup java  -jar ./$1-$VERSION  > ../logs/$1.log 2>&1 &
	echo $! > tpid
	echo "$1 Start Success!"
	sleep 30
	cd $selfpath
}

#------------------------------------------------------------------

exec bazl-dna-eureka
exec bazl-dna-zipkin
exec bazl-dna-config
exec bazl-dna-zuul
exec bazl-dna-system
exec bazl-dna-turbine
exec bazl-dna-files
echo 'finish!'
