package com.basejava.cart;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class BaseApplicationTests {

	/*@Autowired
	private EmployeeService employeeService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testPostEmployee() throws Exception{
		User user = User.builder()
				.username("teste")
				.email("teste@gmail.com")
				.password("teste_pass")
				.role(ERole.USER)
				.created(LocalDateTime.now())
				.updated(LocalDateTime.now()).build();
		Employee employee = Employee.builder()
				.user(user)
				.first_name("teste first")
				.last_name("teste last")
				.email("teste@gmail.com")
				.department(EDepartment.HUMAN_RESOURCES).build();

		ObjectMapper objectMapper = new ObjectMapper();

		MockHttpServletResponse response = (MockHttpServletResponse) mockMvc.perform(MockMvcRequestBuilders
				.post("/new")
				.content(asJsonString(employee))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());


	}

	public static String asJsonString(final Object object){
		try{
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}



*/	@Test
	void contextLoads() {
	}

}
