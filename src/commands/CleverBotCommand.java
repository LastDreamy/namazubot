package commands;
import main.Main;
import Utils.Constants;
import Utils.MessageUtils;
import com.michaelwflaherty.cleverbotapi.CleverBotQuery;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;


public class CleverBotCommand extends _BaseCommand{
	private final CleverBotQuery bot;
	
	public CleverBotCommand() {
		super("CleverBot");
		String cleverBotToken = "CC686Y0-zW0Nho6hUl8F0snICIw";

		bot = cleverBotToken != null ? new CleverBotQuery(cleverBotToken, "") : null;
	}

	@Override
	protected void doStuff(final MessageReceivedEvent event) {
		if(bot == null) {
	        MessageUtils.sendMessage(event.getChannel(), "CleverBot API is unavailable. Please contact an Admin of the server.");
			return;
		}
		try {
			if(event.getMessage().getMentions().contains(Main.getClient().getOurUser())) {
				String message = event.getMessage().getContent();
//				MessageUtils.sendMessage(event.getChannel(), "```" + message + "```");
//				MessageUtils.sendMessage(event.getChannel(), message);
				message = message.replace(Main.getClient().getOurUser().toString().replace("!", ""), "").trim();
				if(message.equals("")) {
					System.out.println("Finished Conversation with ID: " + bot.getConversationID());
					bot.setConversationID("");
					return;
				}
				bot.setPhrase(message);
	    		event.getChannel().toggleTypingStatus();
		        bot.sendRequest();
		        String response = bot.getResponse();
		        MessageUtils.sendMessage(event.getChannel(), response);
			}
		} catch (Exception e) {
			MessageUtils.sendMessage(event.getChannel(), "Oops");
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	protected String help(final MessageReceivedEvent event, Constants.HELP_TYPE helpType) {
		String ret = "";
		if(Constants.HELP_TYPE.SHORT.equals(helpType)){
			ret += "Parler avec NamazuBot";
		}else{
			ret += "```md" + System.lineSeparator();
			ret += "Talk Command" + System.lineSeparator();
			ret += "===============" + System.lineSeparator();
			ret += System.lineSeparator();
			ret += "[*][Usage][*]" + System.lineSeparator();
			ret += "* @NamazuBot \"Message\"" + System.lineSeparator();
			ret += ">    Envoyez un message à NamazuBot et il répondra." + System.lineSeparator();
			ret += "```";
		}
		return ret;
	}
}