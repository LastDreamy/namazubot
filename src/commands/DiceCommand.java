package commands;
import main.Main;
import java.util.List;
import java.util.ArrayList;
import Utils.MessageUtils;
import Utils.SharedMethods;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

   public class DiceCommand extends _BaseCommand{
	public DiceCommand() {
		super("roll");}
	
		@Override
	    protected void doStuff(final MessageReceivedEvent event) {
	        IMessage message = event.getMessage();
	        String msg = message.getContent();
	                String[] words,random;
	                List<String> launches=new ArrayList<String>();
	                int i,n,max;
	        if(msg.contains(" ")){
	            msg = msg.substring(msg.indexOf(" ") + 1);
	                         words=msg.split(" ");
	                        if(words.length==1){
	                            random=words[0].split("d");
	                            try {
	                                n = Integer.parseInt(random[0]);
	                                max = Integer.parseInt(random[1]);
	                            } catch (NumberFormatException e) {
	                                MessageUtils.sendMessage(event.getChannel(),"Dice Usage: '" + Main.BOT_PREFIX + "dice (launches)d(max number)'");
	                                return;
	                            }
	                           
	                           
	                            for (i=0;i<n;i++){
	                            	MessageUtils.sendMessage(event.getChannel(), "You roll: " + Integer.toString(SharedMethods.random(1, 100)) );
	                            }
	                        }
	                        else
	                            MessageUtils.sendMessage(event.getChannel(), "Dice Usage: '" + Main.BOT_PREFIX + "dice (launches)d(max number)'");
	        }else
	            MessageUtils.sendMessage(event.getChannel(), "You roll: " + Integer.toString(SharedMethods.random(1, 100)) );
	    }
   }