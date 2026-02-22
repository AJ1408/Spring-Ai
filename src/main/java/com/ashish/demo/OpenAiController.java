package com.ashish.demo;

import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAiController {
		private final ChatClient chatClient;

		public OpenAiController(AzureOpenAiChatModel chatModel) { //constructor injection
				this.chatClient = ChatClient.create(chatModel);
		}

		@GetMapping("/api/{message}")
		public String getResponse(@PathVariable String message){
				String response = chatClient.prompt(message)
								.call()
								.content();

				return "with chat client :" + response;
		}
}
