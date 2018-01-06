package Utils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class IOUtils {
	public static final String FC_FILE = "files/fcFile"; //File Name for the Friend Codes
	public static final String SCHEDULE_FILE = "files/scheduleFile"; //File Name for the Schedule Text/Link
	public static final String CURRENT_FILE = "files/currentFile"; //File Name for the Schedule Text/Link
	public static final String PERMISSION_FILE = "files/permissionFile"; //File Name for the Permissions
	public static final String EMOTE_FILE = "files/emoteFile"; //File Name for the Emotes
	public static final String MUTE_FILE = "files/muteFile"; //File Name for the Mutes
	public static final String LODESTONE_FILE = "files/lodestoneFile"; //File Name for the Lodestone
	public static final String COMMAND_LOG = "files/commandLog"; //File Name for the Emotes
	public static final String TOKEN = "files/botToken"; //Bot Token
		
	public static List<String> readFile(String fileName) throws BotException{
		return readFile(fileName, false);
	}	
	@SuppressWarnings("resource")
	private static List<String> readFile(String fileName, boolean repeat) throws BotException{
		try {
			final BufferedReader br = new BufferedReader(new FileReader(fileName));
			return br.lines().collect(Collectors.toList());
		} catch (FileNotFoundException e) {
			if(!repeat){
				LogUtils.log().warn("File '" + fileName + "' doesn't exist, trying to create", e);
				writeFile(fileName);
				return readFile(fileName, true);
			}
			else{
				throw new BotException("Unable to open or create file '" + fileName + "'", e);
			}
		}
	}

	public static void writeFile(String fileName, List<String> lines) throws BotException{
		writeFile(fileName, lines.toArray(new String[lines.size()]));
	}
	public static void writeFile(String fileName, String... lines) throws BotException{
		writeFile(fileName, false, lines);
	}
	
	public static void writeInFile(String fileName, String... lines) throws BotException{
		writeFile(fileName, true, lines);
	}
	
	private static void writeFile(String fileName, Boolean append, String... lines) throws BotException{
		synchronized(fileName){
			FileWriter fw = null;
			try {
				fw = new FileWriter(fileName, append);
				for(String line : lines)
					fw.write(line + System.lineSeparator());
			} catch (IOException e) {
				throw new BotException("Error writing lines to file '" + fileName + "'", e);
			}
			finally{
				try {
					fw.flush();
					fw.close();
				} catch (IOException e) {
					throw new BotException("Unable to close FileWriter", e);
				}
			}
		}
	}
	
	public static void log(MessageReceivedEvent event){
	    final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String logEntry = sdf.format(timestamp) + "\t" + event.getAuthor().getLongID() + "\t" + event.getAuthor().getDisplayName(event.getGuild()) + "\t" + event.getMessage().getContent();
		synchronized(COMMAND_LOG){
			try{
				String name = (event.getAuthor().getNicknameForGuild(event.getGuild()) == null) ? (event.getAuthor().getName()) : (event.getAuthor().getNicknameForGuild(event.getGuild()));
		        logEntry = sdf.format(timestamp) + "\t" + event.getAuthor().getLongID() + "\t" + name + "\t" + event.getMessage().getContent();
			} catch(Exception e){
				logEntry += " ||| ERROR PARSING";
			} finally{
			    try {
					writeInFile(COMMAND_LOG, logEntry);
				} catch (BotException e) {
					e.printStackTrace();
				}
		    }
		}
	}
}
