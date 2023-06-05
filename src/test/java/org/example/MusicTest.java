package org.example;

import org.example.Music;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import static org.mockito.Mockito.*;

public class MusicTest extends Assertions{
    Music music;
    @BeforeEach
    void setUp(){
        music=new Music("Level.wav");
    }

    @Test
    public void loadSoundTest(){
        Assertions.assertEquals(null, music.loadSound("tests.wav"));
    }

    @Test
    public void MusicTest(){
        Clip sound=mock(Clip.class);
        music.setSound(sound);
        Assertions.assertEquals(false, music.isPlaying());
        when(sound.isRunning()).thenReturn(true);
        music.start();
        Assertions.assertEquals(true, music.isPlaying());
        Mockito.verify(sound, times(1)).setMicrosecondPosition(0);
        Mockito.verify(sound, times(1)).start();
        Mockito.verify(sound, times(1)).loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Test
    void createAndLoadSound() {
        String path = "GameOver.wav";
        music = new Music(path);
        FloatControl floatControl = (FloatControl) music.getSound().getControl(FloatControl.Type.MASTER_GAIN);
        assertNotNull(music);
        assertEquals(-25.0f, floatControl.getValue());
    }

    @Test
    void stopMusic(){
        Clip sound=mock(Clip.class);
        music.setSound(sound);
        music.stop();
        Mockito.verify(sound, times(1)).stop();
    }

    @Test
    void throwException(){
        Music music = Mockito.spy(new Music("Level.wav"));

        Mockito.when(music.loadSound("tests.wav")).thenThrow(new NullPointerException());

        assertThrows(NullPointerException.class, () -> music.loadSound("tests.wav"));
    }
}
