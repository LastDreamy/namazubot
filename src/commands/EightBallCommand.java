package commands;
import java.util.Calendar;
import java.util.Random;

import Utils.Constants;
import Utils.MessageUtils;
import Utils.SharedMethods;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

public class EightBallCommand extends _BaseCommand{
	public static final String[] ANSWERS = new String[]{
		//Goods
		"Certainement", "Peut �tre", "Sans aucun doute", "Bien s�r", "Y'a des chances",
		"Mes sources me disent oui",
		"Certainement", "Peut �tre", "Sans aucun doute", "Bien s�r", "Y'a des chances",
		"Mes sources me disent oui",
		"Certainement", "Peut �tre", "Sans aucun doute", "Bien s�r", "Y'a des chances",
		"Mes sources me disent oui",
		//Neutrals
		"Je suis fatigu� essaye plus tard", "Essaye encore", "Je te dirai �a demain", "J'ai faim d�sol�", "Tu peux faire mieux que �a",
		//Negatives
		"Aucune chance", "Je dirais non", "Mes sources me disent non", "Je ne crois pas", "Y'a peu de chances",
		"Aucune chance", "Je dirais non", "Mes sources me disent non", "Je ne crois pas", "Y'a peu de chances",
		"Aucune chance", "Je dirais non", "Mes sources me disent non", "Je ne crois pas", "Y'a peu de chances",
	};
	
	public EightBallCommand() {
		super("namazudis");
	}

	@Override
	protected void doStuff(final MessageReceivedEvent event) {
		IMessage message = event.getMessage();
		String msg = message.getContent();
		int var = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) * Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
		if(msg.contains(" ")){
			msg = msg.substring(msg.indexOf(" ") + 1).trim();
			try{
                event.getMessage().delete();
            }
            catch(Exception e){e.printStackTrace();};
			if(msg.length() == 0)
				MessageUtils.sendMessage(event.getChannel(), "Je ne peux r�pondre qu'aux questions.");
			else
				MessageUtils.sendMessage(event.getChannel(), "Question(" + SharedMethods.getServerName(event.getAuthor(), event.getGuild()) + "): " + msg + System.lineSeparator() + "R�ponse: " + ANSWERS[new Random(var + msg.toLowerCase().trim().hashCode()).nextInt(ANSWERS.length)]);
		}else
			MessageUtils.sendMessage(event.getChannel(), "Je ne peux r�pondre qu'aux questions.");
	}
	
	@Override
	protected String help(final MessageReceivedEvent event, Constants.HELP_TYPE helpType) {
		String ret = "";
		if(Constants.HELP_TYPE.SHORT.equals(helpType)){
			ret += "Posez moi une question!";
		}else{
			ret += "```md" + System.lineSeparator();
			ret += "EightBall Command" + System.lineSeparator();
			ret += "===============" + System.lineSeparator();
			ret += System.lineSeparator();
			ret += "[*][Usage][*]" + System.lineSeparator();
			ret += "* !namazudis \"question\"" + System.lineSeparator();
			ret += ">    Cette commande te permet de me poser une question." + System.lineSeparator();
			ret += "```";
		}
		return ret;
	}
}
