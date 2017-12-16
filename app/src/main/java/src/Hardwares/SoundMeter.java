package src.Hardwares;

import android.media.*;

/**
 * Created by serhat on 4.11.2017.
 */

import java.io.IOException;

import android.media.MediaRecorder;
import android.os.Environment;

public class SoundMeter {
    static final private double EMA_FILTER = 0.6;

    private MediaRecorder mRecorder = null;
    private double mEMA = 0.0;
    public static final int RECORD_AUDIO = 0;
    //Initialize the recorder to collect sound data
    public void start()  {
        if (mRecorder == null) {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile("/dev/null/");

            try {
                mRecorder.prepare();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            mRecorder.start();
            mEMA = 0.0;
        }
    }

    //Stops listening the recorder
    public void stop() {
        if (mRecorder != null) {
            stopRecording();
            mRecorder.release();
            mRecorder = null;
        }
    }

    private void stopRecording() {
        try{
            mRecorder.stop();
        }catch(RuntimeException stopException){
            //handle cleanup here
        }
    }
    //Returns the amplitude of the captured signal which will be the converted,
    //using the amplitude value
    public double getTheAmplitude(){
        if(mRecorder != null)
            return (mRecorder.getMaxAmplitude());
        else
            return 1;
    }

    //Returns the amplitude of the captured signal which will be the converted,
    //using the amplitude value
    public double getAmplitude() {
        if (mRecorder != null)
            return  (mRecorder.getMaxAmplitude()/2700.0);
        else
            return 0;

    }
    //EMA level of the captured sound
    public double getAmplitudeEMA() {
        double amp = getAmplitude();
        mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA;
        return mEMA;
    }
}