package com.ashish.demo;

import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ai.chat.memory.InMemoryChatMemory;

import java.util.Map;


@RestController
public class OpenAiController {
		private final ChatClient chatClient;

//		public OpenAiController(AzureOpenAiChatModel chatModel) { //constructor injection
//				this.chatClient = ChatClient.create(chatModel);            // We need to do this when we multiple models are there and we want to inject a specific model. But if we have only one model then we can directly inject the chat client as shown in the constructor below.
//		}
		public OpenAiController(ChatClient.Builder builder){
				this .chatClient = builder
								.defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory())) // this will add the MessageChatMemoryAdvisor to the chat client, which will allow us to store the conversation history in memory and use it for future interactions.
								.build();     // this will work when we have only one model and we want to inject the chat client directly without worrying about which model is being used. The builder will automatically use the available model to create the chat client.
		}


		@GetMapping("/api/{message}")
		public org.springframework.http.ResponseEntity<String> getResponse(@PathVariable String message){
				String response = chatClient.prompt(message)
								.call()
								.content();

				return ResponseEntity.ok(response);

		}
		@PostMapping("/api/recommend")
		public String recommend(@RequestParam String type , @RequestParam String year , @RequestParam String lang){
				String temp = """
								Recommend me a list of 5 %s movies released in %s and in %s language. 
								Please provide the list in a comma separated format without numbering or bullet points.
								""".formatted(type, year, lang);
				PromptTemplate promptTemplate = new PromptTemplate(temp);
				Prompt prompt = promptTemplate.create(Map.of("type", type, "year", year, "lang", lang));

				String response = chatClient.prompt(prompt).call().content();
				return  response ;
		}
}
