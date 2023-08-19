package com.keyin;

import com.keyin.Entity.User;
import com.keyin.User.UserController;
import com.keyin.User.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowUserInputPage() {
        assertEquals("enter-numbers", userController.showUserInputPage());
    }

    @Test
    public void testShowHomePage() {
        assertEquals("index", userController.showHomePage().getViewName());
    }

    @Test
    public void testProcessUserInput() {
        String numbers = "5 3 7";
        Model model = mock(Model.class);

        String viewName = userController.processUserInput(numbers, model);

        verify(model).addAttribute(eq("jsonData"), anyString());
        assertEquals("process-numbers", viewName);
    }

    @Test
    public void testShowPreviousTrees() {
        List<User> userInputList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userInputList);
        Model model = mock(Model.class);

        String viewName = userController.showPreviousTrees(model);

        verify(model).addAttribute(eq("treeJsonList"), anyList());
        assertEquals("previous-trees", viewName);
    }
}
