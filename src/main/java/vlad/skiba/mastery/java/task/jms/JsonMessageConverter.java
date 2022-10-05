package vlad.skiba.mastery.java.task.jms;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.jms.*;

/**
 * Copyright Vlad Skiba (c) 2022.
 */
@Component
public class JsonMessageConverter implements MessageConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonMessageConverter.class);

    @Autowired
    private ObjectMapper mapper;

    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        String json;
        try {
            json = mapper.writeValueAsString(object);
        } catch (Exception ex) {
            LOGGER.error("Message cannot be parsed: " + ex);
            throw new MessageConversionException("Message cannot be parsed: ", ex);
        }
        TextMessage message = session.createTextMessage();
        message.setText(json);
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        return ((TextMessage) message).getText();
    }

}
