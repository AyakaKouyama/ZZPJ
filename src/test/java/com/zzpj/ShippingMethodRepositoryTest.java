package com.zzpj;

import com.zzpj.entities.ShippingMethod;
import com.zzpj.repositories.ShippingMethodRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.Application;
import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ShippingMethodRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;


    @Autowired
    private ShippingMethodRepository shippingMethodRepository;

    @Test
    public void whenFindByName_thenReturnShippingMethod(){
        //given
        ShippingMethod shippingMethod = new ShippingMethod();
        shippingMethod.setId(3L);
        shippingMethod.setName("test");
        shippingMethod.setPrice(new BigDecimal(1));

        entityManager.persist(shippingMethod);
        entityManager.flush();

        //when
        Optional<ShippingMethod> shippingMethodOptional = shippingMethodRepository.findById(3L);

        //then
        assertThat(shippingMethodOptional.isPresent()).isEqualTo(true);
        assertThat(shippingMethodOptional.get().getName()).isSameAs("test");
        assertThat(shippingMethodOptional.get().getId()).isEqualTo(3L);
        assertThat(shippingMethodOptional.get().getPrice()).isEqualTo(new BigDecimal(1));
    }

}
