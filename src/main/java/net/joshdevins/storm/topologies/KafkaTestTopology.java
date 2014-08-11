package net.joshdevins.storm.topologies;

import java.util.Map;
import java.util.Properties;

import net.joshdevins.storm.spout.KafkaSpout;
import net.joshdevins.storm.spout.StringScheme;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import backtype.storm.utils.Utils;

public class KafkaTestTopology {


	public static void main(final String[] args) {

		TopologyBuilder builder = new TopologyBuilder();
		Config conf = new Config();
		conf.setDebug(false);
		LocalCluster cluster = new LocalCluster();

		// ensure that you have the same or more partitions on the Kafka broker
		// if parallelism count is greater than partitions, some spouts/consumers will sit idle
		builder.setSpout("t1", createKafkaSpout(), 1);
		builder.setBolt("t2", new BaseRichBolt() {
			private static final long serialVersionUID = 1L;

			@Override
			public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
			
			}

			@Override
			public void execute(Tuple input) {
				System.out.println(input.getValue(0));
			}

			@Override
			public void declareOutputFields(OutputFieldsDeclarer declarer) {
				
			} }).shuffleGrouping("t1");
		
//		Map<String, Object> conf = new HashMap<String, Object>();
//		conf.put(Config.TOPOLOGY_DEBUG, true);

		cluster.submitTopology("test", conf, builder.createTopology());

		// for testing, just leave up for 10 mins then kill it all
		Utils.sleep(10 * 60 * 1000); // 10 mins
		cluster.killTopology("test");
		cluster.shutdown();
	}

	private static IRichSpout createKafkaSpout() {

		// setup Kafka consumer
		Properties kafkaProps = new Properties();
		kafkaProps.put("zookeeper.connect",  "127.0.0.1:2181");
		kafkaProps.put("group.id", "test-consumer-group");
//		kafkaProps.put("zk.connect", "192.168.174.132:2182");
//		kafkaProps.put("zk.connectiontimeout.ms", "1000000");
//		kafkaProps.put("groupid", "storm");
//
		return new KafkaSpout(kafkaProps, "topicT", new StringScheme());
	}
}
