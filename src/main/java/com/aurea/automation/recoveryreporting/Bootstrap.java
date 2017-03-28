package com.aurea.automation.recoveryreporting;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.jaxrs.JerseyDockerCmdExecFactory;

public class Bootstrap {

	public static void main(String[] args) {
		// System.out.println("start-----");
		
//		StreamUtils s;
		
		DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
				.withDockerHost("unix:///var/run/docker.sock").withDockerTlsVerify(false)
				.build();
		// using jaxrs/jersey implementation here (netty impl is also available)
		DockerCmdExecFactory dockerCmdExecFactory = new JerseyDockerCmdExecFactory().withReadTimeout(1000)
				.withConnectTimeout(1000).withMaxTotalConnections(100).withMaxPerRouteConnections(10);
		
		DockerClient dockerClient = DockerClientBuilder.getInstance(config)
				.withDockerCmdExecFactory(dockerCmdExecFactory).build();
		
		Info info = dockerClient.infoCmd().exec();
		System.out.print(info);
	}

}
