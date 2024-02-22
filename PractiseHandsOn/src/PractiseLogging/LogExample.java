package PractiseLogging;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger; //1
import java.util.logging.SimpleFormatter;
public class LogExample {

	
//log4j - Logger,Appender,Layout
//Levels - DIWEF
//Handler(Appender) - Console,File
//Layout - PatternLayout, SimpleLayout
	
	
// Classes           methods
//1.Logger     - getLogger(),log()->[debug(),info()...fatatl()]
//2.Level      - constants(INFO,.....)
//3.LogManager - getLogManager()	
//4.ConsoleHandler - setLevel()
	
	
//Applying basic logging in the whole code with the existing Logger class (present inside util and logging) in java
	//2
	private final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME) ; //returns Global Logger object - "global"
	public static void main(String[] args) {
		//3
//		logr.log(Level.INFO,"My first LOG"); //It's printing on console though we didn't mention handler becoz, It is passing upto the root handler that automatically has a little handler console on to it.                                           
//											//Root Logger can be accessed by this way -> Logger root = Logger.getLogger("");
//		LogManager.getLogManager().reset(); //It gets rid of any handlers that the root one has as default(like console)
//		logr.setLevel(Level.OFF);
		
		LogManager.getLogManager().reset();
		logr.setLevel(Level.ALL);
		
		ConsoleHandler ch = new ConsoleHandler();  //Handler
		ch.setLevel(Level.ALL);   //Level
		logr.addHandler(ch);

		try {
			FileHandler fh = new FileHandler("mylogger.log");
			fh.setLevel(Level.FINE);
			logr.addHandler(fh);
			//fh.setFormatter(new SimpleFormatter());
		} catch (IOException e) {
			logr.log(Level.INFO,"File Logger not working",e);
		}
		
		
		
//		logr.log(Level.INFO,"My first LOG"); //If we just want to display a msg then there is short cut below
		logr.info("Hello");
		
	}
	
	

}
