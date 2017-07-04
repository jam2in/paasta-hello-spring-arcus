package org.openpaas.controller;

import org.openpaas.arcus.ArcusCache;
import org.openpaas.support.ArcusSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ArcusSupport arcusSupport;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(Model model) {
		logger.info("Welcome home!");

		ArcusCache arcusCacheClient = arcusSupport.getArcusCache();

		Future<Boolean> futureSet = null;
		Future<Object> futureGet = null;

		String sampleKey = "sampleKey";
		String value = null;

		try {
			futureSet = arcusCacheClient.set(sampleKey,10, "sampleValue");
			futureSet.get();
		} catch (InterruptedException e) {
			futureSet.cancel(true);
			e.printStackTrace();
		} catch (ExecutionException e) {
			futureSet.cancel(true);
			e.printStackTrace();
		}

		try {
			futureGet = arcusCacheClient.get(sampleKey);
			value = (String)futureGet.get();
		} catch (InterruptedException e) {
			futureGet.cancel(true);
			e.printStackTrace();
		} catch (ExecutionException e) {
			futureSet.cancel(true);
			e.printStackTrace();
		}

		model.addAttribute("key", sampleKey);
		model.addAttribute("value", value);

		return "home";
	}

}
