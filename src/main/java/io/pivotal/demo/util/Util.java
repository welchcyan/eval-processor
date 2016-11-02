package io.pivotal.demo.util;


import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.UUID;

public class Util {


	public static void Hello(String[] args) {
		int i = 0;
		for (int j = 0; j < 10000000; j++) {
			UUID.randomUUID();
		}
	}

	private static final Expression DEFAULT_EXPRESSION = new SpelExpressionParser().parseExpression("payload");

	private Expression expression = DEFAULT_EXPRESSION;

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}
	
}
