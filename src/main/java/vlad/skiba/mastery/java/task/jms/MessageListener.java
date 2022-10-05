package vlad.skiba.mastery.java.task.jms;

import org.springframework.beans.factory.annotation.Autowired;
import vlad.skiba.mastery.java.task.entities.TransferableEntity;
import vlad.skiba.mastery.java.task.services.EmployeeService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Copyright Vlad Skiba (c) 2022.
 */
@Component
public class MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private EmployeeService employeeService;

    @JmsListener(destination = "MessageQueue")
    public void receiveMessage(final TransferableEntity entity) {
        LOGGER.debug("Received message: \n" + entity);
        employeeService.updateJobTitleByDepartmentId(entity.getDepartmentId(), entity.getJobTitle());
    }

}
