package intelligent_store.mileageservice.service.command;

import intelligent_store.mileageservice.dto.request.MileageRequestWhenOrder;
import intelligent_store.mileageservice.dto.request.OrderFailRollbackMileageRequest;
import intelligent_store.mileageservice.dto.response.MileageResponse;
import intelligent_store.mileageservice.service.query.MileageQueryService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MileageCommandServiceTest {

    @Autowired
    MileageCommandService mileageCommandService;

    @Autowired
    MileageQueryService mileageQueryService;

    @Autowired
    EntityManager em;

    void createMileage(String username) {
        mileageCommandService.createMileage(username);
        em.flush();
        em.clear();
    }

    @Test
    @Transactional
    void createMileageTest() {
        //given
        String username = "dlafjldsjafldjfladjlafj";

        //when
        mileageCommandService.createMileage(username);
        em.flush();
        em.clear();

        //then
        assertThat(mileageQueryService.getMileageByUsername(username).getMileagePoint())
                .isEqualTo(0);
    }

    @Test
    @Transactional
    void accumulateMileageTest() {
        //given
        String username = "dkjslfdnlfsjfd";
        long itemPrice = 30000;
        long spentMileage = 0;
        createMileage(username);

        //when
        MileageRequestWhenOrder requestDto = new MileageRequestWhenOrder();
        requestDto.setUsername(username);
        requestDto.setItemPrice(itemPrice);
        requestDto.setSpentMileage(spentMileage);
        mileageCommandService.accumulateMileage(requestDto);
        em.flush();
        em.clear();

        //then
        assertThat(mileageQueryService.getMileageByUsername(username).getMileagePoint())
                .isEqualTo((long) (itemPrice * 0.01));
    }

    @Test
    @Transactional
    void useMileageTest() {
        String username = "aaaaaaaaaaaaaaaa";
        long itemPrice = 40000;
        long spentMileage = 100;
        createMileage(username);
        MileageRequestWhenOrder requestDto = new MileageRequestWhenOrder();
        requestDto.setUsername(username);
        requestDto.setItemPrice(itemPrice);
        requestDto.setSpentMileage(spentMileage);
        mileageCommandService.accumulateMileage(requestDto);
        em.flush();
        em.clear();

        //when
        mileageCommandService.useMileage(requestDto);
        em.flush();
        em.clear();

        //then
        assertThat(mileageQueryService.getMileageByUsername(username).getMileagePoint())
                .isEqualTo((long) (itemPrice * 0.01) - spentMileage);
    }

    @Test
    @Transactional
    void rollbackMileageTest() {
        //given
        String username = "dfsfefsefwewfwsfsef";
        long itemPrice = 40000;
        long spentMileage = 0;
        createMileage(username);
        MileageRequestWhenOrder requestDto = new MileageRequestWhenOrder();
        requestDto.setUsername(username);
        requestDto.setItemPrice(itemPrice);
        requestDto.setSpentMileage(spentMileage);
        mileageCommandService.accumulateMileage(requestDto);
        em.flush();
        em.clear();

        //when
        OrderFailRollbackMileageRequest request = new OrderFailRollbackMileageRequest();
        request.setItemPrice(itemPrice);
        request.setUsername(username);
        request.setSpentMileage(spentMileage);
        mileageCommandService.rollbackMileage(request);
        em.flush();
        em.clear();

        //then
        assertThat(mileageQueryService.getMileageByUsername(username).getMileagePoint())
                .isEqualTo(0);
    }

    @Test
    @Transactional
    void removeMileageTest() {
        //given
        String username = "dfsfefsefwewfwsfsef";
        createMileage(username);

        //when
        mileageCommandService.removeMileage(username);
        em.flush();
        em.clear();

        //then
        assertThat(mileageQueryService.getMileageByUsername(username))
                .isEqualTo(new MileageResponse());
    }
}