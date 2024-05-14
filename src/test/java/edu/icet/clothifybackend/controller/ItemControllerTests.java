package edu.icet.clothifybackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothifybackend.controller.item.ItemController;
import edu.icet.clothifybackend.dto.item.ItemDto;
import edu.icet.clothifybackend.exception.GlobalExceptionHandler;
import edu.icet.clothifybackend.service.item.ItemService;
import edu.icet.clothifybackend.service.util.ItemMapper;
import edu.icet.clothifybackend.util.Category;
import edu.icet.clothifybackend.util.Size;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
@WebMvcTest(ItemController.class)
class ItemControllerTests {
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    private final ItemMapper itemMapper;
    ItemControllerTests(ObjectMapper objectMapper, ItemMapper itemMapper){
        this.objectMapper = objectMapper;
        this.itemMapper = itemMapper;
    }
    @MockBean
    private ItemService service;
    @InjectMocks
    private ItemController controller;

    private ItemDto itemDto;
    private ItemDto itemDtoWithId;
    private ItemDto nullItemDto;
    private ItemDto invalidItemDto;

    @BeforeEach
    void init() {
        controller = new ItemController(service);

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        MockitoAnnotations.openMocks(this);

        this.itemDto = ItemDto.builder()
                .itemId(null)
                .name("Tommy Hilfiger - red")
                .category(Category.MEN)
                .count(36)
                .description("Best T-shirt for Men")
                .price(3020D)
                .size(Size.M)
                .stockId(1L)
                .build();

        this.itemDtoWithId = ItemDto.builder()
                .itemId(1L)
                .name("Tommy Hilfiger - red")
                .category(Category.MEN)
                .count(36)
                .description("Best T-shirt for Men")
                .price(3020D)
                .size(Size.M)
                .stockId(1L)
                .build();

        this.nullItemDto = ItemDto.builder()
                .itemId(null)
                .name(null)
                .category(null)
                .count(null)
                .description(null)
                .price(null)
                .size(null)
                .stockId(null)
                .build();

        this.invalidItemDto = ItemDto.builder()
                .itemId(null)
                .name("")
                .category(null)
                .count(0)
                .description("")
                .price(0D)
                .size(null)
                .stockId(null)
                .build();
    }

    @Test
    @DisplayName(value = "Throw MethodArgumentNotValidException for nullRequestDto")
    void catchMethodArgumentNotValidExceptionForNullRequestDto() throws Exception {

        ResultActions nullResponse = mockMvc.perform(post("/api/v1/stock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nullItemDto)));

        nullResponse.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name", CoreMatchers.is("item name cannot be blank or null")))
                .andExpect(jsonPath("$.description", CoreMatchers.is("item description cannot be blank or null")))
                .andExpect(jsonPath("$.price", CoreMatchers.is("item price cannot be null")))
                .andExpect(jsonPath("$.category", CoreMatchers.is("item category cannot be blank or null")))
                .andExpect(jsonPath("$.size", CoreMatchers.is("item size cannot be null")))
                .andExpect(jsonPath("$.count", CoreMatchers.is("item count cannot be null")))
                .andExpect(jsonPath("$.stockId", CoreMatchers.is("stockId cannot be null")))
                .andDo(print());
    }
}