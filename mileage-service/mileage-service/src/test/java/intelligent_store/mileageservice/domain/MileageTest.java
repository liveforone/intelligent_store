package intelligent_store.mileageservice.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MileageTest {

    @Test
    void createTest() {
        //given
        String username = "dsjlfsjlfsjdlfjsdlfjdslkfjs";

        //when
        Mileage mileage = Mileage.create(username);

        //then
        assertThat(mileage.getUsername())
                .isEqualTo(username);
    }

    @Test
    void accumulateTest() {
        //given
        String username = "dsjlfsjlfsjdlfjsdlfjdslkfjs";
        Mileage mileage = Mileage.create(username);

        //when
        long itemPrice = 10000;
        mileage.accumulate(itemPrice);

        //then
        long accumulatePoint = (long) (itemPrice * 0.01);
        assertThat(mileage.getMileagePoint())
                .isEqualTo(accumulatePoint);
    }

    @Test
    void useMileageTest() {
        //given
        String username = "dsjlfsjlfsjdlfjsdlfjdslkfjs";
        Mileage mileage = Mileage.create(username);
        long itemPrice = 30000;
        mileage.accumulate(itemPrice);

        //when
        long spentMileage = 200;
        mileage.useMileage(spentMileage);

        //then
        assertThat(mileage.getMileagePoint())
                .isEqualTo(100);
    }
}