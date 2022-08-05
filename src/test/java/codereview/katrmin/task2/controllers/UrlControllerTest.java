package codereview.katrmin.task2.controllers;

import codereview.katrmin.task2.dtos.LinkDto;
import codereview.katrmin.task2.dtos.UrlDto;
import codereview.katrmin.task2.services.UrlService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UrlController.class)
class UrlControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UrlService urlService;

    @Test
    void successGettingShortLink() throws Exception {
        String jsonRequest = "{\"original\": \"https://classroom.google.com/c/NTI1NDAzOTg3OTY3/a/NTI1NDAzOTg3OTgy/details\"}";
        String expectedResponse = "L1ink2";
        LinkDto linkDto = new LinkDto(expectedResponse);
        Mockito.when(urlService.generateLink(ArgumentMatchers.any())).thenReturn(linkDto);
        mockMvc.perform(post("/generated-link")
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .content(jsonRequest).accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.link", Matchers.equalTo(expectedResponse)));
    }

    @Test
    void successGettingRedirectionToOriginalUrl() throws Exception {
        String expectedResponse = "https://classroom.google.com/c/NTI1NDAzOTg3OTY3/a/NTI1NDAzOTg3OTgy/details";
        UrlDto urlDto = new UrlDto(expectedResponse);
        Mockito.when(urlService.getOriginalUrl(ArgumentMatchers.any())).thenReturn(urlDto);
        mockMvc.perform(get("/L1ink2")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(expectedResponse));
    }
}