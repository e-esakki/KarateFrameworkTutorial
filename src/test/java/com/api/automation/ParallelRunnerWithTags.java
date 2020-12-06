package com.api.automation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.intuit.karate.Runner.Builder;

public class ParallelRunnerWithTags {

	private static final String CLASS_PATH = "classpath:";
	private static final String DELIMITER = ",";

	@Test
	public void executeKarateTests() {
		// Runner.parallel(getClass(), 5);
		Builder aRunner = new Builder();
		aRunner.path(getLocation());
		// aRunner.tags("@Smoke","@Regression");
		aRunner.tags(getTags());
		aRunner.parallel(5);
		// Runner.parallel(aRunner);
	}

	// Provide the values for location and tags and separate them with ","
	// Use the "," to split the string and create List out of it
		
	/*
	 * private List<String> getTags() { String aTags = System.getProperty("tags",
	 * "@Confidence"); List<String> aTagList = Arrays.asList(aTags); return
	 * aTagList; }
	 * 
	 * private List<String> getLocation() { String aLocation =
	 * System.getProperty("location", "com/api/automation"); List<String>
	 * aLocationList = Arrays.asList(CLASS_PATH + aLocation); return aLocationList;
	 * }
	 */
	private List<String> getTags() {
		String aTags = System.getProperty("tags","@Confidence");
		List<String> aTagList = Collections.emptyList();
		if(aTags.contains(DELIMITER)) {
			String aTagArray[] = aTags.split(DELIMITER);
			aTagList = Arrays.asList(aTagArray);
			return aTagList;
		}
		aTagList = Arrays.asList(aTags);
		return aTagList;
	}
	
	private List<String> getLocation() {
		String aLocation = System.getProperty("location", "com/api/automation");
		List<String> aLocationList = Collections.emptyList();
		if(aLocation.contains(DELIMITER)) {
			String aLocationArray[] = aLocation.split(DELIMITER);
			aLocationList = Arrays.asList(aLocationArray);
			aLocationList.replaceAll((entry) -> {
				return CLASS_PATH + entry;
			});
			return aLocationList;
		}
		aLocationList = Arrays.asList(CLASS_PATH + aLocation);
		return aLocationList;
	}
	
}
