package risk;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

class Sound implements Runnable {
    
    static private HashMap<String, File> currentMusic = new HashMap<String, File>();
    static private HashMap<String, Thread> threadMap = new HashMap<String, Thread>();
    static private boolean mute;
    Thread myThread;
    File soundFile;
    
    public boolean donePlaying = false;
    Sound()
    {
        
    }
    public void run()
    {
        try {
        AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
        AudioFormat format = ais.getFormat();
    //    System.out.println("Format: " + format);
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
    
    public void play(String name) {
        soundFile = currentMusic.get(name);
        myThread = new Thread(this);
        threadMap.put(name, myThread);
        if (!mute)
            myThread.start();
    }
    
    public void addSound(String name) {
        File file = new File(name);
        currentMusic.put(name, file);
    }
    
    public void checkMusicLoop() {
        if (!mute && donePlaying)
            myThread.start();
    }
    
    static public boolean getMute()
    { return mute; }
    
    static public void muteAllSounds() {
        mute = true;
        Iterator it = threadMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Thread thread = (Thread) pair.getValue();
            thread.stop();
        }
    }
    
    static public void unmute() {
        mute = false;
    }
}
