package com.zzpj;

import com.zzpj.dtos.ShippingMethodDto;
import com.zzpj.entities.ShippingMethod;
import com.zzpj.repositories.ShippingMethodRepository;
import com.zzpj.services.impl.ShippingMethodServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShippingMethodServiceTest {

    @Mock
    ShippingMethodRepository shippingMethodRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    ShippingMethodServiceImpl shippingMethodService = new ShippingMethodServiceImpl(shippingMethodRepository,
            modelMapper);

    @Test
    public void shouldSortByName() {
        when(shippingMethodRepository.findAll()).thenReturn(createShippingMethodList());

        List<ShippingMethodDto> sorted = shippingMethodService.sortField("name");
        assertThat(sorted.get(0).getName()).isSameAs("a");
        assertThat(sorted.get(1).getName()).isSameAs("b");
        assertThat(sorted.get(2).getName()).isSameAs("c");
        assertThat(sorted.get(3).getName()).isSameAs("d");
        assertThat(sorted.get(4).getName()).isSameAs("f");
    }

    @Test
    public void shouldSortByPrice() {
        when(shippingMethodRepository.findAll()).thenReturn(createShippingMethodList());

        List<ShippingMethodDto> sorted = shippingMethodService.sortField("price");
        assertThat(sorted.get(0).getName()).isSameAs("a");
        assertThat(sorted.get(1).getName()).isSameAs("b");
        assertThat(sorted.get(2).getName()).isSameAs("c");
        assertThat(sorted.get(3).getName()).isSameAs("d");
        assertThat(sorted.get(4).getName()).isSameAs("f");
    }

    @Test
    public void shouldFilterByPrice() {
        when(shippingMethodRepository.findAll()).thenReturn(createShippingMethodList());

        List<ShippingMethodDto> sorted = shippingMethodService.filterField("priceLowerThan", "16.0");
        assertThat(sorted.size()).isEqualTo(3);
        assertThat(sorted.get(0).getName()).isSameAs("b");
        assertThat(sorted.get(1).getName()).isSameAs("a");
        assertThat(sorted.get(2).getName()).isSameAs("c");
    }

    @Test
    public void shouldFilterByPhraseInName() {
        when(shippingMethodRepository.findAll()).thenReturn(createShippingMethodList());

        List<ShippingMethodDto> sorted = shippingMethodService.filterField("phraseInName", "a");
        assertThat(sorted.size()).isEqualTo(1);
        assertThat(sorted.get(0).getName()).isSameAs("a");
    }

    private List<ShippingMethod> createShippingMethodList() {
        List<ShippingMethod> shippingMethodList = new ArrayList<>();

        ShippingMethod shippingMethod1 = new ShippingMethod();
        shippingMethod1.setName("b");
        shippingMethod1.setPrice(new BigDecimal(10));
        shippingMethodList.add(shippingMethod1);

        ShippingMethod shippingMethod2 = new ShippingMethod();
        shippingMethod2.setName("a");
        shippingMethod2.setPrice(new BigDecimal(5));
        shippingMethodList.add(shippingMethod2);

        ShippingMethod shippingMethod3 = new ShippingMethod();
        shippingMethod3.setName("d");
        shippingMethod3.setPrice(new BigDecimal(20));
        shippingMethodList.add(shippingMethod3);

        ShippingMethod shippingMethod4 = new ShippingMethod();
        shippingMethod4.setName("c");
        shippingMethod4.setPrice(new BigDecimal(15));
        shippingMethodList.add(shippingMethod4);

        ShippingMethod shippingMethod5 = new ShippingMethod();
        shippingMethod5.setName("f");
        shippingMethod5.setPrice(new BigDecimal(30));
        shippingMethodList.add(shippingMethod5);

        return shippingMethodList;
    }
}
