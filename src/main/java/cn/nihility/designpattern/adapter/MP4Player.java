package cn.nihility.designpattern.adapter;

public class MP4Player implements AdvanceMediaPlayer {

	@Override
	public void playVlc(String fileName) {
		System.out.println("MP4 Player Can Not Playing VLC, File Name: " + fileName);
	}

	@Override
	public void playMp4(String fileName) {
		System.out.println("MP4 Player Playing MP4, File Name : " + fileName);
	}

}
