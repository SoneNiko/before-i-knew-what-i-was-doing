package org.example;

import javax.sound.sampled.*;
import java.util.Arrays;

public class Sound
{
    public static float SAMPLE_RATE = 8000f;


    public static void tone(int hz, int msecs) throws LineUnavailableException {
        tone(hz, msecs, 1.0);
    }

    public static void tone(int hz, int msecs, double vol) throws LineUnavailableException {
        byte[] buf = new byte[1];
        AudioFormat af = new AudioFormat(SAMPLE_RATE,8,1,true,false);


        SourceDataLine sdl = AudioSystem.getSourceDataLine(af, getMixer());

        sdl.open(af);
        sdl.start();
        for (int i=0; i < msecs*8; i++) {
            double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
            buf[0] = (byte)(Math.sin(angle) * 127.0 * vol);
            sdl.write(buf,0,1);
        }
        sdl.drain();
        sdl.stop();
        sdl.close();
    }

    public static Mixer.Info getMixer() {
        return Arrays.stream(AudioSystem.getMixerInfo()).filter(info -> info.getName().startsWith("CABLE Input")).findFirst().orElseThrow();
    }

    public static void main(String[] args) throws Exception {
//        Sound.tone(3672,5000,1);

        System.out.println(6 * 3 / 4);
        System.out.println((6 * 3) / 4);

    }


}