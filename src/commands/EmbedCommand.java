package commands;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;

public class EmbedCommand extends _BaseCommand{
    public EmbedCommand() {
        super("unitz");
    }
    
    protected void doStuff(final MessageReceivedEvent event) {
        String msg = event.getMessage().getContent();
      msg = msg.substring(msg.indexOf(" ") + 1);
      if (msg.toLowerCase().equals("dankayaka"))
          dankAyaka(event);
      if(msg.toLowerCase().equals("danktidus"))
          dankTidus(event);
;
      }

    protected void dankAyaka(final MessageReceivedEvent event){
    
        EmbedBuilder builder = new EmbedBuilder();
        builder.appendField("Pros", "Has candies", true);
        builder.appendField("Cons", "No damage", false);
        builder.appendField("★✫✫✫✫", "Get Rikku", false);

        builder.withAuthorName("Ayaya");
        builder.withAuthorIcon("https://i.imgur.com/Qz87Qsm.png");
        builder.withAuthorUrl("http://i.imgur.com/oPvYFj3.png");

        builder.withColor(255, 0, 0);
        builder.withDesc("withDesc");
        builder.withDescription("Some random bitch that can't damage for shit");
        builder.withTitle("Queen of Healz (or so she claims)");
        builder.withTimestamp(100);
        builder.withUrl("https://exvius.gamepedia.com/Ayaka");
        builder.withImage("https://images-ext-2.discordapp.net/external/TMGC_5ATSUdrdT1SJJ6e___TFWSxD2TUkVQELukY-6g/http/puu.sh/xYEF6/488026d632.jpg?width=372&height=250");

        builder.withFooterIcon("http://i.imgur.com/Ch0wy1e.png");
        builder.withFooterText("drymyreviews");
        builder.withFooterIcon("http://i.imgur.com/TELh8OT.png");
        builder.withThumbnail("https://i.imgur.com/Qz87Qsm.png");
        
        RequestBuffer.request(() -> event.getChannel().sendMessage(builder.build())); 
        
    }
        
        protected void dankTidus(final MessageReceivedEvent event){
            
            EmbedBuilder builder = new EmbedBuilder();
            builder.appendField("Pros", "Has candies", true);
            builder.appendField("Cons", "No damage", false);
            builder.appendField("★✫✫✫✫", "Get Rikku", false);

            builder.withAuthorName("Ayaya");
            builder.withAuthorIcon("https://i.imgur.com/Qz87Qsm.png");
            builder.withAuthorUrl("http://i.imgur.com/oPvYFj3.png");

            builder.withColor(255, 0, 0);
            builder.withDesc("withDesc");
            builder.withDescription("Some random bitch that can't damage for shit");
            builder.withTitle("Queen of Healz (or so she claims)");
            builder.withTimestamp(100);
            builder.withUrl("https://exvius.gamepedia.com/Ayaka");
            builder.withImage("https://images-ext-2.discordapp.net/external/TMGC_5ATSUdrdT1SJJ6e___TFWSxD2TUkVQELukY-6g/http/puu.sh/xYEF6/488026d632.jpg?width=372&height=250");

            builder.withFooterIcon("http://i.imgur.com/Ch0wy1e.png");
            builder.withFooterText("drymyreviews");
            builder.withFooterIcon("http://i.imgur.com/TELh8OT.png");
            builder.withThumbnail("https://i.imgur.com/Qz87Qsm.png");
            
            RequestBuffer.request(() -> event.getChannel().sendMessage(builder.build()));
}
        
        
}