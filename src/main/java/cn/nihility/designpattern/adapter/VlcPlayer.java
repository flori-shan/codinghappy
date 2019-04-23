package cn.nihility.designpattern.adapter;

public class VlcPlayer implements AdvanceMediaPlayer {

	@Override
	public void playVlc(String fileName) {
		System.out.println("VLC Player Playing VLC. File Name: " + fileName);
	}

	@Override
	public void playMp4(String fileName) {
		System.out.println("VLC Player Can Not Playing MP4. File Name : " + fileName);
	}

}
