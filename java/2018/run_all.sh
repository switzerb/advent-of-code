#!/usr/bin/env bash
for i in `find . -name run.sh | sort`; do
  # echo '#!/usr/bin/env bash' > $i
  # echo 'cd `dirname $0`' >> $i
  # echo 'mvn exec:java -Dexec.mainClass=com.brennaswitzer.aoc.Main' >> $i
  $i
done
