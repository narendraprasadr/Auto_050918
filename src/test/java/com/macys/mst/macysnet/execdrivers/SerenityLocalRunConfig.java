package com.macys.mst.macysnet.execdrivers;

import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import com.macys.mst.artemis.serenityJbehaveLocal.SerenityLocalTestRunner;
import com.macys.mst.macysnet.stepdefs.AFOJ;
import com.macys.mst.macysnet.stepdefs.AFOJRestServices;
public class SerenityLocalRunConfig extends SerenityLocalTestRunner {

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration()
				/*,new AFOJ15()
		        ,new AFOJ17()
		        ,new AFOJ32()
		        ,new AFOJ34()
		        ,new AFOJ35()
		        ,new AFOJ37()
		        ,new AFOJ45()
		        ,new AFOJ57()
		        ,new AFOJ59()
		        ,new AFOJ7()
		        ,new AFOJ8()
		        ,new AFOJ10()
		        ,new AFOJ38()*/
				,new AFOJ()
		        ,new AFOJRestServices()


				//new AFOJ35(),new AFOJ34(),new FreightOptimization(),new AFOJRestServices()
		);
	}



}
