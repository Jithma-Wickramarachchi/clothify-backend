package edu.icet.clothifybackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothifybackend.dto.StockDto;
import edu.icet.clothifybackend.exception.GlobalExceptionHandler;
import edu.icet.clothifybackend.exception.StockIdNotFoundException;
import edu.icet.clothifybackend.service.StockService;
import lombok.RequiredArgsConstructor;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StockController.class)
@RequiredArgsConstructor
class StockControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private StockService service;
    @InjectMocks
    private StockController controller;

    private StockDto stockDto;
    private StockDto stockDtoWithId;
    private StockDto nullStockDto;
    private StockDto invalidStockDto;

    @BeforeEach
    void init() {
        controller = new StockController(service);

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        MockitoAnnotations.openMocks(this);

        this.stockDto = StockDto.builder()
                .stockId(null)
                .companyName("Brandix")
                .initialItemCount(514)
                .availableItemCount(210)
                .date(LocalDate.parse("2024-04-23"))
                .build();

        this.stockDtoWithId = StockDto.builder()
                .stockId(1L)
                .companyName("Brandix")
                .initialItemCount(514)
                .availableItemCount(210)
                .date(LocalDate.parse("2024-04-23"))
                .build();

        this.nullStockDto = StockDto.builder()
                .stockId(null)
                .companyName(null)
                .initialItemCount(null)
                .availableItemCount(null)
                .date(null)
                .build();

        this.invalidStockDto = StockDto.builder()
                .stockId(1L)
                .companyName("")
                .initialItemCount(0)
                .availableItemCount(0)
                .date(null)
                .build();
    }

    @Test
    @DisplayName(value = "Throw MethodArgumentNotValidException for null RequestDto")
    void catchMethodArgumentNotValidExceptionForNullRequestDto() throws Exception {
        ResultActions nullResponse = mockMvc.perform(post("/api/v1/stock")
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(nullStockDto)))
                .andExpect(status().isBadRequest());

        nullResponse.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.companyName", CoreMatchers.is("companyName cannot be blank or null")))
                .andExpect(jsonPath("$.initialItemCount", CoreMatchers.is("initial item count cannot be null")))
                .andExpect(jsonPath("$.availableItemCount", CoreMatchers.is("available item count cannot be null")))
                .andExpect(jsonPath("$.date", CoreMatchers.is("date cannot be null")))
                .andDo(print());
    }
    @Test
    @DisplayName(value = "Throw MethodArgumentNotValidException for invalid RequestDto")
    void catchMethodArgumentNotValidExceptionForInvalidRequestDto() throws Exception {
        ResultActions invalidResponse = mockMvc.perform(post("/api/v1/stock")
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(invalidStockDto)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertInstanceOf(MethodArgumentNotValidException.class, result.getResolvedException()));


        invalidResponse.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.companyName", CoreMatchers.is("companyName cannot be blank or null")))
                .andExpect(jsonPath("$.initialItemCount", CoreMatchers.is("initial item count should be positive number")))
                .andExpect(jsonPath("$.availableItemCount", CoreMatchers.is("available item count should be positive number")))
                .andExpect(jsonPath("$.date", CoreMatchers.is("date cannot be null")))
                .andDo(print());
    }
    @Test
    @DisplayName(value = "Stock successfully creation")
    void givenStockDto_whenCreateStock_thenReturnCreatedStockDto() throws Exception {

        given(service.saveStock(any()))
                .willReturn(stockDtoWithId);

        ResultActions response = mockMvc.perform(post("/api/v1/stock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(stockDto)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.stockId", CoreMatchers.is(1)))
                .andExpect(jsonPath("$.companyName", CoreMatchers.is("Brandix")))
                .andExpect(jsonPath("$.initialItemCount", CoreMatchers.is(514)))
                .andExpect(jsonPath("$.availableItemCount", CoreMatchers.is(210)))
                .andExpect(jsonPath("$.date[0]", CoreMatchers.is(stockDtoWithId.getDate().getYear())))
                .andExpect(jsonPath("$.date[1]", CoreMatchers.is(stockDtoWithId.getDate().getMonthValue())))
                .andExpect(jsonPath("$.date[2]", CoreMatchers.is(stockDtoWithId.getDate().getDayOfMonth())))
                .andDo(print());
    }
    @Test
    @DisplayName(value = "Get stock by stockId success")
    void givenStockDtoId_whenGetStockIdFound_thenReturnStockDto() throws Exception {
        Long stockId = stockDtoWithId.getStockId();
        given(service.getStockByStockId(stockId)).willReturn(stockDtoWithId);

        ResultActions response = mockMvc.perform(get(String.format("/api/v1/stock/%s",stockId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(stockId)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.stockId", CoreMatchers.is(1)))
                .andExpect(jsonPath("$.companyName", CoreMatchers.is("Brandix")))
                .andExpect(jsonPath("$.initialItemCount", CoreMatchers.is(514)))
                .andExpect(jsonPath("$.availableItemCount", CoreMatchers.is(210)))
                .andExpect(jsonPath("$.date[0]", CoreMatchers.is(stockDtoWithId.getDate().getYear())))
                .andExpect(jsonPath("$.date[1]", CoreMatchers.is(stockDtoWithId.getDate().getMonthValue())))
                .andExpect(jsonPath("$.date[2]", CoreMatchers.is(stockDtoWithId.getDate().getDayOfMonth())))
                .andDo(print());
    }
    @Test
    @DisplayName(value = "Get stock by stockId failure")
    void givenStockDtoId_whenGetStockIdNotFound_thenThrowStockIdNotFoundException() throws Exception {
        Long stockId = stockDtoWithId.getStockId();
        given(service.getStockByStockId(stockId)).willThrow(new StockIdNotFoundException(stockId));

        ResultActions response = mockMvc.perform(get(String.format("/api/v1/stock/%s", stockId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(stockId)));

        response.andExpect(status().isNotFound())
                .andExpect
                        (result -> assertInstanceOf(StockIdNotFoundException.class, result.getResolvedException()))
                .andExpect
                        (result -> assertEquals("Stock Id not found! Id:"+stockId, Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
    @Test
    @DisplayName("Get All stocks success")
    void whenCallMethod_thenReturnAllStocksAsList() throws Exception {
        given(service.getAllStocks()).willReturn(Arrays.asList(stockDtoWithId, stockDtoWithId, stockDtoWithId));

        ResultActions response = mockMvc.perform(get("/api/v1/stock/"));

        response.andExpect(status().isOk())
                //check first stock in list
                .andExpect(jsonPath("$[0].stockId", CoreMatchers.is(1)))
                .andExpect(jsonPath("$[0].companyName", CoreMatchers.is("Brandix")))
                .andExpect(jsonPath("$[0].initialItemCount", CoreMatchers.is(514)))
                .andExpect(jsonPath("$[0].availableItemCount", CoreMatchers.is(210)))
                .andExpect(jsonPath("$[0].date[0]", CoreMatchers.is(stockDtoWithId.getDate().getYear())))
                .andExpect(jsonPath("$[0].date[1]", CoreMatchers.is(stockDtoWithId.getDate().getMonthValue())))
                .andExpect(jsonPath("$[0].date[2]", CoreMatchers.is(stockDtoWithId.getDate().getDayOfMonth())))

                //check second stock in list
                .andExpect(jsonPath("$[1].stockId", CoreMatchers.is(1)))
                .andExpect(jsonPath("$[1].companyName", CoreMatchers.is("Brandix")))
                .andExpect(jsonPath("$[1].initialItemCount", CoreMatchers.is(514)))
                .andExpect(jsonPath("$[1].availableItemCount", CoreMatchers.is(210)))
                .andExpect(jsonPath("$[1].date[0]", CoreMatchers.is(stockDtoWithId.getDate().getYear())))
                .andExpect(jsonPath("$[1].date[1]", CoreMatchers.is(stockDtoWithId.getDate().getMonthValue())))
                .andExpect(jsonPath("$[1].date[2]", CoreMatchers.is(stockDtoWithId.getDate().getDayOfMonth())))

                //check third stock in list
                .andExpect(jsonPath("$[2].stockId", CoreMatchers.is(1)))
                .andExpect(jsonPath("$[2].companyName", CoreMatchers.is("Brandix")))
                .andExpect(jsonPath("$[2].initialItemCount", CoreMatchers.is(514)))
                .andExpect(jsonPath("$[2].availableItemCount", CoreMatchers.is(210)))
                .andExpect(jsonPath("$[2].date[0]", CoreMatchers.is(stockDtoWithId.getDate().getYear())))
                .andExpect(jsonPath("$[2].date[1]", CoreMatchers.is(stockDtoWithId.getDate().getMonthValue())))
                .andExpect(jsonPath("$[2].date[2]", CoreMatchers.is(stockDtoWithId.getDate().getDayOfMonth())))
                .andDo(print());
    }

}
