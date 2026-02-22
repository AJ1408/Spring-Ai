# GitHub Models (Azure OpenAI) Integration Setup

## Prerequisites
- Java 17 or higher
- Maven
- GitHub Account

## Getting Your GitHub Token

### Option 1: GitHub Personal Access Token (Classic)
1. Go to [GitHub Settings](https://github.com/settings/tokens)
2. Click **Developer settings** → **Personal access tokens** → **Tokens (classic)**
3. Click **Generate new token (classic)**
4. Give it a name (e.g., "Spring AI Project")
5. Select scopes (at minimum, select `repo` and `user`)
6. Click **Generate token**
7. **Copy the token immediately** (you won't be able to see it again)

### Option 2: GitHub Models Marketplace
1. Visit [GitHub Models Marketplace](https://github.com/marketplace/models)
2. Select a model (e.g., GPT-4o)
3. Follow the instructions to get your API key

## Setting Up the Environment

### On Windows (PowerShell)
```powershell
# Set the environment variable for the current session
$env:GITHUB_TOKEN="your_github_token_here"

# Or set it permanently
[System.Environment]::SetEnvironmentVariable('GITHUB_TOKEN', 'your_github_token_here', 'User')
```

### On Windows (Command Prompt)
```cmd
set GITHUB_TOKEN=your_github_token_here
```

### On Linux/Mac
```bash
export GITHUB_TOKEN=your_github_token_here
```

## Running the Application

1. **Build the project:**
   ```bash
   mvn clean install
   ```

2. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

3. **Test the API:**
   Open your browser or use curl:
   ```bash
   http://localhost:8080/api/Hello
   ```
   
   Or with curl:
   ```bash
   curl http://localhost:8080/api/Hello
   ```

## Available Models

You can change the model in `application.properties`:

- `gpt-4o` (default)
- `gpt-4o-mini`
- `gpt-4-turbo`
- `gpt-3.5-turbo`

## Configuration Options

Edit `src/main/resources/application.properties`:

```properties
# Model selection
spring.ai.azure.openai.chat.options.deployment-name=gpt-4o

# Temperature (0.0 to 2.0) - higher = more creative
spring.ai.azure.openai.chat.options.temperature=0.7

# Max tokens in response
spring.ai.azure.openai.chat.options.max-tokens=1000
```

## Troubleshooting

### Error: "Failed to instantiate... API key must be set"
- Make sure you've set the `GITHUB_TOKEN` environment variable
- Restart your IDE/terminal after setting the environment variable
- Verify the token is valid and has correct permissions

### Error: "Vertex AI project-id must be set"
- Make sure the Vertex AI dependency is commented out in `pom.xml`
- Run `mvn clean install` to refresh dependencies

## API Endpoints

- `GET /api/{message}` - Send a message to the AI model
  - Example: `http://localhost:8080/api/What is Java?`

## Switching Back to Vertex AI

If you want to use Vertex AI instead:

1. Uncomment Vertex AI dependency in `pom.xml`
2. Comment out Azure OpenAI dependency
3. Uncomment Vertex AI configuration in `application.properties`
4. Comment out Azure OpenAI configuration
5. Update `OpenAiController.java` to use `VertexAiGeminiChatModel`
6. Run `mvn clean install`
