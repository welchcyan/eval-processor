package io.pivotal.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.config.SpelExpressionConverterConfiguration;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Import;
import org.springframework.expression.EvaluationContext;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.context.IntegrationContextUtils;
import org.springframework.messaging.Message;

import java.util.UUID;

@EnableBinding(Processor.class)
@EnableConfigurationProperties(EvalProcessorProperties.class)
@Import(SpelExpressionConverterConfiguration.class)
public class EvalProcessor {


    @Autowired
    private EvalProcessorProperties properties;

    @Autowired
    @Qualifier(IntegrationContextUtils.INTEGRATION_EVALUATION_CONTEXT_BEAN_NAME)
    private EvaluationContext evaluationContext;    
	
	@ServiceActivator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public Object process(Message<?> message) throws Exception {

		System.out.println(message.getPayload().toString());

		String value;
		value = message.getPayload().toString().trim();
		int loop = Integer.parseInt(value);

		long start = System.currentTimeMillis();

		doSomething(loop);

		long end = System.currentTimeMillis();

		long time = end - start;

		return "Process time = " + time;
	}

	private void doSomething(int loop){
		System.out.println("======Start to process=====");

		for (int j = 0; j < loop; j++) {
			UUID.randomUUID();
		}

		System.out.println("======End to process=====");
	}

}

