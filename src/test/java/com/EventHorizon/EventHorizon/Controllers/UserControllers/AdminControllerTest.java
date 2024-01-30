package com.EventHorizon.EventHorizon.Controllers.UserControllers;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdatedUserDto;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Services.UserServices.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AdminController.class})
@ExtendWith(SpringExtension.class)
class AdminControllerTest {
    @Autowired
    private AdminController adminController;

    @MockBean
    private AdminService adminService;

//    @Test
//    void testAddModerator() throws Exception {
//        User user = new User();
//        user.setActive(1);
//        user.setEmail("jane.doe@example.org");
//        user.setEnable(1);
//        user.setFirstName("Jane");
//        user.setGender(Gender.MALE);
//        user.setId(1);
//        user.setLastName("Doe");
//        user.setPassword("iloveyou");
//        user.setPayPalAccount("3");
//        user.setRole(Role.CLIENT);
//        user.setSignInWithEmail(1);
//        user.setUserName("janedoe");
//        when(adminService.addModerator(Mockito.<UpdatedUserDto>any())).thenReturn(user);
//
//        UpdatedUserDto updatedUserDto = new UpdatedUserDto();
//        updatedUserDto.setEmail("jane.doe@example.org");
//        updatedUserDto.setFirstName("Jane");
//        updatedUserDto.setGender("Gender");
//        updatedUserDto.setId(1);
//        updatedUserDto.setLastName("Doe");
//        updatedUserDto.setPassword("iloveyou");
//        updatedUserDto.setPayPalAccount("3");
//        updatedUserDto.setRole("Role");
//        updatedUserDto.setSignInWithEmail(1);
//        updatedUserDto.setUserName("janedoe");
//        String content = (new ObjectMapper()).writeValueAsString(updatedUserDto);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/addModerator")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//
//        // Act and Assert
//        MockMvcBuilders.standaloneSetup(adminController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    void testDeleteModerator() throws Exception {
//        // Arrange
//        doNothing().when(adminService).deleteModerator(anyInt());
//        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/admin/deleteModerator");
//        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("idOfModerator", String.valueOf(1));
//
//        // Act and Assert
//        MockMvcBuilders.standaloneSetup(adminController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
}
