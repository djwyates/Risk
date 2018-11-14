package risk;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class SoundManager {
    
    static private HashMap<String, Sound> sounds = new HashMap<String, Sound>();
    
    public void play(String name) {
        // System.out.println("playing: " + name);
        Sound sound = sounds.get(name);
        sound.play(name, false);
    }

    public void loop(String name) {
        // System.out.println("looping: " + name);
        Sound sound = sounds.get(name);
        sound.play(name, true);
    }

    public void addSound(String name) {
        Sound sound = new Sound(name);
        sounds.put(name, sound);
    }
    
    static public void toggleMute() {
        Iterator it = sounds.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Sound sound = (Sound)pair.getValue();
            sound.toggleMute();
        }
    }
}
