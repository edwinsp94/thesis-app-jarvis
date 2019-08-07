package com.android.jarvis.reconocedor;

public class AudioFormato {

    private int sampleRate;
    private int sampleSizeInBits;
    private int channels;
    private int audioEncoding;
    private boolean bigEndian;

    public AudioFormato(int sampleRate, int sampleSizeInBits, int channels, int audioEncoding, boolean bigEndian) {
        this.sampleRate = sampleRate;
        this.sampleSizeInBits = sampleSizeInBits;
        this.channels = channels;
        this.audioEncoding = audioEncoding;
        this.bigEndian = bigEndian;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public int getSampleSizeInBits() {
        return sampleSizeInBits;
    }

    public int getChannels() {
        return channels;
    }

    public int getAudioEncoding() {
        return audioEncoding;
    }

    public boolean isBigEndian() {
        return bigEndian;
    }

}
