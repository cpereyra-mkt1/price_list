package com.ecommerce.filterprices.infrastructure.adapter.api.controller;

import com.ecommerce.filterprices.domain.port.service.PriceService;
import com.ecommerce.filterprices.infrastructure.adapter.api.mapper.PriceApiMapper;
import com.ecommerce.filterprices.infrastructure.adapter.db.DbPriceRepository;
import com.ecommerce.filterprices.infrastructure.adapter.db.repository.PriceRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.Assert;
import org.springframework.web.context.request.NativeWebRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PriceApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private NativeWebRequest nativeWebRequest;

    @Autowired
    private PriceService priceService;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private PriceApiController priceApiController;

    @Autowired
    private DbPriceRepository dbPriceRepository;

    private final PriceApiMapper mapper = Mappers.getMapper(PriceApiMapper.class);

    private static final String  URL = "/price-manager-service/api/v1/price?product_id={pid}&chain_id={cid}&application_date={date}";
    private static final String  PRODUCT_ID = "35455";
    private static final String  CHAIN_ID = "1";
    private static final String  TEST_CASE_1_DATE = "2020-06-14T10:00:00.000Z";
    private static final String  TEST_CASE_2_DATE = "2020-06-14T16:00:00.000Z";
    private static final String  TEST_CASE_3_DATE = "2020-06-14T21:00:00.000Z";
    private static final String  TEST_CASE_4_DATE = "2020-06-15T10:00:00.000Z";
    private static final String  TEST_CASE_5_DATE = "2020-06-16T21:00:00.000Z";
    private static final double  TEST_CASE_1_EXPECTED_PRICE = 35.5;
    private static final double  TEST_CASE_2_EXPECTED_PRICE = 25.45;
    private static final double  TEST_CASE_3_EXPECTED_PRICE = 35.5;
    private static final double  TEST_CASE_4_EXPECTED_PRICE = 30.5;
    private static final double  TEST_CASE_5_EXPECTED_PRICE = 38.95;

    @BeforeEach
    void setMockOutput() throws Exception {
        ReflectionTestUtils.setField(priceApiController, "service", priceService);
    }
    @Test
    void test_case_1() throws Exception{
        Double price = executeGet(get(URL, PRODUCT_ID, CHAIN_ID, TEST_CASE_1_DATE));
        Assert.isTrue( price.equals(TEST_CASE_1_EXPECTED_PRICE),"Price is not equal to: " + TEST_CASE_1_EXPECTED_PRICE);
    }

    @Test
    void test_case_2() throws Exception{
        Double price = executeGet(get(URL, PRODUCT_ID, CHAIN_ID, TEST_CASE_2_DATE));
        Assert.isTrue( price.equals(TEST_CASE_2_EXPECTED_PRICE),"Price is not equal to: " + TEST_CASE_2_EXPECTED_PRICE);
    }

    @Test
    void test_case_3() throws Exception{
        Double price = executeGet(get(URL, PRODUCT_ID, CHAIN_ID, TEST_CASE_3_DATE));
        Assert.isTrue( price.equals(TEST_CASE_3_EXPECTED_PRICE),"Price is not equal to: " + TEST_CASE_3_EXPECTED_PRICE);;
    }

    @Test
    void test_case_4() throws Exception{
        Double price = executeGet(get(URL, PRODUCT_ID, CHAIN_ID, TEST_CASE_4_DATE));
        Assert.isTrue( price.equals(TEST_CASE_4_EXPECTED_PRICE),"Price is not equal to:  " + TEST_CASE_4_EXPECTED_PRICE);
    }
    @Test
    void test_case_5() throws Exception{
        Double price = executeGet(get(URL, PRODUCT_ID, CHAIN_ID, TEST_CASE_5_DATE));
        Assert.isTrue( price.equals(TEST_CASE_5_EXPECTED_PRICE),"Price is not equal to:" + TEST_CASE_5_EXPECTED_PRICE);
    }

    private Double executeGet(MockHttpServletRequestBuilder URL) throws Exception {
        MvcResult result = this.mockMvc.perform(URL
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        return JsonPath.read(result.getResponse().getContentAsString(), "$.price");
    }
}