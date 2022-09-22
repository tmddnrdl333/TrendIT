chmod 400 J7A301T.pem
rm *.out
ssh -i J7A301T.pem j7a301@cluster.ssafy.io "cd /home/j7a301/trendit/hadoop;hadoop jar trendit.jar wordcount trendit_keyword wordcount_keyword_out"
ssh -i J7A301T.pem j7a301@cluster.ssafy.io "cd /home/j7a301/trendit/hadoop;hdfs dfs -cat wordcount_keyword_out/part-r-00000 | more" >> output1.out
ssh -i J7A301T.pem j7a301@cluster.ssafy.io "cd /home/j7a301/trendit/hadoop;hdfs dfs -cat wordcount_keyword_out/part-r-00001 | more" >> output2.out
