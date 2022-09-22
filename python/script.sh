chmod 400 J7A301T.pem
rm *.in
rm *.out
scp -i J7A301T.pem -o StrictHostKeyChecking=no keywords_input/keyword_input.in j7a301@cluster.ssafy.io:/home/j7a301/trendit/hadoop
ssh -i J7A301T.pem -o StrictHostKeyChecking=no j7a301@cluster.ssafy.io "cd /home/j7a301/trendit/hadoop;hdfs dfs -put keyword_input.in trendit_keyword"
ssh -i J7A301T.pem -o StrictHostKeyChecking=no j7a301@cluster.ssafy.io "cd /home/j7a301/trendit/hadoop;hadoop jar trendit.jar wordcount trendit_keyword wordcount_keyword_out"
ssh -i J7A301T.pem -o StrictHostKeyChecking=no j7a301@cluster.ssafy.io "cd /home/j7a301/trendit/hadoop;hdfs dfs -cat wordcount_keyword_out/part-r-00000 | more" >> keywords_output/keyword_output1.out
ssh -i J7A301T.pem -o StrictHostKeyChecking=no j7a301@cluster.ssafy.io "cd /home/j7a301/trendit/hadoop;hdfs dfs -cat wordcount_keyword_out/part-r-00001 | more" >> keywords_output/keyword_output2.out
ssh -i J7A301T.pem -o StrictHostKeyChecking=no j7a301@cluster.ssafy.io "cd /home/j7a301/trendit/hadoop;hdfs dfs -rm trendit_keyword/keyword_input.in"
ssh -i J7A301T.pem -o StrictHostKeyChecking=no j7a301@cluster.ssafy.io "cd /home/j7a301/trendit/hadoop;rm /home/j7a301/trendit/hadoop/keyword_input.in"