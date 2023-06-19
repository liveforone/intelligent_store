package intelligent_store.mileageservice.consumer.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import intelligent_store.mileageservice.consumer.log.KafkaConsumerLog;
import intelligent_store.mileageservice.dto.MileageRequestWhenOrder;
import intelligent_store.mileageservice.dto.OrderFailRollbackMileageRequest;
import intelligent_store.mileageservice.exception.MileageRequestFailException;
import intelligent_store.mileageservice.producer.model.MileageProducer;
import intelligent_store.mileageservice.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static intelligent_store.mileageservice.consumer.model.ConsumerTopic.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class MileageConsumer {

    //service 호출
    private final MileageProducer mileageProducer;
    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = CREATE_MILEAGE)
    public void createMileage(String kafkaMessage) throws JsonProcessingException {
        log.info(KafkaConsumerLog.KAFKA_RECEIVE_LOG.getLog() + kafkaMessage);

        String username = objectMapper.readValue(kafkaMessage, String.class);

        if (CommonUtils.isNull(username)) {
            log.info(KafkaConsumerLog.KAFKA_NULL_LOG.getLog());
        } else {
            //create method 호출
            log.info(KafkaConsumerLog.CREATE_MILEAGE_SUCCESS.getLog() + username);
        }
    }

    @KafkaListener(topics = REMOVE_MILEAGE_BELONG_MEMBER)
    public void removeMileage(String kafkaMessage) throws JsonProcessingException {
        log.info(KafkaConsumerLog.KAFKA_RECEIVE_LOG.getLog() + kafkaMessage);

        String username = objectMapper.readValue(kafkaMessage, String.class);

        if (CommonUtils.isNull(username)) {
            log.info(KafkaConsumerLog.KAFKA_NULL_LOG.getLog());
        } else {
            //delete method 호출
            log.info(KafkaConsumerLog.REMOVE_MILEAGE_BELONG_MEMBER_SUCCESS.getLog() + username);
        }
    }

    @KafkaListener(topics = MILEAGE_REQUEST_WHEN_ORDER)
    public void mileageRequestWhenOrder(String kafkaMessage) throws JsonProcessingException {
        log.info(KafkaConsumerLog.KAFKA_RECEIVE_LOG.getLog() + kafkaMessage);

        MileageRequestWhenOrder requestDto = objectMapper.readValue(kafkaMessage, MileageRequestWhenOrder.class);

        if (CommonUtils.isNull(requestDto)) {
            log.info(KafkaConsumerLog.KAFKA_NULL_LOG.getLog());
        } else {
            try {
                //차감호출
                //적립 호출
            } catch (MileageRequestFailException err) {
                mileageProducer.mileageFailRollbackOrder(requestDto.toMileageFailRollbackOrderDto());
            }
            log.info(KafkaConsumerLog.MILEAGE_REQUEST_WHEN_ORDER_SUCCESS.getLog() + requestDto.getUsername());
        }
    }

    @KafkaListener(topics = ORDER_FAIL_ROLLBACK_MILEAGE)
    public void orderFailRollbackMileage(String kafkaMessage) throws JsonProcessingException {
        log.info(KafkaConsumerLog.KAFKA_RECEIVE_LOG.getLog() + kafkaMessage);

        OrderFailRollbackMileageRequest requestDto = objectMapper.readValue(kafkaMessage, OrderFailRollbackMileageRequest.class);

        if (CommonUtils.isNull(requestDto)) {
            log.info(KafkaConsumerLog.KAFKA_NULL_LOG.getLog());
        } else {
            //롤백처리
            log.info(KafkaConsumerLog.ORDER_FAIL_ROLLBACK_MILEAGE_SUCCESS.getLog() + requestDto.getUsername());
        }
    }
}
