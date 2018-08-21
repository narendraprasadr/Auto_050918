package com.macys.mst.macysnet.execdrivers;

import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import com.macys.mst.artemis.serenityJbehaveLocal.SerenityLocalTestRunner;
import com.macys.mst.macysnet.stepdefs.MIS;
import com.macys.mst.macysnet.stepdefs.MISRestServices;
public class SerenityLocalRunConfig extends SerenityLocalTestRunner {

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration()
		        ,new MIS()
		        ,new MISRestServices()

		);
	}



}
