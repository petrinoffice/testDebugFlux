package com.petr.debug.testdebugflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

@SpringBootApplication
public class TestDebugFluxApplication {

	public static void main(String[] args) {
//		Hooks.onOperatorDebug();

		//ReactorDebugAgent.init();
		//ReactorDebugAgent.processExistingClasses();
		SpringApplication.run(TestDebugFluxApplication.class, args);
	}

}
