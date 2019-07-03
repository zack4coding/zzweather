kill -9 $(jps |grep zzweather|awk '{print $1}')
nohup java -jar zzweather-webflux/target/*.jar >/var/log/zzweather.log 2>&1 &