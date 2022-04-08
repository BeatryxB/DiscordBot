package CoreDiscordBot.Utils;

import CoreDiscordBot.Utils.ConfigManager;
import CoreDiscordBot.Utils.command.MessageManager;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.io.File;

public class Main {

    private static DiscordApi api;
    private static CoreDiscordBot.Utils.ConfigManager configManager;

    public static void main(String[] args) {

        configManager = new ConfigManager(new File( "config.toml"));

        api = new DiscordApiBuilder().setToken(configManager.getToml().getString("bot.token")).login().join();

        api.addMessageCreateListener(MessageManager::create);
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }
}
