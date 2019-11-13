package org;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	
	public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("punnch.wav"));
	public static final AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("smb_mariodie.wav"));
	public static final AudioClip MARCHA = Applet.newAudioClip(Sound.class.getResource("marcha.wav"));
	
	public Sound() {
		// TODO Auto-generated constructor stub
	}

}
