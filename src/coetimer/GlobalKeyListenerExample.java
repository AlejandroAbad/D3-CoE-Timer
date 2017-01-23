package coetimer;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListenerExample implements NativeKeyListener
{
//	ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
	private Beeper	current_beeper;
	private final int THE_KEY = 24;
	private final int STOP_KEY = 25;
	
	public GlobalKeyListenerExample()
	{
	
	}
	
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        if ( e.getKeyCode() == THE_KEY)
        {
        	System.out.println("Starting thread phase");
        	if (current_beeper != null)
        	{
        		current_beeper.interrupt();
        	}
        	current_beeper = new Beeper();
        	current_beeper.start();
        }
        
        if (e.getKeyCode() == STOP_KEY)
        {
        	
        	if (current_beeper != null)
        	{
        		System.out.println("Interrupting thread");
        		current_beeper.interrupt();
        	}
        }
        
    }

    public void nativeKeyReleased(NativeKeyEvent e) {

    }

    public void nativeKeyTyped(NativeKeyEvent e) {
       // System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

	public static void main(String[] args)
	{

		try
		{
			// Get the logger for "org.jnativehook" and set the level to
			// warning.
			Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
			logger.setLevel(Level.WARNING);

			// Don't forget to disable the parent handlers.
			logger.setUseParentHandlers(false);

			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex)
		{
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new GlobalKeyListenerExample());
	}
}
