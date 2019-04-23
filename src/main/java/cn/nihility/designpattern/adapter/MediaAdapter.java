package cn.nihility.designpattern.adapter;

public class MediaAdapter implements MediaPlayer {

	AdvanceMediaPlayer advanceMediaPlayer;

	public MediaAdapter(MediaEnum me) {

		if (me == MediaEnum.MP4) {
			advanceMediaPlayer = new MP4Player();
		} else if (me == MediaEnum.VLC) {
			advanceMediaPlayer = new VlcPlayer();
		}

	}

	@Override
	public void play(MediaEnum audioType, String fileName) {

		if (audioType == MediaEnum.MP4) {
			advanceMediaPlayer.playMp4(fileName);
		} else if (audioType == MediaEnum.VLC) {
			advanceMediaPlayer.playVlc(fileName);
		}

	}

}
