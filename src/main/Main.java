package main;
import commands._BaseCommand;
import Utils.IOUtils;
import Utils.BotException;
import Utils.ImageManipulation;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

public class Main {
	private static final String PRIVATE_TOKEN = "MzY4NzkxNDAyNTcxNzU5NjE3.DMPHXQ.wtat26vRjRULzzCYSby2rwCJ7nY";
    public static final String BOT_PREFIX = "!";
	private static IDiscordClient cli;
	
    public static final IDiscordClient getClient(){
    	return cli;
    }
    
    public static void main(String[] args){
        cli = new ClientBuilder().withToken(PRIVATE_TOKEN).build();
        _BaseCommand.COMMANDS.stream().forEach(c -> cli.getDispatcher().registerListener(c));
        cli.login();
        
       
    }
    
    
}

