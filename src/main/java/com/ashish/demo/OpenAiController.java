package com.ashish.demo;

import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAiController {
		private final AzureOpenAiChatModel chatModel;

		public OpenAiController(AzureOpenAiChatModel chatModel) { //constructor injection
				this.chatModel = chatModel;
		}

		@GetMapping("/api/{message}")
		public String getResponse(@PathVariable String message){
				String response = chatModel.call(message);
				return response;
		}
}
