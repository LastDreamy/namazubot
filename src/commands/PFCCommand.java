package commands;
import Utils.*;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

public class PFCCommand extends _BaseCommand{
    public PFCCommand() {
        super("pfc");
    }



    @Override
    protected void doStuff(final MessageReceivedEvent event) {
        IMessage message = event.getMessage();
        String msg = message.getContent();
        int i;
        String attack="";
        i=SharedMethods.random(1, 3);
        switch(i){
            case 1: attack=":fist:"; break;
            case 2: attack=":v:"; break;
            case 3: attack=":hand_splayed:"; break;
        }
        if(msg.contains(" ")){
        	 msg = msg.substring(msg.indexOf(" ") + 1);
             if(msg.startsWith("go")){
                 MessageUtils.sendMessage(event.getChannel(), attack);
             }else{
            	 MessageUtils.sendMessage(event.getChannel(), "%rps "+
                         Integer.toString(Integer.parseInt(msg)-1));
             }
       

            }
        }
    }

    