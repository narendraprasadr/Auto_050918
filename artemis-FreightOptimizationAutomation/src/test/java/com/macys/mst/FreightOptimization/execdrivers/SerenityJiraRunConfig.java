package com.macys.mst.FreightOptimization.execdrivers;

import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import com.macys.mst.FreightOptimization.stepdefs.AFOJ35;
import com.macys.mst.FreightOptimization.stepdefs.AFOJ59;
import com.macys.mst.FreightOptimization.stepdefs.AFOJ15;
import com.macys.mst.FreightOptimization.stepdefs.AFOJ17;
import com.macys.mst.FreightOptimization.stepdefs.AFOJRestServices;
import com.macys.mst.FreightOptimization.stepdefs.AFOJ34;
import com.macys.mst.FreightOptimization.stepdefs.AFOJ45;
import com.macys.mst.FreightOptimization.stepdefs.AFOJ37;
import com.macys.mst.FreightOptimization.stepdefs.AFOJ57;
import com.macys.mst.FreightOptimization.stepdefs.FreightOptimization;
import com.macys.mst.artemis.serenityJbehaveJira.SerenityJiraTestRunner;

public class SerenityJiraRunConfig extends SerenityJiraTestRunner {

	@Override
	public InjectableStepsFactory stepsFactory(){
		return new InstanceStepsFactory(configuration(),
				new AFOJ17(),new AFOJ57(),new AFOJ45(),new AFOJ35(),new AFOJ59(),new FreightOptimization(),new AFOJ15(),new AFOJRestServices(),new AFOJ37(),new AFOJ34()

		);
	}

}
