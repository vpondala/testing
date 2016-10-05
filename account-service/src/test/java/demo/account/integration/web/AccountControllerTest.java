package demo.account.integration.web;

import demo.account.Account;
import demo.account.AccountController;
import demo.account.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void contextLoads() throws Exception {

        String content = "[{\"userId\": \"user\", \"accountNumber\": \"123456789\"}]";

        given(this.accountService.getUserAccounts())
                .willReturn(Collections.singletonList(new Account("user", "123456789")));

        this.mvc.perform(get("/v1/accounts").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(content));
    }

}
