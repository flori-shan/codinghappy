package cn.nihility.designpattern;

import org.junit.Test;

import cn.nihility.designpattern.adapter.AudioPlayer;
import cn.nihility.designpattern.adapter.MediaEnum;

public class AdapterTest {
	
	@Test
	public void adapterTest() {
		
		AudioPlayer audioPlayer = new AudioPlayer();
		
		audioPlayer.play(MediaEnum.MP3, "MP3 music");
		audioPlayer.play(MediaEnum.MP4, "MP4 movie");
		audioPlayer.play(MediaEnum.VLC, "VLC vidoe");
		audioPlayer.play(MediaEnum.OTHER, "Other type file");
		
	}
}
