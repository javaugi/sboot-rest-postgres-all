/*
 * Copyright (C) 2017 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.filestoragesvc.websvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author bill
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
//@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class AppController {
//public class AppController implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final Logger log = LoggerFactory.getLogger(AppController.class);
    private static final String MONGO_PROFILE = "mongo";
    private static final String FILE_PROFILE = "file";
    private static final String NO_PROFILE_ERROR = "No mode was provided in active profile set. Expected one of the following: mongo, jackrabbit, or file";
    private static final String MISSING_OPERATION_MODE_PROFILE = "Mode active profile not supported. Expected one of the following: mongo, jackrabbit, or file. Found: {}";
    private static final String CANT_SLEEP_FOR_SOME_REASON = "Can't sleep for some reason.";
    private static final String CIM_FILE_SERVICE_APPLICATION_INITILIZED = "CimFileService application initilized";

    /*
    @Autowired
    private ApplicationContext context;

    public boolean containsOperationModeProfile(List<String> activeProfiles) {
        return activeProfiles.contains(MONGO_PROFILE)
                || activeProfiles.contains(FILE_PROFILE);
    }

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        List<String> activeProfiles = Arrays.asList(event.getEnvironment().getActiveProfiles());
        boolean errorOccurred = false;

        if (activeProfiles.isEmpty()) {
            log.error(NO_PROFILE_ERROR);
            errorOccurred = true;
        } else if (!containsOperationModeProfile(activeProfiles)) {
            log.error(MISSING_OPERATION_MODE_PROFILE, activeProfiles);
            errorOccurred = true;
        }

        if (errorOccurred) {
            try {
                Thread.sleep(1000);
                System.exit(1);
            } catch (InterruptedException ex) {
                log.error(CANT_SLEEP_FOR_SOME_REASON, ex);
                Thread.currentThread().interrupt();
            }
        }

    }

    public static void main(String[] args) {
        new SpringApplicationBuilder().listeners(new AppController()).sources(AppController.class).run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args
                -> log.info(CIM_FILE_SERVICE_APPLICATION_INITILIZED);

    }
    // */
}
