package com.example.demoapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecuritySmokeTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createCourseWithoutTokenShouldReturnUnauthorized() throws Exception {
        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  \"title\": \"Test Course\",
                                  \"category\": \"Security\",
                                  \"duration\": 2,
                                  \"published\": true
                                }
                                """))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser(roles = "USER")
    @Test
    void deleteCourseAsUserShouldReturnForbidden() throws Exception {
        mockMvc.perform(delete("/api/courses/123"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void deleteCourseAsAdminShouldNotReturnForbidden() throws Exception {
        mockMvc.perform(delete("/api/courses/123"))
                .andExpect(result ->
                        org.assertj.core.api.Assertions.assertThat(result.getResponse().getStatus())
                                .isNotEqualTo(403));
    }
}
