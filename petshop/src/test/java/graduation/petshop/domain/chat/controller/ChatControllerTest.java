package graduation.petshop.domain.chat.controller;

import graduation.petshop.domain.chat.dto.request.ChatMessageRequestDto;
import graduation.petshop.domain.chat.dto.response.ChatMessageResponseDto;
import graduation.petshop.domain.chat.service.ChatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ChatControllerTest {

    @Autowired
    private WebSocketStompClient stompClient;

    @MockBean
    private ChatService chatService;

    private StompSession stompSession;

    @BeforeEach
    public void setup() throws Exception {
        this.stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        this.stompSession = this.stompClient
                .connect("ws://localhost:8080/ws", new WebSocketHttpHeaders(), new StompSessionHandlerAdapter() {})
                .get(1, TimeUnit.SECONDS);
    }

    @Test
    public void testSendMessage() throws Exception {
        ChatMessageRequestDto requestDto = new ChatMessageRequestDto("Hello", 1L, 2L);
        ChatMessageResponseDto expectedResponse = new ChatMessageResponseDto();
        expectedResponse.setMessage("Hello");

        Mockito.when(chatService.sendMessage(Mockito.any(ChatMessageRequestDto.class)))
                .thenReturn(expectedResponse);

        BlockingQueue<ChatMessageResponseDto> blockingQueue = new ArrayBlockingQueue<>(1);

        this.stompSession.subscribe("/user/2/queue/messages", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return ChatMessageResponseDto.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                blockingQueue.offer((ChatMessageResponseDto) payload);
            }
        });

        this.stompSession.send("/app/send/2", requestDto);

        ChatMessageResponseDto response = blockingQueue.poll(5, TimeUnit.SECONDS);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Hello");
    }
}
