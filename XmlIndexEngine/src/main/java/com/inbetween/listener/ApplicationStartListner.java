package com.inbetween.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.inbetween.cache.BuildIndexCache;
import com.inbetween.exception.IndexEngineException;

public class ApplicationStartListner implements ApplicationListener<ContextRefreshedEvent> {
	
	private static Log logger = LogFactory.getLog(ApplicationListener.class);
	
	@Autowired
	@Qualifier("buildIndexCacheFrmDBImpl")
	private BuildIndexCache buildIndexCache;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			System.out.println("app started");
			buildIndexCache.updateCache();
		} catch (IndexEngineException e) {
			logger.error("Error while updating the cache", e);
		}
	}

}
