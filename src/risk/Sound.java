
package risk;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class Sound implements Runnable {
    private Thread myThread = null;
    private File soundFile = null;
    private boolean mute = false;
    private boolean donePlaying = false;
    private boolean playing = false;
    
    Sound(String name) {
        soundFile = new File(name);
    }
    
    public void play() {
        myThread = new Thread(this);
        if (!mute) {
            myThread.start();
            playing = true;
        }
    }
    
    public void run() {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = ais.getFormat();
            // System.out.println("Format: " + format);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            SourceDataLine source = (SourceDataLine) AudioSystem.getLine(info);
            source.open(format);
            source.start();
            int read = 0;
            byte[] audioData = new byte[16384];
            while (read > -1){
                read = ais.read(audioData,0,audioData.length);
                if (read >= 0) {
                    source.write(audioData,0,read);
                }
            }
            donePlaying = true;

            source.drain();
            source.close();
        }
        catch (Exception exc) {
            System.out.println("error: " + exc.getMessage());
            exc.printStackTrace();
        }
    }
    
    public void checkMusicLoop() {
        if (!mute && donePlaying)
            myThread.start();
    }
    
    public void toggleMute() {
        if (mute)
            unmute();
        else
            mute();
    }
    
    private void mute() {
        if (playing && !mute) {
            myThread.stop();
            mute = true;
        }
    }
    
    private void unmute() {
        if (playing && mute) {
            myThread.start();
            mute = false;
        }
    }
    
    public boolean getMute()
    { return mute; }
}
