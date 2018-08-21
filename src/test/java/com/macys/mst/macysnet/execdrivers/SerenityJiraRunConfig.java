package com.macys.mst.macysnet.execdrivers;

import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import com.macys.mst.artemis.serenityJbehaveJira.SerenityJiraTestRunner;
import com.macys.mst.macysnet.stepdefs.MIS;
import com.macys.mst.macysnet.stepdefs.MISRestServices;
public class SerenityJiraRunConfig extends SerenityJiraTestRunner {

	@Override
	public InjectableStepsFactory stepsFactory(){
		return new InstanceStepsFactory(configuration(),
				new MIS()
				,new MISRestServices()
		);
	}

}
