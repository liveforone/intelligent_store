package intelligent_store.shopservice.consumer.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import intelligent_store.shopservice.consumer.log.ConsumerLog;
import intelligent_store.shopservice.dto.ShopRequest;
import intelligent_store.shopservice.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static intelligent_store.shopservice.consumer.model.ConsumerTopic.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class ShopConsumer {

    //service 호출
    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = CREATE_SHOP)
    public void createShop(String kafkaMessage) throws JsonProcessingException {
        log.info(ConsumerLog.KAFKA_RECEIVE_LOG.getLog() + kafkaMessage);

        ShopRequest requestDto = objectMapper.readValue(kafkaMessage, ShopRequest.class);

        if (CommonUtils.isNull(requestDto)) {
            log.info(ConsumerLog.KAFKA_NULL_LOG.getLog());
        } else {
            //service
            log.info(ConsumerLog.CREATE_SHOP_SUCCESS.getLog() + requestDto.getUsername());
        }
    }

    @KafkaListener(topics = REMOVE_SHOP_BELONG_MEMBER)
    public void removeShop(String kafkaMessage) throws JsonProcessingException {
        log.info(ConsumerLog.KAFKA_RECEIVE_LOG.getLog() + kafkaMessage);

        String username = objectMapper.readValue(kafkaMessage, String.class);

        if (CommonUtils.isNull(username)) {
            log.info(ConsumerLog.KAFKA_NULL_LOG.getLog());
        } else {
            //service
            log.info(ConsumerLog.REMOVE_SHOP_SUCCESS.getLog() + username);
        }
    }
}
