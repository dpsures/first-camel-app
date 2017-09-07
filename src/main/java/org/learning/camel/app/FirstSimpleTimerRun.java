package org.learning.camel.app;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FirstSimpleTimerRun {
	public static void main(String[] args) throws Exception {
		CamelContext context = new DefaultCamelContext();
		try {
			context.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					from("timer://myTimer?period=2000")
					.setBody()
					.simple("Camel fired at ${header.firedTime}")
					.to("stream:out");
				}
			});
			context.start();
			Thread.sleep(10000);
			System.out.println("App run completed");
		} finally {
			context.stop();
		}
	}
}
