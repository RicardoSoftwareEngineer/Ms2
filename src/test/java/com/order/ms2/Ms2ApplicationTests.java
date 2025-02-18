package com.order.ms2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.ms2.dto.OrderDTO;
import com.order.ms2.entity.ItemEntity;
import com.order.ms2.entity.OrderStatus;
import com.order.ms2.queue.Receiver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
class Ms2ApplicationTests {

	@Autowired
	private Receiver receiver;
	@Mock
	private RabbitTemplate rabbitTemplate;
	private TestUtils testUtils = new TestUtils();
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void test_total_price_calculation() throws JsonProcessingException, InterruptedException {
		doNothing().when(rabbitTemplate).convertAndSend(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

		OrderDTO orderDTO = testUtils.createSampleOrder();
		String message = objectMapper.writeValueAsString(orderDTO);
		orderDTO = receiver.receiveMessage(message);
		assertEquals(OrderStatus.PROCESSING, orderDTO.getStatus());
		assertEquals(0.0, orderDTO.getTotal());
	}

	@Test
	void test_microservice_contract() throws Exception {

	}
}
