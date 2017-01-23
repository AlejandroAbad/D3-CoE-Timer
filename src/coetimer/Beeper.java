package coetimer;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class Beeper extends Thread
{
	public static final int ELEMENT_DELAY = 4000;
	public static final int NOTE_DELAY = 100;

	public void run()
	{
		System.out.println("Thread started");

		try
		{
			Synthesizer midiSynth = MidiSystem.getSynthesizer();
			midiSynth.open();

			// get and load default instrument and channel lists
			Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
			MidiChannel[] mChannels = midiSynth.getChannels();

			midiSynth.loadInstrument(instr[10]);// load an instrument
			midiSynth.loadInstrument(instr[20]);// loaopopd an instrument
			// omidiSynth.loadInstrument(instr[30]);// load an instrument

			MidiChannel channel = mChannels[0];

			note(channel, 50);
			Thread.sleep(ELEMENT_DELAY - NOTE_DELAY);
			
			while (true)
			{


				// Switch to physical
				note(channel, 70);
				Thread.sleep(ELEMENT_DELAY - NOTE_DELAY);
				
				// Cold
				note(channel, 90);
				Thread.sleep(1000 - NOTE_DELAY);
				note(channel, 90);
				Thread.sleep(1000 - NOTE_DELAY);
				note(channel, 90);
				Thread.sleep(1000 - NOTE_DELAY);
				note(channel, 90);
				Thread.sleep(1000 - NOTE_DELAY);
				
				// Fire
				note(channel, 100);
				Thread.sleep(ELEMENT_DELAY - NOTE_DELAY);
				
				// Ray
				note(channel, 70);
				note(channel, 70);
				Thread.sleep(ELEMENT_DELAY - (2*NOTE_DELAY));

			}
		}
		catch (InterruptedException | MidiUnavailableException e)
		{
			Thread.currentThread().interrupt();
		}

	}

	private void note(MidiChannel ch, int note) throws InterruptedException
	{
		
		ch.noteOn(note, 70);
		Thread.sleep(NOTE_DELAY);
		ch.noteOff(note, 0);
	}
}
