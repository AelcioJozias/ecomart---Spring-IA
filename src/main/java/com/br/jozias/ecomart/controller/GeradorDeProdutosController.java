package com.br.jozias.ecomart.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gerador")
public class GeradorDeProdutosController {

    private final ChatClient chatClient;

    public GeradorDeProdutosController(ChatClient.Builder chatClientBuilder) {
        // aqui só um exemplo de como configurar default
        chatClientBuilder.defaultOptions(OpenAiChatOptions.builder()
                        .model(OpenAiApi.ChatModel.CHATGPT_4_O_LATEST)
                        .temperature(1.5)
                        .maxTokens(100)
                .build());
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/ai")
    String gerarProdutos(String userInput) {
        final String pergunta = "Gere 5 produtos ecologicos";
        return this.chatClient.prompt()
                .user(pergunta)
                .call()
                .content();
    }
}
