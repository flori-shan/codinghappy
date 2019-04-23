package cn.nihility.designpattern.adapter;

public class AudioPlayer implements MediaPlayer {

	MediaAdapter mediaAdapter;

	@Override
	public void play(MediaEnum audioType, String fileName) {

		if (audioType == MediaEnum.MP3) {
			System.out.println("Playing mp3 file, name = " + fileName);
		} else if (audioType == MediaEnum.MP4 || audioType == MediaEnum.VLC) {
			mediaAdapter = new MediaAdapter(audioType);
			mediaAdapter.play(audioType, fileName);
		} else {
			System.out.println("Invalid media. " + audioType + " format not supported");
		}
	}

}
