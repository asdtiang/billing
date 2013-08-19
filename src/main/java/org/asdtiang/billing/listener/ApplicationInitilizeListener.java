package org.asdtiang.billing.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.asdtiang.billing.entity.Person;
import org.asdtiang.billing.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ApplicationInitilizeListener implements ServletContextListener {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ApplicationInitilizeListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LOGGER.debug("initializing..");
		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		PersonRepository personRepository = ctx.getBean(PersonRepository.class);
		personRepository.deleteAll();
		if (personRepository.count() < 10) {
			List<Person> persons = new ArrayList<Person>();

			int itemCount = 100;
			int chunkSize = 25;
			for (int i = 1; i <= itemCount; i++) {
				Person p = new Person();
				p.setAge((i % 100) + 1);
				p.setName("name" + i);
				persons.add(p);
				if ((i % chunkSize) == 0) {
					personRepository.save(persons);
					persons.clear();
				}
			}
			personRepository.save(persons);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
