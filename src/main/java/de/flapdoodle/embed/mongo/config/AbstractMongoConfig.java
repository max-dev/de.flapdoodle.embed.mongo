package de.flapdoodle.embed.mongo.config;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import de.flapdoodle.embed.process.config.ExecutableProcessConfig;
import de.flapdoodle.embed.process.distribution.IVersion;
import de.flapdoodle.embed.process.runtime.Network;


public abstract class AbstractMongoConfig extends ExecutableProcessConfig {

	public static class Storage {
	
		private final int oplogSize;
		private final String replSetName;
		private final String databaseDir;
	
		public Storage() {
			this(null, null, 0);
		}
	
		public Storage(String databaseDir, String replSetName, int oplogSize) {
			this.databaseDir = databaseDir;
			this.replSetName = replSetName;
			this.oplogSize = oplogSize;
		}
	
		public int getOplogSize() {
			return oplogSize;
		}
	
		public String getReplSetName() {
			return replSetName;
		}
	
		public String getDatabaseDir() {
			return databaseDir;
		}
	
	}

	public static class Net {
	
		private final String bindIp;
		private final int port;
		private final boolean ipv6;
	
		public Net() throws UnknownHostException, IOException {
			this(null, Network.getFreeServerPort(), Network.localhostIsIPv6());
		}
	
		public Net(int port, boolean ipv6) {
			this(null, port, ipv6);
		}
	
		public Net(String bindIp, int port, boolean ipv6) {
			this.bindIp = bindIp;
			this.port = port;
			this.ipv6 = ipv6;
		}
	
		public String getBindIp() {
			return bindIp;
		}
	
		public int getPort() {
			return port;
		}
	
		public boolean isIpv6() {
			return ipv6;
		}
	
		public InetAddress getServerAddress() throws UnknownHostException {
			if (bindIp != null) {
				return InetAddress.getByName(bindIp);
			}
			return Network.getLocalHost();
		}
	}

	public static class Timeout {
	
		private final long startupTimeout;
	
		public Timeout() {
			this(20000);
		}
	
		public Timeout(long startupTimeout) {
			this.startupTimeout = startupTimeout;
		}
	
		public long getStartupTimeout() {
			return startupTimeout;
		}
	}

	public AbstractMongoConfig(IVersion version) {
		super(version);
	}

}
