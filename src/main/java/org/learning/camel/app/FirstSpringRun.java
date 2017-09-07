package org.learning.camel.app;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FirstSpringRun {

	public static void main(String[] args) throws Exception {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);
		try {
			ProducerTemplate template = camelContext.createProducerTemplate();
			camelContext.start();
			template.sendBody("activemq:employee.queue", "Ran the first apache camel with spring application");
		} finally {
			camelContext.stop();
		}
	}
}
